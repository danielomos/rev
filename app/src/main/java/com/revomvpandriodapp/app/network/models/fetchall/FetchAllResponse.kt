package com.revomvpandriodapp.app.network.models.fetchall

import com.google.gson.annotations.SerializedName

data class FetchAllResponse(

	@field:SerializedName("metadata")
	val metadata: FetchAllResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: List<FetchAllResponsePayloadItem?>? = null
)

data class FetchAllResponsePayloadItem(

	@field:SerializedName("orderCustomerAddress")
	val orderCustomerAddress: String? = null,

	@field:SerializedName("orderAmount")
	val orderAmount: String? = null,

	@field:SerializedName("orderId")
	val orderId: String? = null,

	@field:SerializedName("pickUpKg")
	val pickUpKg: String? = null,

	@field:SerializedName("orderKg")
	val orderKg: String? = null,

	@field:SerializedName("orderCusFullName")
	val orderCusFullName: String? = null,

	@field:SerializedName("orderStatus")
	val orderStatus: String? = null,

	@field:SerializedName("orderCusPhoneNumber")
	val orderCusPhoneNumber: String? = null,

	@field:SerializedName("orderCode")
	val orderCode: String? = null,

	@field:SerializedName("orderCreatedDate")
	val orderCreatedDate: String? = null,

	@field:SerializedName("deliveryKg")
	val deliveryKg: String? = null
)

data class FetchAllResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
