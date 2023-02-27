package com.company.baluairlines.services_feature.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.utils.Resource
import com.company.baluairlines.services_feature.data.ServicesRepository
import javax.inject.Inject

class StatusViewModel (
    private val servicesRepository: ServicesRepository
): ViewModel(){

    val flightStatus: LiveData<Resource<FlightInfo>> = servicesRepository.flightStatus

    suspend fun flightStatusWithNumber(flightNumber: String, date: Long){
        servicesRepository.flightStatusWithNumber(flightNumber, date)
    }

    suspend fun flightStatusWithAirports(){
        servicesRepository.flightStatusWithAirports()
    }
}