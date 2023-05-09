package com.company.baluairlines.core.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "bookings"
)
data class Booking(
    @SerializedName("book_date")
    @ColumnInfo("book_date")
    val bookDate: String,
    @PrimaryKey
    @SerializedName("book_ref")
    @ColumnInfo("book_ref")
    val bookRef: String,
    @SerializedName("tickets")
    @ColumnInfo("tickets")
    val tickets: List<Ticket>,
    @SerializedName("total_amount")
    @ColumnInfo("total_amount")
    val totalAmount: Int
)
