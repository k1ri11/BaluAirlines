package com.company.baluairlines.buy_ticket_feature.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.baluairlines.buy_ticket_feature.data.BuyTicketRepository
import com.company.baluairlines.core.data.mappers.serviceToString
import com.company.baluairlines.core.domain.*
import com.company.baluairlines.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlightDetailsViewModel(
    private val buyTicketRepository: BuyTicketRepository
) : ViewModel() {

    val bookingStatus: LiveData<Resource<Booking>> = buyTicketRepository.bookingStatus

    fun sendPersonalInformation(
        flightService: ServiceClass,
        passengerName: String,
        phone: String,
        email: String,
        passport: String,
        flights: List<Int>
    ) = viewModelScope.launch(Dispatchers.IO) {
        val contactData = ContactData(email = email, phone = phone)
        val passengers = listOf(
            Passenger(
                contactData = contactData,
                passengerName = passengerName,
                passengerId = passport,
            )
        )
        val ticketInfo = TicketInfo(
            fareCondition = flightService.serviceToString(),
            flights = flights,
            passengers = passengers
        )
        buyTicketRepository.sendPersonalInfo(ticketInfo)
    }
}