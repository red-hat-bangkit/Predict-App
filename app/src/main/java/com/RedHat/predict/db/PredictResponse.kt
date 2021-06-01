package com.RedHat.predict.db

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("PredictResponse")
	val predictResponse: List<PredictResponseItem>
)

data class Location(

	@field:SerializedName("city")
	val city: Any,

	@field:SerializedName("lat_long")
	val latLong: Any,

	@field:SerializedName("name")
	val name: String
)

data class PredictResponseItem(

	@field:SerializedName("reason")
	val reason: String,

	@field:SerializedName("is_bencana")
	val isBencana: Boolean,

	@field:SerializedName("bencana")
	val bencana: String,

	@field:SerializedName("confidence")
	val confidence: Any,

	@field:SerializedName("rmse")
	val rmse: Double,

	@field:SerializedName("location")
	val location: Location,

	@field:SerializedName("time")
	val time: String
)
