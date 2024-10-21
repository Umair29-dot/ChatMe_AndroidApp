package com.umair.chatme.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.umair.chatme.R

@Composable
fun SignUpScreen(navController: NavController) {
	var userName = remember {
		mutableStateOf("")
	}
	var phoneNumber = remember {
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
			modifier = Modifier.padding(20.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Spacer(
				modifier = Modifier
					.height(20.dp)
			)

			Image(
				painter = painterResource(R.drawable.person_chatting_3d),
				contentDescription = "Icon",
				modifier = Modifier
					.fillMaxWidth()
					.height(300.dp)
			)

			Spacer(
				modifier = Modifier
					.height(20.dp)
			)

			OutlinedTextField(
				value = email.value,
				onValueChange = { email.value = it},
				label = { Text("Email") },
				shape = RoundedCornerShape(10.dp),
				modifier = Modifier.fillMaxWidth(),
				leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = "Email Icon") },
				singleLine = true
			)

			Spacer(
				modifier = Modifier
					.height(10.dp)
			)

			OutlinedTextField(
				value = email.value,
				onValueChange = { email.value = it},
				label = { Text("Phone Number") },
				shape = RoundedCornerShape(10.dp),
				modifier = Modifier.fillMaxWidth(),
				leadingIcon = { Icon(imageVector = Icons.Filled.Phone, contentDescription = "Phone Icon") },
				singleLine = true
			)

			Spacer(
				modifier = Modifier
					.height(10.dp)
			)

			OutlinedTextField(
				value = password.value,
				onValueChange = { password.value = it},
				label = { Text("Password") },
				shape = RoundedCornerShape(10.dp),
				modifier = Modifier.fillMaxWidth(),
				leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon")},
				singleLine = true
			)

			Spacer(
				modifier = Modifier
					.height(30.dp)
			)

			Button(
				onClick = {
					navController.popBackStack()
				},
				modifier = Modifier.fillMaxWidth()
			) {
				Text("Sign Up")
			}
		}//: Column
	}//: Surface
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	SignUpScreen(navController = rememberNavController())
}