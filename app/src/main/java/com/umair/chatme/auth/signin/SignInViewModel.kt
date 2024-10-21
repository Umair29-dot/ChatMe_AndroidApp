package com.umair.chatme.auth.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.umair.chatme.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
	private val auth: FirebaseAuth
): ViewModel() {

	private val LOG = "SignInViewModel"

	private var _result: MutableStateFlow<Resource<String>> = MutableStateFlow(Resource.ideal())
	val result = _result.asStateFlow()

	fun userSignIn(email: String, password: String) {
		try {
			_result.value = Resource.Loading()

			viewModelScope.launch(Dispatchers.IO) {
				auth.signInWithEmailAndPassword(email, password)
					.addOnSuccessListener {
						_result.value = Resource.Success(data = it.user!!.uid)
					}
					.addOnFailureListener {
						_result.value = Resource.Error(message = it.message.toString())
					}
			}
		} catch (e: Exception) {
			Log.d(LOG, e.message.toString())
		}
	}

}