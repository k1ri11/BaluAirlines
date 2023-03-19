package com.company.baluairlines.core.di

import com.company.baluairlines.services_feature.data.ServicesRepository
import com.company.baluairlines.services_feature.di.ServicesFlightFragmentScope
import com.company.baluairlines.services_feature.domain.ServicesRepositoryImpl
import dagger.Binds
import dagger.Module

//todo: убрать в ServicesFlightComponent (после правильного инжекта viewmodel)

@Module
interface AppModuleBinds {

    @ApplicationScope
    @Binds
    fun getServicesRepository(repository: ServicesRepositoryImpl): ServicesRepository

}