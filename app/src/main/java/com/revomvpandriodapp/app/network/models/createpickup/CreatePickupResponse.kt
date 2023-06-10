package com.revomvpandriodapp.app.network.models.createpickup

import com.google.gson.annotations.SerializedName

data class CreatePickupResponse(

	@field:SerializedName("metadata")
	val metadata: CreatePickupResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: CreatePickupResponsePayload? = null
)

data class CreatePickupResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class CreatePickupResponsePayload(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("deliveryKg")
	val deliveryKg: String? = null
)
