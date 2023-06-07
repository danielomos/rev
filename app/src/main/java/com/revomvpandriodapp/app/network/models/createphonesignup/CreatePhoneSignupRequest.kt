package com.revomvpandriodapp.app.network.models.createphonesignup

import com.google.gson.annotations.SerializedName

data class CreatePhoneSignupRequest(

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("shopName")
	val shopName: String? = null
)
