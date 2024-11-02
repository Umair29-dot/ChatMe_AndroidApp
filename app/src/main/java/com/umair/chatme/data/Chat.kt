package com.umair.chatme.data

data class Chat (
	val chatID: String,
	val currentUser: User,
	val chatUser: ChatUser
) {
	constructor(): this(chatID = "", currentUser = User(), chatUser = ChatUser())
}