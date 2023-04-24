package com.company.baluairlines.buy_ticket_feature.di

import androidx.lifecycle.LifecycleOwner
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@ServicesFlightViewScope
@Subcomponent
interface BuyTicketViewComponent {
//    val root: FragmentServicesFlightListBinding
    val viewLifecycleOwner: LifecycleOwner
//    val servicesFlightListController: ServicesFlightListController


    @Subcomponent.Factory
    interface Factory {
        fun create(
//            @BindsInstance root: FragmentServicesFlightListBinding,
            @BindsInstance viewLifecycleOwner: LifecycleOwner,
        ): BuyTicketViewComponent
    }
}



@Scope
annotation class ServicesFlightViewScope


