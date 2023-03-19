package com.company.baluairlines.services_feature.domain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.baluairlines.core.data.AirApi
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.utils.*
import com.company.baluairlines.services_feature.data.ServicesRepository
import com.company.myapplication.R
import javax.inject.Inject

//TOdo scope
class ServicesRepositoryImpl @Inject constructor(
    private val api: AirApi,
    private val context: Context,
    private val networkUtils: NetworkUtils,
) : ServicesRepository {

    private val _flightStatus = MutableLiveData<Resource<List<FlightInfo>>>()
    override val flightStatus: LiveData<Resource<List<FlightInfo>>> = _flightStatus

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