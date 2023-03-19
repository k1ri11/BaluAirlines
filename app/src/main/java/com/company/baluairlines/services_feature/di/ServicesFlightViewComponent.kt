package com.company.baluairlines.services_feature.di

import androidx.lifecycle.LifecycleOwner
import com.company.baluairlines.services_feature.presentation.controllers.ServicesFlightListController
import com.company.myapplication.databinding.FragmentServicesFlightListBinding
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@ServicesFlightViewScope
@Subcomponent
interface ServicesFlightViewComponent {
    val root: FragmentServicesFlightListBinding
    val viewLifecycleOwner: LifecycleOwner
    val servicesFlightListController: ServicesFlightListController


    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance root: FragmentServicesFlightListBinding,
            @BindsInstance viewLifecycleOwner: LifecycleOwner,
        ): ServicesFlightViewComponent
    }
}



@Scope
annotation class ServicesFlightViewScope


