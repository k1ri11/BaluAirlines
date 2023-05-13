package com.company.baluairlines.core.domain

data class Booking(
    val bookDate: String,
    val bookRef: String,
    val tickets: List<Ticket>,
    val totalAmount: Int
)
