package com.umair.chatme.navGraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.umair.chatme.auth.signin.SignInScreen
import com.umair.chatme.auth.signin.SignInViewModel
import com.umair.chatme.auth.signup.SignUpScreen
import com.umair.chatme.auth.signup.SignUpViewModel
import com.umair.chatme.splash.SplashScreen

@Composable
fun NavGraph(startDestination: String) {

	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = startDestination) {
		navigation(
			route = Route.AppStartNavigation.route,
			startDestination = Route.SplashScreen.route
		) {
			composable(route = Route.SplashScreen.route) {
				SplashScreen(navController = navController)
			}
			composable(route = Route.SignInScreen.route) {
				val viewModel: SignInViewModel = hiltViewModel()
				SignInScreen(navController = navController, viewModel = viewModel)
			}
			composable(route = Route.SignUpScreen.route) {
				val viewModel: SignUpViewModel = hiltViewModel()
				SignUpScreen(navController = navController, viewModel = viewModel)
			}
		}
	}

}