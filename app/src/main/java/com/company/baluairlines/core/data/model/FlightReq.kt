package com.company.baluairlines.core.data.model


import com.google.gson.annotations.SerializedName

data class FlightReq(
    @SerializedName("cost")
    val cost: Double,
    @SerializedName("time")
    val time: String,
    @SerializedName("flights")
    val flights: List<FlightInfoReq>,
)