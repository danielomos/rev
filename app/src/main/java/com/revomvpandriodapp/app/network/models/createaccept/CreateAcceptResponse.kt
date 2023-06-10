package com.revomvpandriodapp.app.network.models.createaccept

import com.google.gson.annotations.SerializedName

data class CreateAcceptResponse(

	@field:SerializedName("metadata")
	val metadata: CreateAcceptResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: CreateAcceptResponsePayload? = null
)

data class CreateAcceptResponsePayload(

	@field:SerializedName("message")
	val message: String? = null
)

data class CreateAcceptResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
