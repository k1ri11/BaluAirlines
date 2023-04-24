package com.company.baluairlines.services_feature.data

import androidx.lifecycle.LiveData
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.utils.Resource

/**
 * Содержит основную логику получения информации о полетах различными способами
 * @property flightStatus хранит результат запроса flights, публичное для подписки
 */
interface ServicesRepository {

    val flightStatus: LiveData<Resource<List<FlightInfo>>>

    /**
     * запрос статуса рейса по померу и дате полета
     * @param flightNumber номер самолета
     * @param date дата полета
     */
    suspend fun getFlightStatusWithNumber(flightNumber: String, date: Long)

    /**
     * запрос статуса рейса по аэропорту вылета, прилета и дате полета
     * @param departureAirport аэропорт вылета
     * @param arrivalAirport аэропорт прилета
     * @param date дата полета
     */
    suspend fun getFlightStatusWithAirports(departureAirport: String, arrivalAirport: String, date: Long)

    /**
     * запрос на получение списка рейсов из аэропорта на выбранную дату
     * @param departureAirport аэропорт вылета
     * @param date дата полета
     */
    suspend fun getFlightTable(departureAirport: String, date: Long)
}