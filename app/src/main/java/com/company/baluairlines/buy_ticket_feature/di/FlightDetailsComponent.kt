package com.company.baluairlines.buy_ticket_feature.di

import androidx.fragment.app.Fragment
import com.company.baluairlines.buy_ticket_feature.presentation.adapters.FlightDetailsAdapter
import com.company.baluairlines.buy_ticket_feature.presentation.viewmodels.FlightDetailsViewModel
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope


@FlightDetailsFragmentScope
@Subcomponent
interface FlightDetailsComponent {

    val flightDetailsAdapter: FlightDetailsAdapter
    val fragment: Fragment
    val viewModel: FlightDetailsViewModel
    //todo: переделать inject viewmodel

    fun flightDetailsViewComponent(): FlightDetailsViewComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            @BindsInstance viewModel: FlightDetailsViewModel
        ): FlightDetailsComponent
    }

}

@Scope
annotation class FlightDetailsFragmentScope