package com.company.baluairlines.core.data.model


import com.company.baluairlines.core.domain.ContactData
import com.google.gson.annotations.SerializedName

data class TicketReq(
    @SerializedName("book_ref")
    val bookRef: String,
    @SerializedName("contact_data")
    val contactData: ContactData,
    @SerializedName("flight")
    val flight: List<FlightInfoReq>?,
    @SerializedName("passenger_id")
    val passengerId: String,
    @SerializedName("passenger_name")
    val passengerName: String,
    @SerializedName("ticket_no")
    val ticketNo: String
)