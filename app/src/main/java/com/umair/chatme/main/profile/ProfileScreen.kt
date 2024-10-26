package com.umair.chatme.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.umair.chatme.R

@Composable
fun ProfileScreen() {
	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(colorResource(R.color.teal_200))
		) {
			Text("Chat Screen")
		}//: Column
	}//: Surface
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	ProfileScreen()
}
