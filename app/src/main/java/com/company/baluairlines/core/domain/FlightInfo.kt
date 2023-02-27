package com.company.baluairlines.core.domain


import java.util.*

data class FlightInfo(
    val flightNo: String,
    val scheduledArrival: Date,
    val scheduledDeparture: Date,
    val arrivalAirport: String,
    val departureAirport: String,
    val status: String,
    val aircraftCode: String,
    val actualArrival: Date? = null,
    val actualDeparture: Date? = null,
)

