package com.umair.chatme.main.profile

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.umair.chatme.data.User
import com.umair.chatme.main.profile.component.EditProfileSection
import com.umair.chatme.main.profile.component.SettingsSection
import com.umair.chatme.util.Resource

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {

	val context = LocalContext.current
	val result = viewModel.result.collectAsState().value
	val updateResult = viewModel.updateResult.collectAsState().value
	var isVisible = remember { mutableStateOf(false) }
	lateinit var  user: User

	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent()
	) { uri ->
		uri?.let { it ->
			viewModel.updateProfileImage(it, user)
		}
	}

	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		Column {
			when(result) {
				is Resource.Loading -> {
					Column(
						modifier = Modifier.fillMaxSize(),
						verticalArrangement = Arrangement.Center,
						horizontalAlignment = Alignment.CenterHorizontally
					) {
						CircularProgressIndicator()
					}
				}
				is Resource.Success -> {
						result.data?.let { it ->
							user = it
							LaunchedEffect(Unit) {
								isVisible.value = true
							}
							AnimatedVisibility(
								visible = isVisible.value,
								enter = fadeIn(animationSpec = tween(durationMillis = 2000)) +
										scaleIn(initialScale = 0.8f, animationSpec = tween(durationMillis = 2000))
							) {
								EditProfileSection(it, updateProfilePic = { launcher.launch("image/*") }, editProfile = { })
							}

							AnimatedVisibility(
								visible = isVisible.value,
								enter = fadeIn(animationSpec = tween(durationMillis = 2000, delayMillis = 200)) +
										scaleIn(initialScale = 0.8f, animationSpec = tween(durationMillis = 2000, delayMillis = 200))
							) {
								SettingsSection()
							}
						}
				}
				is Resource.Error -> {
					LaunchedEffect(true) {
						Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
					}
				}
				is Resource.ideal -> {
					Column {  }
				}
			}

			when(updateResult) {
				is Resource.Loading -> {
					Column(
						modifier = Modifier.fillMaxSize(),
						verticalArrangement = Arrangement.Center,
						horizontalAlignment = Alignment.CenterHorizontally
					) {
						CircularProgressIndicator()
					}
				}
				is Resource.Success -> {
					LaunchedEffect(true) {
						Toast.makeText(context, updateResult.data, Toast.LENGTH_SHORT).show()
					}
				}
				is Resource.Error -> {
					LaunchedEffect(true) {
						Toast.makeText(context, updateResult.message.toString(), Toast.LENGTH_SHORT).show()
					}
				}
				is Resource.ideal -> {
					Column {  }
				}
			}
		}//: Column
	}//: Surface
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	ProfileScreen(viewModel = hiltViewModel())
}
