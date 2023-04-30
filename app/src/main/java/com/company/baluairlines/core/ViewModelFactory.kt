package com.company.baluairlines.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.baluairlines.buy_ticket_feature.data.BuyTicketRepository
import com.company.baluairlines.buy_ticket_feature.presentation.viewmodels.SearchResultViewModel
import com.company.baluairlines.services_feature.data.ServicesRepository
import com.company.baluairlines.services_feature.presentation.viewmodels.StatusViewModel
import javax.inject.Inject

/**
 * класс создания viewmodel определенного типа
 * @param servicesRepository [ServicesRepository]
 * @param buyTicketRepository [BuyTicketRepository]
 */
class ViewModelFactory @Inject constructor(
    private val servicesRepository: ServicesRepository,
    private val buyTicketRepository: BuyTicketRepository
) : ViewModelProvider.Factory {

    /**
     * функция создания viewmodel определенного типа
     * @param modelClass тип класса viewmodel
     * @return viewmodel переданного типа
     * @throws IllegalArgumentException если получаем класс отличный от сществующих viewmodel
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatusViewModel::class.java)) {
            return StatusViewModel(servicesRepository = servicesRepository) as T
        }
        if (modelClass.isAssignableFrom(SearchResultViewModel::class.java)) {
            return SearchResultViewModel(buyTicketRepository = buyTicketRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}