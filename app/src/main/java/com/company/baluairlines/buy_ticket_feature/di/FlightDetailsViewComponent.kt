package com.company.baluairlines.buy_ticket_feature.di

import androidx.lifecycle.LifecycleOwner
import com.company.baluairlines.buy_ticket_feature.presentation.controllers.FlightDetailsController
import com.company.myapplication.databinding.FragmentFlightDetailsBinding
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@FragmentDetailsViewScope
@Subcomponent
interface FlightDetailsViewComponent{
    val root: FragmentFlightDetailsBinding
    val viewLifecycleOwner: LifecycleOwner
    val flightDetailsController: FlightDetailsController


    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance root: FragmentFlightDetailsBinding,
            @BindsInstance viewLifecycleOwner: LifecycleOwner,
        ): FlightDetailsViewComponent
    }
}

@Scope
annotation class FragmentDetailsViewScope