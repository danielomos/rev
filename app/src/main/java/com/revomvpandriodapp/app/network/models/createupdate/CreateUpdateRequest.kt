package com.revomvpandriodapp.app.network.models.createupdate

import com.google.gson.annotations.SerializedName

data class CreateUpdateRequest(

	@field:SerializedName("sellingPrice")
	val sellingPrice: String? = null,

	@field:SerializedName("quantity")
	val quantity: String? = null
)
