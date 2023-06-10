package com.revomvpandriodapp.app.network.models.createcompleted

import com.google.gson.annotations.SerializedName

data class CreateCompletedRequest(

	@field:SerializedName("orderId")
	val orderId: String? = null
)
