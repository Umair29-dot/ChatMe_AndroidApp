package com.umair.chatme.auth.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.umair.chatme.R
import com.umair.chatme.navGraph.Route
import com.umair.chatme.util.Resource

@Composable
fun SignInScreen(navController: NavController, viewModel: SignInViewModel) {
	var email = remember {
		mutableStateOf("")
	}
	var password = remember {
		mutableStateOf("")
	}
	val context = LocalContext.current
	val result = viewModel.result.collectAsState().value

	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		Column(
			modifier = Modifier.padding(20.dp)
				.verticalScroll(rememberScrollState()),
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
					.height(10.dp)
			)

			Text("Forget Password?",
				modifier = Modifier.fillMaxWidth(),
				textAlign = TextAlign.End
				)

			Spacer(
				modifier = Modifier
					.height(20.dp)
			)

			Button(
				onClick = {
					viewModel.userSignIn(email.value.trim(), password.value.trim())
				},
				modifier = Modifier.fillMaxWidth()
			) {
				Text("Sign In")
			}

			Spacer(
				modifier = Modifier
					.height(10.dp)
			)

			Text("Don't have an account? Signup",
				modifier = Modifier.fillMaxWidth()
					.clickable {
						navController.navigate(Route.SignUpScreen.route)
					},
				textAlign = TextAlign.Center
			)

			when(result) {
				is Resource.Loading -> {
					CircularProgressIndicator()
				}
				is Resource.Success -> {
					LaunchedEffect(true) {
						//navController.navigate(Route.SignUpScreen.route)
						Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show()
					}
				}
				is Resource.Error -> {
					Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
				}
				is Resource.ideal -> {}
			}
		}//: Column
	}//: Surface
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	SignInScreen(
		navController = rememberNavController(),
		viewModel = hiltViewModel()
	)
}