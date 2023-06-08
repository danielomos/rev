package com.revomvpandriodapp.app.network.models.createmobiletokenverification

import com.google.gson.annotations.SerializedName

data class CreateMobileTokenVerificationRequest(

	@field:SerializedName("token")
	val token: String? = null
)
