package com.revomvpandriodapp.app.network.models.createmobiletokenverification

import com.google.gson.annotations.SerializedName

data class CreateMobileTokenVerificationResponse(

	@field:SerializedName("metadata")
	val metadata: CreateMobileTokenVerificationResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: CreateMobileTokenVerificationResponsePayload? = null
)

data class CreateMobileTokenVerificationResponsePayload(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)

data class CreateMobileTokenVerificationResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
