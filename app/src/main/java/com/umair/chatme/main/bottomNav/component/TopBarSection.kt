package com.umair.chatme.main.bottomNav.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBarSection(title: String) {
	Text(title,
		style = MaterialTheme.typography.headlineLarge,
		fontWeight = FontWeight.Bold,
		modifier = Modifier.fillMaxWidth()
			.padding(top = 50.dp, bottom = 15.dp, start = 15.dp, end = 15.dp)
	)
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	TopBarSection(title = "Chat")
}