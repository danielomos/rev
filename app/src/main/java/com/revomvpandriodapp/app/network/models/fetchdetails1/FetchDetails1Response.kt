package com.revomvpandriodapp.app.network.models.fetchdetails1

import com.google.gson.annotations.SerializedName

data class FetchDetails1Response(

	@field:SerializedName("metadata")
	val metadata: FetchDetails1ResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: FetchDetails1ResponsePayload? = null
)

data class FetchDetails1ResponsePayload(

	@field:SerializedName("serialNumber")
	val serialNumber: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("gender")
	val gender: Int? = null,

	@field:SerializedName("defaultProfileId")
	val defaultProfileId: Int? = null,

	@field:SerializedName("givenName")
	val givenName: String? = null,

	@field:SerializedName("sellerName")
	val sellerName: Any? = null,

	@field:SerializedName("individualId")
	val individualId: Int? = null,

	@field:SerializedName("sellersPrice")
	val sellersPrice: String? = null,

	@field:SerializedName("manage")
	val manage: Int? = null,

	@field:SerializedName("customerOrders")
	val customerOrders: List<FetchDetails1ResponsePayloadCustomerOrdersItem?>? = null,

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

data class FetchDetails1ResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class FetchDetails1ResponsePayloadCustomerOrdersItem(

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
