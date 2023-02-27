package com.company.baluairlines.core.domain


data class Flight(
    val cost: Double,
    val time: String,
    val flights: List<FlightInfo>,
)