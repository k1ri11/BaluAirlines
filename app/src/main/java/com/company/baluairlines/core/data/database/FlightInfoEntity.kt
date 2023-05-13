package com.company.baluairlines.core.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "flights",
    indices = [Index(value = ["flight_id"], unique = true)]
)
data class FlightInfoEntity(
    @ColumnInfo("ticket_number")
    val ticketNumber: String,
    @PrimaryKey
    @ColumnInfo("flight_id")
    val flightId: Int,
    @ColumnInfo("flight_number")
    val flightNo: String,
    @ColumnInfo("scheduled_arrival")
    val scheduledArrival: Date,
    @ColumnInfo("scheduled_departure")
    val scheduledDeparture: Date,
    @ColumnInfo("arrival_airport")
    val arrivalAirport: String,
    @ColumnInfo("departure_airport")
    val departureAirport: String,
    @ColumnInfo("aircraft_code")
    val aircraftCode: String,
)
