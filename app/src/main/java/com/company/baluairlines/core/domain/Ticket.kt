package com.company.baluairlines.core.domain


import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("book_ref")
    val bookRef: String,
    @SerializedName("contact_data")
    val contactData: ContactData,
    @SerializedName("passenger_id")
    val passengerId: String,
    @SerializedName("passenger_name")
    val passengerName: String,
    @SerializedName("ticket_no")
    val ticketNo: String
)