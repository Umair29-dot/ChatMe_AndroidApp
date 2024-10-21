package com.umair.chatme.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.umair.chatme.navGraph.NavGraph
import com.umair.chatme.navGraph.Route
import com.umair.chatme.splash.SplashScreen
import com.umair.chatme.ui.theme.ChatMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			ChatMeTheme {
				NavGraph(Route.AppStartNavigation.route)
			}
		}
	}
}