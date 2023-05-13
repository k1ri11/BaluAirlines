package com.company.baluairlines.core.domain
data class Ticket(
    val bookRef: String,
    val contactData: ContactData,
    val flight: List<FlightInfo>,
    val passengerId: String,
    val passengerName: String,
    val ticketNo: String
)