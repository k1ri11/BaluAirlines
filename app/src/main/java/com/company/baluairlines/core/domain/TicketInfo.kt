package com.company.baluairlines.core.domain


import com.google.gson.annotations.SerializedName

data class TicketInfo(
    @SerializedName("fare_condition")
    val fareCondition: String,
    @SerializedName("flights")
    val flights: List<Int>,
    @SerializedName("passengers")
    val passengers: List<Passenger>
)