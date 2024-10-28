package com.umair.chatme.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.umair.chatme.data.UserProfile
import com.umair.chatme.util.Constants.USER_COLLECTION
import com.umair.chatme.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
	private val auth: FirebaseAuth,
	private val db: FirebaseFirestore
): ViewModel() {

	private var _result: MutableStateFlow<Resource<UserProfile>> = MutableStateFlow(Resource.ideal())
	var result = _result.asStateFlow()

	init {
		getUserInfo()
	}

	private fun getUserInfo() {
		try {
			_result.value = Resource.Loading()

			viewModelScope.launch(Dispatchers.IO) {
				val uid = auth.currentUser?.uid ?: return@launch
				db.collection(USER_COLLECTION)
					.document(uid)
					.addSnapshotListener { snapshot, error ->
						if (error != null) {
							_result.value = Resource.Error(error.message.toString())
							return@addSnapshotListener
						}
						if (snapshot != null && snapshot.exists()) {
							val currentUser = snapshot.toObject(UserProfile::class.java)!!
							_result.value = Resource.Success(currentUser)
						} else {
							_result.value = Resource.Error("Couldn't find the user")
						}
					}
			}
		} catch (e: Exception) {
			_result.value = Resource.Error(e.message.toString())
		}
	}

}