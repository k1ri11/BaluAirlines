package com.company.baluairlines.services_feature.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.utils.Resource
import com.company.baluairlines.services_feature.data.ServicesRepository

class StatusViewModel (
    private val servicesRepository: ServicesRepository
): ViewModel(){

    val flightStatus: LiveData<Resource<List<FlightInfo>>> = servicesRepository.flightStatus

    suspend fun getFlightStatusWithNumber(flightNumber: String, date: Long){
        servicesRepository.getFlightStatusWithNumber(flightNumber.uppercase(), date)
    }

    suspend fun getFlightStatusWithAirports(departureAirport: String, arrivalAirport: String, date: Long){
        servicesRepository.getFlightStatusWithAirports(departureAirport.uppercase(), arrivalAirport.uppercase(), date)
    }

    suspend fun getFlightTable(departureAirport: String, date: Long){
        servicesRepository.getFlightTable(departureAirport.uppercase(), date)
    }
}