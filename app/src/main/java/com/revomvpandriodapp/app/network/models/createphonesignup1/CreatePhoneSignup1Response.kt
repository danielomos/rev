package com.revomvpandriodapp.app.network.models.createphonesignup1

import com.google.gson.annotations.SerializedName

data class CreatePhoneSignup1Response(

	@field:SerializedName("metadata")
	val metadata: CreatePhoneSignup1ResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: CreatePhoneSignup1ResponsePayload? = null
)

data class CreatePhoneSignup1ResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class CreatePhoneSignup1ResponsePayload(

	@field:SerializedName("accountId")
	val accountId: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)
