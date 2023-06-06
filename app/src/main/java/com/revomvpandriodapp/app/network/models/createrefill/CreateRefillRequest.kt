package com.revomvpandriodapp.app.network.models.createrefill

import com.google.gson.annotations.SerializedName

data class CreateRefillRequest(

	@field:SerializedName("sellerId")
	val sellerId: String? = null,

	@field:SerializedName("requestedQuantity")
	val requestedQuantity: String? = null,

	@field:SerializedName("preferredDeliveryDate")
	val preferredDeliveryDate: String? = null,

	@field:SerializedName("paymentType")
	val paymentType: Int? = null
)
