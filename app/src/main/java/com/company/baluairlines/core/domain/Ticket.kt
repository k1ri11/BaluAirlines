package com.company.baluairlines.core.domain


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("book_ref")
    @ColumnInfo("book_ref")
    val bookRef: String,
    @SerializedName("contact_data")
    @ColumnInfo("contact_data")
    val contactData: ContactData,
    @SerializedName("flight")
    @ColumnInfo("flight")
    val flight: List<FlightInfo>,
    @SerializedName("passenger_id")
    @ColumnInfo("passenger_id")
    val passengerId: String,
    @SerializedName("passenger_name")
    @ColumnInfo("passenger_name")
    val passengerName: String,
    @SerializedName("ticket_no")
    @ColumnInfo("ticket_no")
    val ticketNo: String
)