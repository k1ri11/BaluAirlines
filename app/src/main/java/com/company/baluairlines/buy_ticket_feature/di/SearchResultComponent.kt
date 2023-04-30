package com.company.baluairlines.buy_ticket_feature.di

import androidx.fragment.app.Fragment
import com.company.baluairlines.buy_ticket_feature.presentation.adapters.CostsAdapter
import com.company.baluairlines.buy_ticket_feature.presentation.adapters.FlightListAdapter
import com.company.baluairlines.buy_ticket_feature.presentation.viewmodels.SearchResultViewModel
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@SearchResultFragmentScope
@Subcomponent
interface SearchResultComponent {
    val costsAdapter: CostsAdapter
    val flightListAdapter: FlightListAdapter
    val fragment: Fragment
    val viewModel: SearchResultViewModel
    //todo: переделать inject viewmodel

    fun searchResultViewComponent(): SearchResultViewComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            @BindsInstance viewModel: SearchResultViewModel
        ): SearchResultComponent
    }
}


@Scope
annotation class SearchResultFragmentScope