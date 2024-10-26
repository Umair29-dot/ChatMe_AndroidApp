package com.umair.chatme.main.bottomNav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.umair.chatme.R
import com.umair.chatme.data.BottomNavItem
import com.umair.chatme.main.bottomNav.component.BottomNavMenu
import com.umair.chatme.main.chat.ChatScreen
import com.umair.chatme.navGraph.Route

@Composable
fun BottomNavScreen() {

	val navController = rememberNavController()

	val items = listOf(
		BottomNavItem(icon = painterResource(R.drawable.chat), text = "Chats"),
		BottomNavItem(icon = painterResource(R.drawable.status), text = "Status"),
		BottomNavItem(icon = painterResource(R.drawable.user), text = "Profile")
	)

	var selectedIndex = rememberSaveable {
		mutableStateOf(0)
	}

	val backStackState = navController.currentBackStackEntryAsState().value
	selectedIndex.value = when(backStackState?.destination?.route) {
		Route.ChatScreen.route -> 0
		Route.StatusScreen.route -> 1
		Route.ProfileScreen.route -> 2
		else -> 0
	}

	Scaffold(
		modifier = Modifier
			.fillMaxSize(),
		bottomBar = {
			BottomNavMenu(
				items = items,
				selectedIndex = selectedIndex.value,
				onClick = {
					when(it) {
						0 -> navController.navigate(Route.ChatScreen.route)
						1 -> navController.navigate(Route.StatusScreen.route)
						2 -> navController.navigate(Route.ProfileScreen.route)
					}
				}
			)
		}
	) { it ->
		val bottomPadding = it.calculateBottomPadding()

		NavHost(navController = navController, startDestination = Route.ChatScreen.route) {
			composable(route = Route.ChatScreen.route) {
				ChatScreen()
			}
			composable(route = Route.StatusScreen.route) {

			}
			composable(route = Route.ProfileScreen.route) {

			}
		}//: NavHost
	}//: Scaffold

}

@Composable
@Preview(showBackground = true)
private fun Preview() {
	BottomNavScreen()
}