package com.company.baluairlines.buy_ticket_feature.domain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.baluairlines.buy_ticket_feature.data.BuyTicketRepository
import com.company.baluairlines.core.data.AirApi
import com.company.baluairlines.core.data.database.AirDao
import com.company.baluairlines.core.data.database.Route
import com.company.baluairlines.core.data.mappers.toFlightInfoEntityList
import com.company.baluairlines.core.data.mappers.toTicketEntityList
import com.company.baluairlines.core.di.ApplicationScope
import com.company.baluairlines.core.domain.Booking
import com.company.baluairlines.core.domain.CostItem
import com.company.baluairlines.core.domain.Flight
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.domain.TicketInfo
import com.company.baluairlines.core.utils.NetworkUtils
import com.company.baluairlines.core.utils.Resource
import com.company.baluairlines.core.utils.handleBookingResponse
import com.company.baluairlines.core.utils.handleFlightListNetworkResponse
import com.company.myapplication.R
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@ApplicationScope
class BuyTicketRepositoryImpl @Inject constructor(
    private val dao: AirDao,
    private val api: AirApi,
    private val context: Context,
    private val networkUtils: NetworkUtils,
) : BuyTicketRepository {

    private val _searchResult = MutableLiveData<Resource<List<Flight>>>()
    override val searchResult: LiveData<Resource<List<Flight>>> = _searchResult

    private val _costs = MutableLiveData<Resource<List<CostItem>>>()
    override val costs: LiveData<Resource<List<CostItem>>> = _costs

    private val _bookingStatus = MutableLiveData<Resource<Booking>>()
    override val bookingStatus: LiveData<Resource<Booking>> = _bookingStatus

    private val DAY_DURATION = 86400000

    private val costDateFormatter = SimpleDateFormat("E, dd MMM", Locale.getDefault())
    private val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
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
                            date = costDateFormatter.format(currentDate),
                            fullDate = formatter.format(currentDate),
                            cost = cost?.toInt() ?: 0,
                            passengers = passengers.toString()
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

    override suspend fun sendPersonalInfo(ticketInfo: TicketInfo) {
        _bookingStatus.postValue(Resource.Loading())
        if (networkUtils.hasInternetConnection()) {
            val response = api.sendPersonalInfo(ticketInfo)
            val result = handleBookingResponse(response, context)
            _bookingStatus.postValue(result)
        } else {
            _bookingStatus.postValue(Resource.Error(context.resources.getString(R.string.no_internet_connection)))
        }
    }

    override suspend fun saveBooking(booking: Booking, flightInfoList: List<FlightInfo>) {
        dao.saveTickets(booking.toTicketEntityList())
        dao.saveFlightsInfo(booking.toFlightInfoEntityList(flightInfoList))
    }

    override suspend fun saveRoute(route: Route) {
        dao.saveRoute(route)
    }
}