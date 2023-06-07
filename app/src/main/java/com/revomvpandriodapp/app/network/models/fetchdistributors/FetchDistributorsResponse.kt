package com.revomvpandriodapp.app.network.models.fetchdistributors

import com.google.gson.annotations.SerializedName

data class FetchDistributorsResponse(

	@field:SerializedName("metadata")
	val metadata: FetchDistributorsResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: FetchDistributorsResponsePayload? = null
)

data class FetchDistributorsResponsePayload(

	@field:SerializedName("distributions")
	val distributions: List<FetchDistributorsResponsePayloadDistributionsItem?>? = null
)

data class FetchDistributorsResponsePayloadDistributionsItem(

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

data class FetchDistributorsResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
