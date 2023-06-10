package com.revomvpandriodapp.app.network.models.createemailsignup

import com.google.gson.annotations.SerializedName

data class CreateEmailSignupResponse(

	@field:SerializedName("metadata")
	val metadata: CreateEmailSignupResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: CreateEmailSignupResponsePayload? = null
)

data class CreateEmailSignupResponsePayload(

	@field:SerializedName("accountId")
	val accountId: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)

data class CreateEmailSignupResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
