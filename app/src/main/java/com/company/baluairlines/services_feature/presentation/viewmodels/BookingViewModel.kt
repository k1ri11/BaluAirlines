package com.company.baluairlines.services_feature.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.baluairlines.core.domain.Booking
import com.company.baluairlines.core.utils.Resource
import com.company.baluairlines.services_feature.data.ServicesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookingViewModel(
    private val servicesRepository: ServicesRepository
): ViewModel() {

    val bookingStatus: LiveData<Resource<Booking>> = servicesRepository.bookingStatus

    fun getBooking(bookRef: String) =
        viewModelScope.launch(Dispatchers.IO) {
            servicesRepository.getBooking(bookRef)
        }
}