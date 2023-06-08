package com.revomvpandriodapp.app.network.models.createtoken

import com.google.gson.annotations.SerializedName

data class CreateTokenResponse(

	@field:SerializedName("metadata")
	val metadata: CreateTokenResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: CreateTokenResponsePayload? = null
)

data class CreateTokenResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class CreateTokenResponsePayload(

	@field:SerializedName("refExpiredAt")
	val refExpiredAt: Long? = null,

	@field:SerializedName("accExpiredAt")
	val accExpiredAt: Long? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

	@field:SerializedName("refreshToken")
	val refreshToken: String? = null
)
