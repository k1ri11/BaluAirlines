package com.company.baluairlines.core.di

import android.content.Context
import com.company.baluairlines.core.ViewModelFactory
import com.company.baluairlines.core.data.AirApi
import com.company.baluairlines.core.data.database.AirDao
import com.company.baluairlines.core.utils.NetworkUtils
import com.company.baluairlines.services_feature.di.ServicesFlightComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope


@ApplicationScope
@Component(modules = [AppModule::class,AppModuleBinds::class])
interface AppComponent {
    val appContext: Context
    val api: AirApi
    val dao: AirDao
    val networkUtils: NetworkUtils
    fun getViewModelFactory(): ViewModelFactory
    fun servicesFlightComponent(): ServicesFlightComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance appContext: Context,
        ): AppComponent
    }
}

@Scope
annotation class ApplicationScope