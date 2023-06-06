package com.revomvpandriodapp.app.network.models.fetchareas

import com.google.gson.annotations.SerializedName

data class FetchAreasResponse(

	@field:SerializedName("metadata")
	val metadata: FetchAreasResponseMetadata? = null,

	@field:SerializedName("payload")
	val payload: List<FetchAreasResponsePayloadItem?>? = null
)

data class FetchAreasResponsePayloadItem(

	@field:SerializedName("areaCode")
	val areaCode: Int? = null,

	@field:SerializedName("areaId")
	val areaId: String? = null,

	@field:SerializedName("areaName")
	val areaName: String? = null
)

data class FetchAreasResponseMetadata(

	@field:SerializedName("traceId")
	val traceId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)
