package com.company.baluairlines.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.baluairlines.services_feature.data.ServicesRepository
import com.company.baluairlines.services_feature.presentation.viewmodels.StatusViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor (private val servicesRepository: ServicesRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatusViewModel::class.java)) {
            return StatusViewModel(servicesRepository = servicesRepository ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}