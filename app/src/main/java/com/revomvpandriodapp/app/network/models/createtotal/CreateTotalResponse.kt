package com.revomvpandriodapp.app.network.models.createtotal

import com.google.gson.annotations.SerializedName

data class CreateTotalResponse(

	@field:SerializedName("metadata")
	val metadata: CreateTotalResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: String? = null
)

data class CreateTotalResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
