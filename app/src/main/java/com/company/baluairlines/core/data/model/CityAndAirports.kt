package com.company.baluairlines.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "city_and_airport"
)
data class CityAndAirports(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val city: String,
    val airport: String
)
