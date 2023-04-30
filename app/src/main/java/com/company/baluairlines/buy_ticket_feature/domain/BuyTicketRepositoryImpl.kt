package com.company.baluairlines.buy_ticket_feature.domain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.baluairlines.buy_ticket_feature.data.BuyTicketRepository
import com.company.baluairlines.core.data.AirApi
import com.company.baluairlines.core.di.ApplicationScope
import com.company.baluairlines.core.domain.CostItem
import com.company.baluairlines.core.domain.Flight
import com.company.baluairlines.core.utils.NetworkUtils
import com.company.baluairlines.core.utils.Resource
import com.company.baluairlines.core.utils.handleFlightListNetworkResponse
import com.company.myapplication.R
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ApplicationScope
class BuyTicketRepositoryImpl @Inject constructor(
    private val api: AirApi,
    private val context: Context,
    private val networkUtils: NetworkUtils,
) : BuyTicketRepository {

    private val _searchResult = MutableLiveData<Resource<List<Flight>>>()
    override val searchResult: LiveData<Resource<List<Flight>>> = _searchResult

    private val _costs = MutableLiveData<Resource<List<CostItem>>>()
    override val costs: LiveData<Resource<List<CostItem>>> = _costs

    private val DAY_DURATION = 86400000

    private val costDateFormatter = SimpleDateFormat("E, dd MMM", Locale.ROOT)
    private val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
    override suspend fun getFlights(
        date: Long, departureAirport: String, arrivalAirport: String, maxTransits: Int,
        serviceClass: String, passengers: Int
    ) {
        _searchResult.postValue(Resource.Loading())
        if (networkUtils.hasInternetConnection()) {
            val response = api.getFlights(
                date, departureAirport, arrivalAirport, maxTransits,
                serviceClass, passengers
            )
            val result = handleFlightListNetworkResponse(response, context)
            _searchResult.postValue(result)
        } else {
            _searchResult.postValue(Resource.Error(context.resources.getString(R.string.no_internet_connection)))
        }
    }

    override suspend fun getFlightCosts(
        date: Long, departureAirport: String, arrivalAirport: String, maxTransits: Int,
        serviceClass: String, passengers: Int
    ) {
        _costs.postValue(Resource.Loading())
        if (networkUtils.hasInternetConnection()) {
            val response = api.getFlightCosts(
                date, departureAirport, arrivalAirport, maxTransits,
                serviceClass, passengers
            )
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    val costs = body.mapIndexed() { index, cost ->
                        val currentDate = date + index * DAY_DURATION
                        CostItem(
                            costDateFormatter.format(currentDate),
                            formatter.format(currentDate),
                            cost.toInt(), passengers.toString()
                        )
                    }
                    _costs.postValue(Resource.Success(costs))
                    return
                }
            }
            when (response.code()) {
                400 -> _costs.postValue(Resource.Error(context.resources.getString(R.string.incorrect_request)))
                401 -> _costs.postValue(Resource.Error(context.resources.getString(R.string.incorrect_authorization)))
                404 -> _costs.postValue(Resource.Error(context.resources.getString(R.string.element_not_found)))
                else -> _costs.postValue(Resource.Error(context.resources.getString(R.string.server_error)))
            }
        } else {
            _costs.postValue(Resource.Error(context.resources.getString(R.string.no_internet_connection)))
        }
    }
}