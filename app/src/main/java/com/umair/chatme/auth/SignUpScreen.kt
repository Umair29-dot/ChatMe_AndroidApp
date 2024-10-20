package com.umair.chatme.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen() {
	var userName = remember {
		mutableStateOf("")
	}
	var email = remember {
		mutableStateOf("")
	}
	var password = remember {
		mutableStateOf("")
	}

	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		Column(
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			OutlinedTextField(
				value = userName.value,
				onValueChange = { userName.value = it},
				label = { Text("Username") }
			)

			Spacer(
				modifier = Modifier
					.height(10.dp)
			)

			OutlinedTextField(
				value = email.value,
				onValueChange = { email.value = it},
				label = { Text("Email") }
			)

			Spacer(
				modifier = Modifier
					.height(10.dp)
			)

			OutlinedTextField(
				value = password.value,
				onValueChange = { password.value = it},
				label = { Text("Password") }
			)

			Spacer(
				modifier = Modifier
					.height(10.dp)
			)

			Spacer(
				modifier = Modifier
					.height(20.dp)
			)

			Button(
				onClick = {

				}
			) {
				Text("SignUp")
			}

			Spacer(
				modifier = Modifier
					.height(10.dp)
			)
		}//: Column
	}//: Surface
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	SignUpScreen()
}