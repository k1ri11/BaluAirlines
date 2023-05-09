package com.company.baluairlines.core.data.model


import com.google.gson.annotations.SerializedName

data class BookingReq(
    @SerializedName("book_date")
    val bookDate: String,
    @SerializedName("book_ref")
    val bookRef: String,
    @SerializedName("tickets")
    val tickets: List<TicketReq>,
    @SerializedName("total_amount")
    val totalAmount: Int
)