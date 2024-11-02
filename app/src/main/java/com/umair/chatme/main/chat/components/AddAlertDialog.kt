package com.umair.chatme.main.chat.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AddAlertDialog(
	showDialog:  MutableState<Boolean>,
	personNumber: MutableState<String>,
	onTap: () -> Unit = {}
) {
	AlertDialog(
		onDismissRequest = {
			showDialog.value = false
		},
		title = {
			Text(text = "Add Chat")
		},
		text = {
			OutlinedTextField(
				value = personNumber.value,
				onValueChange = { it ->
					personNumber.value = it
				},
				label = {
					Text("Phone No")
				},
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
			)
		},
		confirmButton = {
			Button(
				onClick = {
					onTap.invoke()
				}
			) {
				Text("Add")
			}
		},
		dismissButton = {
			Button(
				onClick = {
					showDialog.value = false
				}) {
				Text("Cancel")
			}
		}
	)
}