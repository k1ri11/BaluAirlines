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

    @GET("/flights/no/PG0404?date=2023-02-23T21%3A12%3A09.929578")

    suspend fun flightStatusWithAirports(): Response<FlightInfoReq>


    @GET("/flights/no/{flight_number}?")
    suspend fun flightStatusWithNumber(
        @Path("flight_number") flightNumber: String,
        @Query("date") date: Long
    ): Response<FlightInfoReq>

}