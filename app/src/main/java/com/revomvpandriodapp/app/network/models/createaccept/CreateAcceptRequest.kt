package com.revomvpandriodapp.app.network.models.createaccept

import com.google.gson.annotations.SerializedName

data class CreateAcceptRequest(

	@field:SerializedName("orderId")
	val orderId: String? = null
)
