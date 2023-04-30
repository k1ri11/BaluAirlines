package com.company.baluairlines.services_feature.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.utils.Resource
import com.company.baluairlines.services_feature.data.ServicesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Содержит основную логику получения информации о полетах различными способами
 * @property flightStatus хранит результат запроса flights, публичное для подписки
 * @param servicesRepository [ServicesRepository]
 */
class StatusViewModel(
    private val servicesRepository: ServicesRepository
) : ViewModel() {

    val flightStatus: LiveData<Resource<List<FlightInfo>>> = servicesRepository.flightStatus

    /**
     * запрос статуса рейса по померу и дате полета
     * @param flightNumber номер самолета
     * @param date дата полета
     */
    fun getFlightStatusWithNumber(flightNumber: String, date: Long) =
        viewModelScope.launch(Dispatchers.IO) {
            servicesRepository.getFlightStatusWithNumber(flightNumber.uppercase(), date)
        }

    /**
     * запрос статуса рейса по аэропорту вылета, прилета и дате полета
     * @param departureAirport аэропорт вылета
     * @param arrivalAirport аэропорт прилета
     * @param date дата полета
     */
    fun getFlightStatusWithAirports(departureAirport: String, arrivalAirport: String, date: Long) =
        viewModelScope.launch(Dispatchers.IO) {
        servicesRepository.getFlightStatusWithAirports(
            departureAirport.uppercase(),
            arrivalAirport.uppercase(),
            date
        )
    }

    /**
     * запрос на получение списка рейсов из аэропорта на выбранную дату
     * @param departureAirport аэропорт вылета
     * @param date дата полета
     */
    fun getFlightTable(departureAirport: String, date: Long) = viewModelScope.launch(Dispatchers.IO){
        servicesRepository.getFlightTable(departureAirport.uppercase(), date)
    }
}