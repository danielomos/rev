package com.revomvpandriodapp.app.network.models.createdelivered

import com.google.gson.annotations.SerializedName

data class CreateDeliveredRequest(

	@field:SerializedName("orderId")
	val orderId: String? = null
)
