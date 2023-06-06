package com.revomvpandriodapp.app.network.models.createsetup

import com.google.gson.annotations.SerializedName

data class CreateSetupRequest(

	@field:SerializedName("sellingPrice")
	val sellingPrice: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("deliveringPrice")
	val deliveringPrice: String? = null,

	@field:SerializedName("serviceAreaId")
	val serviceAreaId: String? = null,

	@field:SerializedName("stockLevel")
	val stockLevel: String? = null
)
