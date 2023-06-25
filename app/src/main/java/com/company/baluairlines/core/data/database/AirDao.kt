package com.company.baluairlines.core.data.database

import androidx.room.*

@Dao
interface AirDao {
    @Transaction
    @Query("SELECT * FROM tickets")
    fun getTicketsWithFlights(): List<TicketsWithFlights>

    @Query("SELECT * FROM routes")
    fun getRoutes(): List<Route>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTickets(tickets: List<TicketEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFlightsInfo(flights: List<FlightInfoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRoute(route: Route)

    @Query("DELETE FROM routes")
    fun clearRoutes()

    @Query("DELETE FROM tickets")
    fun clearTicketTable()

    @Query("DELETE FROM flights")
    fun clearFlightTable()
}