package com.revomvpandriodapp.app.network.models.fetchdetails

import com.google.gson.annotations.SerializedName

data class FetchDetailsResponse(

	@field:SerializedName("metadata")
	val metadata: FetchDetailsResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: FetchDetailsResponsePayload? = null
)

data class FetchDetailsResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class FetchDetailsResponsePayload(

	@field:SerializedName("serialNumber")
	val serialNumber: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("gender")
	val gender: Int? = null,

	@field:SerializedName("defaultProfileId")
	val defaultProfileId: Int? = null,

	@field:SerializedName("serviceArea")
	val serviceArea: String? = null,

	@field:SerializedName("givenName")
	val givenName: String? = null,

	@field:SerializedName("sellerOrders")
	val sellerOrders: List<FetchDetailsResponsePayloadSellerOrdersItem?>? = null,

	@field:SerializedName("retailerId")
	val retailerId: Int? = null,

	@field:SerializedName("stockLevel")
	val stockLevel: String? = null,

	@field:SerializedName("sellingPrice")
	val sellingPrice: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("familyName")
	val familyName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("onBoarded")
	val onBoarded: Int? = null,

	@field:SerializedName("middleName")
	val middleName: String? = null,

	@field:SerializedName("userType")
	val userType: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class FetchDetailsResponsePayloadSellerOrdersItem(

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
	val orderStatus: Int? = null,

	@field:SerializedName("orderCusPhoneNumber")
	val orderCusPhoneNumber: String? = null,

	@field:SerializedName("orderCode")
	val orderCode: String? = null,

	@field:SerializedName("orderCreatedDate")
	val orderCreatedDate: String? = null,

	@field:SerializedName("deliveryKg")
	val deliveryKg: String? = null
)
