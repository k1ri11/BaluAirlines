package com.company.baluairlines.buy_ticket_feature.di

import androidx.lifecycle.LifecycleOwner
import com.company.baluairlines.buy_ticket_feature.presentation.controllers.SearchResultController
import com.company.myapplication.databinding.FragmentSearchResultBinding
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@SearchResultViewScope
@Subcomponent
interface SearchResultViewComponent {
    val root: FragmentSearchResultBinding
    val viewLifecycleOwner: LifecycleOwner
    val searchResultController: SearchResultController


    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance root: FragmentSearchResultBinding,
            @BindsInstance viewLifecycleOwner: LifecycleOwner,
        ): SearchResultViewComponent
    }
}



@Scope
annotation class SearchResultViewScope


