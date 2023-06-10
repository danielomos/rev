package com.revomvpandriodapp.app.network.models.createrefill

import com.google.gson.annotations.SerializedName

data class CreateRefillResponse(

	@field:SerializedName("metadata")
	val metadata: CreateRefillResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: String? = null
)

data class CreateRefillResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
