package com.company.baluairlines.core.data

import com.company.baluairlines.core.data.model.FlightInfoReq
import com.company.baluairlines.core.data.model.FlightReq
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AirApi {

    @GET("/flights?departure_date=2017-05-01 12%3a25%3a00%2b03&max_transits=3")
    suspend fun getFlights(): Response<ArrayList<FlightReq>>

    @GET("/flights/path/{departure_airport}/{arrival_airport}")
    suspend fun flightStatusWithAirports(
        @Path("departure_airport") departureAirport: String,
        @Path("arrival_airport") arrivalAirport: String,
        @Query("date") date: Long
    ): Response<FlightInfoReq>
    @GET("/flights/table/{departure_airport}")
    suspend fun getFlightTable(
        @Path("departure_airport") departureAirport: String,
        @Query("departure_date") date: Long
    ): Response<List<FlightInfoReq>>


    @GET("/flights/no/{flight_number}?")
    suspend fun flightStatusWithNumber(
        @Path("flight_number") flightNumber: String,
        @Query("date") date: Long
    ): Response<FlightInfoReq>

}