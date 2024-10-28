package com.umair.chatme.main.profile

import android.widget.Toast
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
import com.umair.chatme.main.profile.component.EditProfileSection
import com.umair.chatme.main.profile.component.SettingsSection
import com.umair.chatme.util.Resource

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {

	val context = LocalContext.current
	val result = viewModel.result.collectAsState().value
	var isVisible = remember { mutableStateOf(false) }

	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		when(result) {
			is Resource.Loading -> {
				Column(
					verticalArrangement = Arrangement.Center,
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					CircularProgressIndicator()
				}
			}
			is Resource.Success -> {
				Column {
					result.data?.let { it ->
						LaunchedEffect(Unit) {
							isVisible.value = true
						}
						AnimatedVisibility(
							visible = isVisible.value,
							enter = fadeIn(animationSpec = tween(durationMillis = 2000)) +
									scaleIn(initialScale = 0.8f, animationSpec = tween(durationMillis = 2000))
						) {
							EditProfileSection(it)
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
			}
			is Resource.Error -> {
				LaunchedEffect(true) {
					Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
				}
			}
			is Resource.ideal -> TODO()
		}
	}//: Surface
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	ProfileScreen(viewModel = hiltViewModel())
}
