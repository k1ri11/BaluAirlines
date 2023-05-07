package com.company.baluairlines.core.domain


import com.google.gson.annotations.SerializedName

data class BookingReq(
    @SerializedName("book_date")
    val bookDate: String,
    @SerializedName("book_ref")
    val bookRef: String,
    @SerializedName("tickets")
    val tickets: List<Ticket>,
    @SerializedName("total_amount")
    val totalAmount: Int
)