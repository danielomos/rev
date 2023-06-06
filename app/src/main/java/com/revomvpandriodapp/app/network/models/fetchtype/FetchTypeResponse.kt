package com.revomvpandriodapp.app.network.models.fetchtype

import com.google.gson.annotations.SerializedName

data class FetchTypeResponse(

	@field:SerializedName("metadata")
	val metadata: FetchTypeResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: FetchTypeResponsePayload? = null
)

data class FetchTypeResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class FetchTypeResponsePayload(

	@field:SerializedName("defaultProfileId")
	val defaultProfileId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("userType")
	val userType: String? = null
)
