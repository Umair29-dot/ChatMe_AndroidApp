package com.umair.chatme.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.umair.chatme.R
import com.umair.chatme.navGraph.Route
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		Column (
			modifier = Modifier
				.background(color = colorResource(R.color.splash)),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Box(
				modifier = Modifier
					.fillMaxSize(0.5f)
					.padding(vertical = 100.dp)
					.border(
						width = 2.dp,
						color = Color.White,
						shape = CircleShape
					),
				contentAlignment = Alignment.Center
			) {
				Image(
					painter = painterResource(R.drawable.chat_icon),
					contentDescription = "app icon",
					contentScale = ContentScale.Crop,
					modifier = Modifier.size(300.dp)
				)
			}//: Box

			Text("Chat Me",
				color = Color.White,
				fontSize = 50.sp,
				fontWeight = FontWeight.Bold
				)

			LaunchedEffect(true) {
				delay(2000)
				navController.navigate(Route.SigninScreen.route) {
					this.popUpTo(Route.SplashScreen.route) {
						inclusive = true
					}
				}
			}
		}//: Column
	}//: Surface
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	SplashScreen(navController = rememberNavController())
}