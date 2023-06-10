package com.revomvpandriodapp.app.network.models.createemailsignup1

import com.google.gson.annotations.SerializedName

data class CreateEmailSignup1Request(

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
