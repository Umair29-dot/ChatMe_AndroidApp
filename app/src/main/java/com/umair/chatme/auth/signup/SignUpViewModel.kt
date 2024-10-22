package com.umair.chatme.auth.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.umair.chatme.data.UserProfile
import com.umair.chatme.util.Constants
import com.umair.chatme.util.Constants.USER_COLLECTION
import com.umair.chatme.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
	private val auth: FirebaseAuth,
	private val db: FirebaseFirestore
): ViewModel() {

	private val LOG = "SignupViewModel"

	private var _result: MutableStateFlow<Resource<String>> = MutableStateFlow(Resource.ideal())
	val result = _result.asStateFlow()

	fun userSignUp(userName: String, email: String, phoneNo: String, password: String) {
		try{
			_result.value = Resource.Loading()

			viewModelScope.launch(Dispatchers.IO) {
				auth.createUserWithEmailAndPassword(email, password)
					.addOnSuccessListener {
						val profile = UserProfile(
							uid = it.user!!.uid,
							userName = userName,
							email = email,
							phoneNo = phoneNo,
							profilePhoto = null,
							isOnline = false
						)
						createUserProfile(profile)
					}
					.addOnFailureListener {
						_result.value = Resource.Error(message = it.message.toString())
					}
			}
		} catch (e: Exception) {
			Log.d(LOG, e.message.toString())
		}
	}

	private fun createUserProfile(userProfile: UserProfile) {
		db.collection(USER_COLLECTION).document(userProfile.uid)
			.set(userProfile)
			.addOnSuccessListener {
				_result.value = Resource.Success(data = "User created successfully")
			}
			.addOnFailureListener {
				_result.value = Resource.Error(message = it.message.toString())
			}
	}

}