package com.umair.chatme.navGraph

sealed class Route(val route: String) {
	object AppStartNavigation: Route(route = "appStartNavigation")
	object SplashScreen: Route(route = "splashScreen")
	object SignInScreen: Route(route = "signInScreen")
	object SignUpScreen: Route(route = "signUpScreen")
	object AppChatNavigation: Route(route = "appChatNavigation")
	object BottomNavScreen: Route(route = "bottomNavScreen")
	object ChatScreen: Route(route = "chatScreen")
	object StatusScreen: Route(route = "statusScreen")
	object ProfileScreen: Route(route = "profileScreen")
}