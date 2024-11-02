package com.umair.chatme.main.chat

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.umair.chatme.data.Chat
import com.umair.chatme.data.User
import com.umair.chatme.util.Constants.USER_COLLECTION
import com.umair.chatme.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
	private val db: FirebaseFirestore,
	private val auth: FirebaseAuth
): ViewModel() {

	val TAG = "ChatViewModel"

	private var _chats: MutableStateFlow<Resource<Chat>> = MutableStateFlow(Resource.ideal())
	var chat = _chats.asStateFlow()

	var currentUser: MutableState<User?> = mutableStateOf(null)

	init {
		getCurrentUser()
	}

	private fun getCurrentUser() {
		viewModelScope.launch(Dispatchers.IO) {
			val uuid = auth.currentUser!!.uid
			db.collection(USER_COLLECTION)
				.document(uuid)
				.get()
				.addOnSuccessListener { it ->
					val user = it.toObject(User::class.java)!!
					currentUser.value = user
				}
				.addOnFailureListener { it ->
					Log.d(TAG, it.message.toString())
				}
		}
	}

	private fun getChats() {
		viewModelScope.launch(Dispatchers.IO) {

		}
	}

	fun addChat(phoneNo: String) {
		viewModelScope.launch(Dispatchers.IO) {
			db.collection(USER_COLLECTION)
				.whereEqualTo("phoneNo", phoneNo)
				.get()
				.addOnCompleteListener { task ->
					if (task.isSuccessful) {
						if (!task.result.isEmpty) {
							Log.d("Firestore", "Document exists with phoneNo: $phoneNo")
						} else {
							Log.d("Firestore", "No document exists with phoneNo: $phoneNo")
						}
					} else {
						Log.e("Firestore", "Error getting documents: ", task.exception)
					}
				}
		}
	}

}