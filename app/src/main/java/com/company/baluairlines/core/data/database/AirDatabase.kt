package com.company.baluairlines.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.company.baluairlines.core.data.model.CityAndAirports

@Database(
    entities = [CityAndAirports::class],
    version = 1
)
abstract class AirDatabase: RoomDatabase() {

    abstract fun airDao(): AirDao
}