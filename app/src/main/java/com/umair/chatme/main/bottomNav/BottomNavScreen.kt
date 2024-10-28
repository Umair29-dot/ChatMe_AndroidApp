package com.umair.chatme.main.bottomNav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.umair.chatme.R
import com.umair.chatme.data.BottomNavItem
import com.umair.chatme.main.MainScreens
import com.umair.chatme.main.bottomNav.component.BottomNavMenu
import com.umair.chatme.main.bottomNav.component.TopBarSection
import com.umair.chatme.main.chat.ChatScreen
import com.umair.chatme.main.profile.ProfileScreen
import com.umair.chatme.main.profile.ProfileViewModel
import com.umair.chatme.navGraph.Route

@Composable
fun BottomNavScreen(viewModel: BottomNavViewModel) {

	val navController = rememberNavController()

	val items = listOf(
		BottomNavItem(icon = painterResource(R.drawable.chat), text = MainScreens.Chat.name),
		BottomNavItem(icon = painterResource(R.drawable.status), text = MainScreens.Status.name),
		BottomNavItem(icon = painterResource(R.drawable.user), text = MainScreens.Profile.name)
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
		},
		topBar = {
			viewModel.screenTitle.value?.let { it ->
				TopBarSection(it)
			}
		}
	) { it ->
		val topPadding = it.calculateTopPadding()

		NavHost(
			navController = navController,
			startDestination = Route.ChatScreen.route,
			modifier = Modifier.padding(top = topPadding)
		) {
			composable(route = Route.ChatScreen.route) {
				viewModel.setScreenTitle(MainScreens.Chat.name)
				ChatScreen()
			}
			composable(route = Route.StatusScreen.route) {
				viewModel.setScreenTitle(MainScreens.Status.name)
			}
			composable(route = Route.ProfileScreen.route) {
				viewModel.setScreenTitle(MainScreens.Profile.name)
				val vm: ProfileViewModel = hiltViewModel()
				ProfileScreen(viewModel = vm)
			}
		}//: NavHost
	}//: Scaffold
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
	BottomNavScreen(viewModel = viewModel())
}