package com.company.baluairlines.core.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "routes",
    indices = [Index(value = ["departure_airport", "arrival_airport"], unique = true)]
)
data class Route(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "departure_airport")
    val departureAirport: String,
    @ColumnInfo(name = "arrival_airport")
    val arrivalAirport: String
)