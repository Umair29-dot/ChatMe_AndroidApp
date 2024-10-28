package com.umair.chatme.main.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umair.chatme.R
import com.umair.chatme.data.UserProfile

@Composable
fun EditProfileSection(user: UserProfile) {
	val image: String? = null

	Card(
		modifier = Modifier
			.fillMaxWidth()
			.height(350.dp)
			.padding(horizontal = 20.dp, vertical = 10.dp),
		shape = RoundedCornerShape(20.dp),
		colors = CardDefaults.cardColors(Color.White),
		elevation = CardDefaults.cardElevation(5.dp)
	) {
		Column(
			modifier = Modifier
				.fillMaxSize(),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			if(!image.isNullOrBlank()) {
				Image(
					painter = painterResource(R.drawable.my_pic),
					contentScale = ContentScale.FillBounds,
					contentDescription = "user photo",
					modifier = Modifier
						.clip(CircleShape)
						.background(colorResource(R.color.black))
						.size(100.dp)
				)
			} else {
				Box(
					modifier = Modifier
						.clip(CircleShape)
						.background(colorResource(R.color.light_gray))
						.size(100.dp),
					contentAlignment = Alignment.BottomEnd
				) {
					Box(
						modifier = Modifier
							.offset(x = (-5).dp, y = (-5).dp)
							.clip(CircleShape)
							.background(colorResource(R.color.white))
							.size(40.dp),
						contentAlignment = Alignment.Center
					) {
						Icon(
							imageVector = Icons.Filled.Edit,
							contentDescription = "Edit",
							tint = Color.Blue,
							modifier = Modifier.size(20.dp)
						)
					}
				}
			}

			Spacer(
				modifier = Modifier.height(20.dp)
			)

			Text(user.userName,
				fontWeight = FontWeight.Bold,
				style = MaterialTheme.typography.titleLarge
			)

			Spacer(
				modifier = Modifier.height(10.dp)
			)

			Text(user.email)

			Spacer(
				modifier = Modifier.height(30.dp)
			)

			Button(
				onClick = {

				}
			) {
				Row {
					Icon(imageVector = Icons.Filled.Edit, contentDescription = "edit")
					Spacer(modifier = Modifier.width(5.dp))
					Text("Edit Profile",
						fontSize = 18.sp
					)
				}
			}
		}
	}//: Card
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	EditProfileSection(
		user = UserProfile(
			uid = "11223",
			userName = "Umair Nazim",
			email = "umair@gmail.com",
			phoneNo = "0628893",
			profilePhoto = null,
			isOnline = false
		)
	)
}