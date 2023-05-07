package com.company.baluairlines.buy_ticket_feature.data

import androidx.lifecycle.LiveData
import com.company.baluairlines.core.domain.BookingReq
import com.company.baluairlines.core.domain.CostItem
import com.company.baluairlines.core.domain.Flight
import com.company.baluairlines.core.domain.TicketInfo
import com.company.baluairlines.core.utils.Resource

interface BuyTicketRepository {

    val searchResult: LiveData<Resource<List<Flight>>>
    val costs: LiveData<Resource<List<CostItem>>>
    val bookingStatus: LiveData<Resource<BookingReq>>
    suspend fun getFlights(
        date: Long, departureAirport: String, arrivalAirport: String, maxTransits: Int,
        serviceClass: String, passengers: Int
    )

    suspend fun getFlightCosts(
        date: Long, departureAirport: String, arrivalAirport: String, maxTransits: Int,
        serviceClass: String, passengers: Int
    )

    suspend fun sendPersonalInfo(ticketInfo: TicketInfo)
}