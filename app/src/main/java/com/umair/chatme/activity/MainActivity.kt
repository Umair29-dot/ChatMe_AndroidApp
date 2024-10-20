package com.umair.chatme.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.umair.chatme.splash.SplashScreen
import com.umair.chatme.ui.theme.ChatMeTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			ChatMeTheme {
				Start()
			}
		}
	}
}

@Composable
private fun Start() {
	SplashScreen()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	ChatMeTheme {
		Start()
	}
}