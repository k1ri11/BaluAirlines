package com.company.baluairlines.core.data.database

import androidx.room.*

@Dao
interface AirDao {
    @Transaction
    @Query("SELECT * FROM tickets")
    fun getTicketsWithFlights(): List<TicketsWithFlights>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTickets(tasks: List<TicketEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFlightsInfo(tasks: List<FlightInfoEntity>)

    @Query("DELETE FROM tickets")
    fun clearTicketTable()

    @Query("DELETE FROM flights")
    fun clearFlightTable()
}