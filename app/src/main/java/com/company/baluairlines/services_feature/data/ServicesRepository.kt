package com.company.baluairlines.services_feature.data

import androidx.lifecycle.LiveData
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.utils.Resource

interface ServicesRepository {

    val flightStatus: LiveData<Resource<List<FlightInfo>>>

    suspend fun getFlightStatusWithNumber(flightNumber: String, date: Long)

    suspend fun getFlightStatusWithAirports(departureAirport: String, arrivalAirport: String, date: Long)

    suspend fun getFlightTable(departureAirport: String, date: Long)
}