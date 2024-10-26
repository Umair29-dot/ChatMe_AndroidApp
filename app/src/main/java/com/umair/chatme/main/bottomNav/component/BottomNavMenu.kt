package com.umair.chatme.main.bottomNav.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.umair.chatme.data.BottomNavItem
import com.umair.chatme.R

@Composable
fun BottomNavMenu(
	items: List<BottomNavItem>,
	selectedIndex: Int,
	onClick: (Int) -> Unit = {}
) {

	Card(
		modifier = Modifier
			.fillMaxWidth()
			.height(140.dp)
			.padding(start = 10.dp, end = 10.dp, bottom = 50.dp),
		shape = RoundedCornerShape(10.dp),
		elevation = CardDefaults.cardElevation(5.dp)
	) {
		Row(
			modifier = Modifier.fillMaxSize()
		) {
			items.forEachIndexed { index, item ->
				Column(
					modifier = Modifier
						.weight(4F)
						.fillMaxHeight()
						.clickable {
							onClick(index)
						},
					horizontalAlignment = Alignment.CenterHorizontally,
					verticalArrangement = Arrangement.Center
				) {
					Icon(
						modifier = Modifier
							.clip(RoundedCornerShape(20.dp))
							.background(
								if (selectedIndex == index) {
									MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
								} else {
									colorResource(id = R.color.full_transparent)
								}
							)
							.padding(vertical = 5.dp, horizontal = 15.dp)
							.size(20.dp),
						painter = item.icon,
						contentDescription = item.text,
					)
					Spacer(modifier = Modifier.height(5.dp))
					Text(item.text)
				}//: Column
			}
		}//: Row
		/*NavigationBar(
			modifier = Modifier
				.height(80.dp)
				.fillMaxWidth()
		) {
			Row(
				modifier = Modifier.fillMaxSize(),
				verticalAlignment = Alignment.CenterVertically
			) {
				items.forEachIndexed { index, item ->
					NavigationBarItem(
						selected = selectedIndex == index,
						onClick = {
							onClick(index)
						},
						icon = {
							Icon(imageVector = item.icon, contentDescription = "Chats")
						},
						label = {
							Text(item.text)
						}
					)
				}
			}
		}//: NavigationBar*/
	}//: Card
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	BottomNavMenu(
		items = listOf(
			BottomNavItem(icon = painterResource(R.drawable.chat), text = "Chats"),
			BottomNavItem(icon = painterResource(R.drawable.status), text = "Status"),
			BottomNavItem(icon = painterResource(R.drawable.user), text = "Profile")
		),
		selectedIndex = 0
	)
}