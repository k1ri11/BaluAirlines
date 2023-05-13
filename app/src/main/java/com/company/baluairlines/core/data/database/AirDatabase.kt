package com.company.baluairlines.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [TicketEntity::class, FlightInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AirDatabase: RoomDatabase() {

    abstract fun airDao(): AirDao
}