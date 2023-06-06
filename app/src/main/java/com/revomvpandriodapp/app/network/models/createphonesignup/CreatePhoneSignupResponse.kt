package com.revomvpandriodapp.app.network.models.createphonesignup

import com.google.gson.annotations.SerializedName

data class CreatePhoneSignupResponse(

	@field:SerializedName("metadata")
	val metadata: CreatePhoneSignupResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: CreatePhoneSignupResponsePayload? = null
)

data class CreatePhoneSignupResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class CreatePhoneSignupResponsePayload(

	@field:SerializedName("accountId")
	val accountId: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)
