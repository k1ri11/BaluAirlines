package com.company.baluairlines.core.di

import android.content.Context
import com.company.baluairlines.core.data.AirApi
import com.company.baluairlines.core.data.database.AirDao
import com.company.baluairlines.core.utils.NetworkUtils
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {
    val appContext: Context
    val api: AirApi
    val dao: AirDao
    val networkUtils: NetworkUtils
//    fun activityComponent(): ActivityComponent.Factory
//    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance appContext: Context,
        ): AppComponent
    }
}


annotation class ApplicationScope