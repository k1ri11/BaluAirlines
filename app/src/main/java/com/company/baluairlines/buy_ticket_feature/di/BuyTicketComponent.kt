package com.company.baluairlines.buy_ticket_feature.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@BuyTicketFragmentScope
@Subcomponent
interface BuyTicketComponent {
//    val adapter: ServicesFlightAdapter
    val fragment: Fragment
//    val viewModel: StatusViewModel
    //todo: переделать inject viewmodel

    fun buyTicketViewComponent(): BuyTicketViewComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
//            @BindsInstance viewModel: StatusViewModel
        ): BuyTicketComponent
    }
}


@Scope
annotation class BuyTicketFragmentScope