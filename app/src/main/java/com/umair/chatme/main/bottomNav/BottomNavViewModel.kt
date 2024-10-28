package com.umair.chatme.main.bottomNav

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BottomNavViewModel: ViewModel() {

	var screenTitle: MutableState<String?> = mutableStateOf(null)

	fun setScreenTitle(title: String) {
		this.screenTitle.value = title
	}

}