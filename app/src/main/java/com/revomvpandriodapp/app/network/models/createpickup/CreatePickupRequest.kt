package com.revomvpandriodapp.app.network.models.createpickup

import com.google.gson.annotations.SerializedName

data class CreatePickupRequest(

	@field:SerializedName("pickUpKg")
	val pickUpKg: String? = null,

	@field:SerializedName("orderId")
	val orderId: String? = null
)
