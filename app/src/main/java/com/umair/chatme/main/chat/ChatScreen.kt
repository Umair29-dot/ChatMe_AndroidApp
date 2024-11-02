package com.umair.chatme.main.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.umair.chatme.data.User
import com.umair.chatme.main.chat.components.AddAlertDialog
import com.umair.chatme.main.chat.components.FAB

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
	var currentUser = viewModel.currentUser.value

	var showDialog = remember {
		mutableStateOf(false)
	}

	var personNumber = rememberSaveable {
		mutableStateOf("")
	}

	Box(
		modifier = Modifier
			.fillMaxSize(),
		contentAlignment = Alignment.BottomEnd
	) {
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(horizontal = 7.dp)
			) {
				Spacer(modifier = Modifier.height(10.dp))

				CurrentUserSection(user = currentUser)


				if(showDialog.value) {
					AddAlertDialog(showDialog = showDialog, personNumber = personNumber) {
						showDialog.value = false
						viewModel.addChat(phoneNo = personNumber.value.trim())
					}
				}
			}//: Column

		FAB(onTap = {
			showDialog.value = true
		})
	}//: Box
}

@Composable
fun CurrentUserSection(user: User?) {
	Row(
		modifier = Modifier
			.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically
	) {
		AsyncImage(
			model = ImageRequest.Builder(LocalContext.current)
				.data(user?.profilePhoto ?: "")
				.crossfade(true)
				.scale(Scale.FILL)
				.build(),
			contentDescription = "User photo",
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.clip(CircleShape)
				.size(70.dp)
		)

		Spacer(modifier = Modifier.width(10.dp))

		Column {
			Text(user?.userName ?: "",
				style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
			)

			Text(user?.email ?: "")
		}//: Column
	}//: Row
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	ChatScreen(viewModel = hiltViewModel())
}
