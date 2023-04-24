package com.company.baluairlines.services_feature.domain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.baluairlines.core.data.AirApi
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.utils.NetworkUtils
import com.company.baluairlines.core.utils.Resource
import com.company.baluairlines.core.utils.handleListNetworkResponse
import com.company.baluairlines.core.utils.handleSingleNetworkResponse
import com.company.baluairlines.services_feature.data.ServicesRepository
import com.company.myapplication.R
import javax.inject.Inject

/**
 * Содержит основную логику получения информации о полетах различными способами
 * @property _flightStatus хранит результат запроса полета, приватное, изменяется только внутри класса
 * @property flightStatus хранит результат запроса flights, публичное для подписки
 * @param api [AirApi]
 * @param context [Context]
 * @param networkUtils [NetworkUtils]
 */

//TOdo scope
class ServicesRepositoryImpl @Inject constructor(
    private val api: AirApi,
    private val context: Context,
    private val networkUtils: NetworkUtils,
) : ServicesRepository {

    private val _flightStatus = MutableLiveData<Resource<List<FlightInfo>>>()
    override val flightStatus: LiveData<Resource<List<FlightInfo>>> = _flightStatus

    /**
     * запрос статуса рейса по померу и дате полета
     * @param flightNumber номер самолета
     * @param date дата полета
     */
    override suspend fun getFlightStatusWithNumber(flightNumber: String, date: Long) {
        _flightStatus.postValue(Resource.Loading())
        if (networkUtils.hasInternetConnection()) {
            val response = api.flightStatusWithNumber(flightNumber, date)
            val result = handleSingleNetworkResponse(response, context)
            _flightStatus.postValue(result)
        } else {
            _flightStatus.postValue(Resource.Error(context.resources.getString(R.string.no_internet_connection)))
        }
    }

    /**
     * запрос статуса рейса по аэропорту вылета, прилета и дате полета
     * @param departureAirport аэропорт вылета
     * @param arrivalAirport аэропорт прилета
     * @param date дата полета
     */
    override suspend fun getFlightStatusWithAirports(
        departureAirport: String,
        arrivalAirport: String,
        date: Long
    ) {
        _flightStatus.postValue(Resource.Loading())
        if (networkUtils.hasInternetConnection()) {
            val response = api.flightStatusWithAirports(
                departureAirport = departureAirport,
                arrivalAirport = arrivalAirport,
                date = date
            )
            val result = handleSingleNetworkResponse(response, context)
            _flightStatus.postValue(result)
        } else {
            _flightStatus.postValue(Resource.Error(context.resources.getString(R.string.no_internet_connection)))
        }
    }

    /**
     * запрос на получение списка рейсов из аэропорта на выбранную дату
     * @param departureAirport аэропорт вылета
     * @param date дата полета
     */
    override suspend fun getFlightTable(departureAirport: String, date: Long) {
        _flightStatus.postValue(Resource.Loading())
        if (networkUtils.hasInternetConnection()) {
            val response = api.getFlightTable(departureAirport = departureAirport, date = date)
            val result = handleListNetworkResponse(response, context)
            _flightStatus.postValue(result)
        } else {
            _flightStatus.postValue(Resource.Error(context.resources.getString(R.string.no_internet_connection)))
        }
    }

}