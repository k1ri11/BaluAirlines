package com.company.baluairlines.buy_ticket_feature.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.baluairlines.buy_ticket_feature.data.BuyTicketRepository
import com.company.baluairlines.core.domain.CostItem
import com.company.baluairlines.core.domain.Flight
import com.company.baluairlines.core.domain.FlightUIState
import com.company.baluairlines.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class SearchResultViewModel(
    private val buyTicketRepository: BuyTicketRepository
) : ViewModel() {

    val searchResult: LiveData<Resource<List<Flight>>> = buyTicketRepository.searchResult
    val costs: LiveData<Resource<List<CostItem>>> = buyTicketRepository.costs

    private val _uiState: MutableLiveData<FlightUIState> = MutableLiveData()
    val uiState: LiveData<FlightUIState> = _uiState

    private val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)

    fun setUIState(state: FlightUIState){
        _uiState.value = state
    }
    fun getFlightCosts() = viewModelScope.launch(Dispatchers.IO) {
        buyTicketRepository.getFlightCosts(
            formatter.parse(uiState.value!!.date)!!.time,
            uiState.value!!.departureAirport,
            uiState.value!!.arrivalAirport,
            maxTransits = uiState.value!!.maxTransits,
            serviceClass = uiState.value!!.serviceClass,
            passengers = uiState.value!!.passengers
        )
    }


    fun getFlights() = viewModelScope.launch(Dispatchers.IO) {
        buyTicketRepository.getFlights(
            formatter.parse(uiState.value!!.date)!!.time,
            uiState.value!!.departureAirport,
            uiState.value!!.arrivalAirport,
            maxTransits = uiState.value!!.maxTransits,
            serviceClass = uiState.value!!.serviceClass,
            passengers = uiState.value!!.passengers
        )
    }

    fun getFlights(date: String){
        _uiState.value = _uiState.value!!.copy(date = date)
        getFlights()
    }

}