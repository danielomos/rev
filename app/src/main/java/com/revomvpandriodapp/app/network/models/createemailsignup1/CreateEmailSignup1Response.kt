package com.revomvpandriodapp.app.network.models.createemailsignup1

import com.google.gson.annotations.SerializedName

data class CreateEmailSignup1Response(

	@field:SerializedName("metadata")
	val metadata: CreateEmailSignup1ResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: CreateEmailSignup1ResponsePayload? = null
)

data class CreateEmailSignup1ResponsePayload(

	@field:SerializedName("accountId")
	val accountId: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)

data class CreateEmailSignup1ResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
