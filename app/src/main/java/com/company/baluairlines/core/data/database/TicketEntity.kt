package com.company.baluairlines.core.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tickets",
    indices = [Index(value = ["ticket_number"], unique = true)]
)
data class TicketEntity(
    @PrimaryKey
    @ColumnInfo("ticket_number")
    val ticketNumber: String,
    @ColumnInfo("book_ref")
    val bookRef: String,
    @ColumnInfo("passenger_name")
    val passengerName: String
)
