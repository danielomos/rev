package com.revomvpandriodapp.app.network.models.createcompleted

import com.google.gson.annotations.SerializedName

data class CreateCompletedResponse(

	@field:SerializedName("metadata")
	val metadata: CreateCompletedResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: String? = null
)

data class CreateCompletedResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
