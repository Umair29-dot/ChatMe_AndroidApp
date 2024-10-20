package com.umair.chatme.navGraph

sealed class Route(val route: String) {
	object AppStartNavigation: Route(route = "appStartNavigation")
	object SplashScreen: Route(route = "splashScreen")
	object SigninScreen: Route(route = "signinScreen")
	object SignupScreen: Route(route = "signupScreen")
}