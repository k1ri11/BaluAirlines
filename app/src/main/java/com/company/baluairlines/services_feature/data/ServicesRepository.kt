package com.company.baluairlines.services_feature.data

import androidx.lifecycle.LiveData
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.utils.Resource

interface ServicesRepository {

    val flightStatus: LiveData<Resource<FlightInfo>>

    suspend fun flightStatusWithNumber(flightNumber: String, date: Long)

    suspend fun flightStatusWithAirports()
}