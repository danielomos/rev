package com.revomvpandriodapp.app.network.models.createtoken

import com.google.gson.annotations.SerializedName

data class CreateTokenRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("rememberMe")
	val rememberMe: Boolean? = null,

	@field:SerializedName("username")
	val username: String? = null
)
