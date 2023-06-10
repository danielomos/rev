package com.revomvpandriodapp.app.network.models.createtotal

import com.google.gson.annotations.SerializedName

data class CreateTotalRequest(

	@field:SerializedName("refillingQuantity")
	val refillingQuantity: String? = null,

	@field:SerializedName("sellerId")
	val sellerId: String? = null
)
