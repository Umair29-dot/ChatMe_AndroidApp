package com.umair.chatme.navGraph

sealed class Route(val route: String) {
	object AppStartNavigation: Route(route = "appStartNavigation")
	object SplashScreen: Route(route = "splashScreen")
	object SignInScreen: Route(route = "signinScreen")
	object SignUpScreen: Route(route = "signupScreen")
}