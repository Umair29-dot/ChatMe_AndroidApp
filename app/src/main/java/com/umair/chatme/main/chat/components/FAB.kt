package com.umair.chatme.main.chat.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FAB(onTap: () -> Unit) {
	Button(
		onClick = {
			onTap.invoke()
		},
		modifier = Modifier
			.padding(bottom = 150.dp, end = 15.dp)
			.size(60.dp),
		shape = CircleShape
	) {
		Icon(
			imageVector = Icons.Filled.Add,
			contentDescription = "Add Icon",
			modifier = Modifier
				.size(30.dp)
		)
	}
}