package com.revomvpandriodapp.app.network.models.createupdate

import com.google.gson.annotations.SerializedName

data class CreateUpdateResponse(

	@field:SerializedName("metadata")
	val metadata: CreateUpdateResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: String? = null
)

data class CreateUpdateResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
