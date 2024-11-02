package com.umair.chatme.main.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.umair.chatme.data.User
import com.umair.chatme.util.Constants.USER_COLLECTION
import com.umair.chatme.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
	private val auth: FirebaseAuth,
	private val db: FirebaseFirestore,
	private val firebaseStorage: FirebaseStorage
): ViewModel() {

	val TAG = "ProfileViewModel"

	private var _result: MutableStateFlow<Resource<User>> = MutableStateFlow(Resource.ideal())
	var result = _result.asStateFlow()

	private var _updateResult: MutableStateFlow<Resource<String>> = MutableStateFlow(Resource.ideal())
	var updateResult = _updateResult.asStateFlow()

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
							val currentUser = snapshot.toObject(User::class.java)!!
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

	fun updateProfileImage(uri: Uri, userProfile: User) {
		try {
			viewModelScope.launch(Dispatchers.IO) {
				val uuid = UUID.randomUUID()
				firebaseStorage.reference.child("images/$uuid")
					.putFile(uri)
					.addOnSuccessListener { it ->
						viewModelScope.launch {
							val downloadUrl = it.storage.downloadUrl.await().toString()
							val user = userProfile.copy(profilePhoto = downloadUrl)
							withContext(Dispatchers.Main) {
								updateUserProfile(user, isProgress = false)
							}
						}
					}
					.addOnFailureListener {
						_updateResult.value = Resource.Error(it.message.toString())
						return@addOnFailureListener
					}
			}
		} catch (e: Exception) {
			_updateResult.value = Resource.Error(e.message.toString())
		}
	}

	fun updateUserProfile(userProfile: User, isProgress: Boolean) {
		try {
			if (isProgress) {
				_updateResult.value = Resource.Loading()
			}
			viewModelScope.launch(Dispatchers.IO) {
				db.collection(USER_COLLECTION)
					.document(userProfile.uid)
					.set(userProfile)
					.addOnSuccessListener {
						_updateResult.value = Resource.Success("Updated Successfully")
					}
					.addOnFailureListener {
						_updateResult.value = Resource.Error(it.message.toString())
						return@addOnFailureListener
					}
			}
		} catch (e: Exception) {
			_updateResult.value = Resource.Error(e.message.toString())
		}
	}

}