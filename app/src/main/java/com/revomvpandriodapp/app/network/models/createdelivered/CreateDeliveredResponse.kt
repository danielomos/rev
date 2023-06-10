package com.revomvpandriodapp.app.network.models.createdelivered

import com.google.gson.annotations.SerializedName

data class CreateDeliveredResponse(

	@field:SerializedName("metadata")
	val metadata: CreateDeliveredResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: CreateDeliveredResponsePayload? = null
)

data class CreateDeliveredResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class CreateDeliveredResponsePayload(

	@field:SerializedName("message")
	val message: String? = null
)
