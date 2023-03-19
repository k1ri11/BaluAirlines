package com.company.baluairlines.services_feature.di

import androidx.fragment.app.Fragment
import com.company.baluairlines.core.di.AppModuleBinds
import com.company.baluairlines.services_feature.presentation.adapter.ServicesFlightAdapter
import com.company.baluairlines.services_feature.presentation.viewmodels.StatusViewModel
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@ServicesFlightFragmentScope
@Subcomponent
interface ServicesFlightComponent {
    val adapter: ServicesFlightAdapter
    val fragment: Fragment
    val viewModel: StatusViewModel
    //todo: переделать inject viewmodel

    fun servicesFlightListViewComponent(): ServicesFlightViewComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            @BindsInstance viewModel: StatusViewModel
        ): ServicesFlightComponent
    }
}


@Scope
annotation class ServicesFlightFragmentScope