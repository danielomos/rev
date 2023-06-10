package com.revomvpandriodapp.app.network.models.createsetup

import com.google.gson.annotations.SerializedName

data class CreateSetupResponse(

	@field:SerializedName("metadata")
	val metadata: CreateSetupResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: String? = null
)

data class CreateSetupResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
