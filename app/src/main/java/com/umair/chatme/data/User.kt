package com.umair.chatme.data

data class User(
	val uid: String,
	val userName: String,
	val email: String,
	val phoneNo: String,
	val profilePhoto: String? = null,
	val isOnline: Boolean
) {
	constructor(): this(uid = "0", userName = "", email = "", phoneNo = "", profilePhoto = null, isOnline = false)
}
