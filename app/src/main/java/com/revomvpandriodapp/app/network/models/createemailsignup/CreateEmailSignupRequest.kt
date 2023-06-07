package com.revomvpandriodapp.app.network.models.createemailsignup

import com.google.gson.annotations.SerializedName

data class CreateEmailSignupRequest(

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("shopName")
	val shopName: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
