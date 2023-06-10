package com.revomvpandriodapp.app.network.models.fetchid1

import com.google.gson.annotations.SerializedName

data class FetchId1Response(

	@field:SerializedName("metadata")
	val metadata: FetchId1ResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: FetchId1ResponsePayload? = null
)

data class FetchId1ResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class FetchId1ResponsePayload(

	@field:SerializedName("cusOrderId")
	val cusOrderId: String? = null,

	@field:SerializedName("orderAmount")
	val orderAmount: String? = null,

	@field:SerializedName("pickUpKg")
	val pickUpKg: String? = null,

	@field:SerializedName("orderKg")
	val orderKg: String? = null,

	@field:SerializedName("orderSelPhoneNumber")
	val orderSelPhoneNumber: String? = null,

	@field:SerializedName("orderSelFullName")
	val orderSelFullName: String? = null,

	@field:SerializedName("orderStatus")
	val orderStatus: String? = null,

	@field:SerializedName("orderSelAddress")
	val orderSelAddress: String? = null,

	@field:SerializedName("orderCode")
	val orderCode: String? = null,

	@field:SerializedName("orderCreatedDate")
	val orderCreatedDate: String? = null,

	@field:SerializedName("deliveryKg")
	val deliveryKg: String? = null,

	@field:SerializedName("orderSellerShopName")
	val orderSellerShopName: String? = null
)
