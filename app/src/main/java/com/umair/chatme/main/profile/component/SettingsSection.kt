package com.umair.chatme.main.profile.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.umair.chatme.R

@Composable
fun SettingsSection() {
	val scrollState = rememberScrollState()

	Card(
		modifier = Modifier
			.fillMaxWidth()
			.height(450.dp)
			.padding(horizontal = 20.dp),
		colors = CardDefaults.cardColors(Color.White),
		elevation = CardDefaults.cardElevation(5.dp)
	) {
		Column(
			modifier = Modifier
				.verticalScroll(scrollState)
				.padding(5.dp)
		){
			Spacer(modifier = Modifier.height(15.dp))
			SettingsItem(iconColor = Color.Blue, icon = painterResource(R.drawable.clock), label = "TimeZone") {

			}
			Spacer(modifier = Modifier.height(8.dp))
			SettingsItem(iconColor = Color.DarkGray, icon = painterResource(R.drawable.language), label = "Language") {

			}
		}//: Column
	}//: Card
}

@Composable
fun SettingsItem(iconColor: Color, icon: Painter, label: String, onClick: () -> Unit) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.height(45.dp)
			.padding(horizontal = 10.dp)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.height(40.dp),
			verticalAlignment = Alignment.CenterVertically
		){
			Icon(
				painter = icon,
				contentDescription = "Clock Icon",
				tint = iconColor,
				modifier = Modifier
					.weight(1f)
					.clip(RoundedCornerShape(8.dp))
					.background(iconColor.copy(alpha = 0.1f))
					.padding(10.dp) // Inner padding for the icon size within the background
			)

			Spacer(modifier = Modifier.width(10.dp))

			Text(label,
				modifier = Modifier
					.weight(3f),
				style = MaterialTheme.typography.titleMedium,
				fontWeight = FontWeight.SemiBold
			)

			Spacer(modifier = Modifier.width(10.dp))

			Text("Timezone",
				modifier = Modifier
					.weight(3f),
				style = MaterialTheme.typography.bodySmall
			)
		}//: Row

		Spacer(modifier = Modifier.height(2.dp))

		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.height(1.dp)
				.background(Color.Gray)
		)
	}//: Column
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	SettingsSection()
}