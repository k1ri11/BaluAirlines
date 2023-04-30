package com.company.baluairlines.core.domain

data class FlightUIState(
    var date: String,
    val departureAirport: String,
    val arrivalAirport: String,
    val serviceClass: String = "Economy",
    val maxTransits: Int = 2,
    val passengers: Int = 1
)
