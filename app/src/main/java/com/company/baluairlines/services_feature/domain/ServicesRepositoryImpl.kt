package com.company.baluairlines.services_feature.domain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.baluairlines.core.data.AirApi
import com.company.baluairlines.core.data.mappers.toFlightInfo
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.utils.NetworkUtils
import com.company.baluairlines.core.utils.Resource
import com.company.baluairlines.core.utils.handleNetworkResponse
import com.company.baluairlines.services_feature.data.ServicesRepository
import com.company.myapplication.R
import javax.inject.Inject

//TOdo scope
class ServicesRepositoryImpl @Inject constructor(
    private val api: AirApi,
    private val context: Context,
    private val networkUtils: NetworkUtils,
) : ServicesRepository {

    private val _flightStatus = MutableLiveData<Resource<FlightInfo>>()
    override val flightStatus: LiveData<Resource<FlightInfo>> = _flightStatus


    override suspend fun flightStatusWithNumber(flightNumber: String, date: Long) {
        _flightStatus.postValue(Resource.Loading())
        if (networkUtils.hasInternetConnection()) {
            val response = api.flightStatusWithNumber(flightNumber, date)
            val result = handleNetworkResponse(response, context)
            _flightStatus.postValue(result)
        } else {
            _flightStatus.postValue(Resource.Error(context.resources.getString(R.string.no_internet_connection)))
        }
    }


    override suspend fun flightStatusWithAirports() {
        _flightStatus.postValue(Resource.Loading())
        if (networkUtils.hasInternetConnection()) {
            val response = api.flightStatusWithAirports()
            val result = handleNetworkResponse(response, context)
            _flightStatus.postValue(result)
        } else {
            _flightStatus.postValue(Resource.Error(context.resources.getString(R.string.no_internet_connection)))
        }
    }
}