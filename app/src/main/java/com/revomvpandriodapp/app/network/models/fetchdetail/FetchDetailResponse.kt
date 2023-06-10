package com.revomvpandriodapp.app.network.models.fetchdetail

import com.google.gson.annotations.SerializedName

data class FetchDetailResponse(

	@field:SerializedName("metadata")
	val metadata: FetchDetailResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: FetchDetailResponsePayload? = null
)

data class FetchDetailResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class FetchDetailResponsePayload(

	@field:SerializedName("sellerId")
	val sellerId: String? = null,

	@field:SerializedName("sellerName")
	val sellerName: String? = null,

	@field:SerializedName("sellerFullName")
	val sellerFullName: String? = null,

	@field:SerializedName("sellerAddress")
	val sellerAddress: String? = null,

	@field:SerializedName("sellerDeliveryCharge")
	val sellerDeliveryCharge: String? = null,

	@field:SerializedName("sellerSellingPrice")
	val sellerSellingPrice: String? = null
)
