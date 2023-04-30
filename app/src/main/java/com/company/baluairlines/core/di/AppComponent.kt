package com.company.baluairlines.core.di

import android.content.Context
import com.company.baluairlines.buy_ticket_feature.di.SearchResultComponent
import com.company.baluairlines.core.ViewModelFactory
import com.company.baluairlines.core.data.AirApi
import com.company.baluairlines.core.data.database.AirDao
import com.company.baluairlines.core.utils.NetworkUtils
import com.company.baluairlines.services_feature.di.ServicesFlightComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope


/** компонент содержащий основные зависимости с жц приложения
 * @property appContext контекст приложения
 * @property api интерфейс для сетевых запросов
 * @property dao интерфейс для работы с БД
 * @property networkUtils класс с информацией о статусе подключения к интернету
 * */
@ApplicationScope
@Component(modules = [AppModule::class,AppModuleBinds::class])
interface AppComponent {
    val appContext: Context
    val api: AirApi
    val dao: AirDao
    val networkUtils: NetworkUtils
    /** функция получения фабрики для viewmodel  */
    fun getViewModelFactory(): ViewModelFactory

    /** функция получения компонента для сервис фичи  */
    fun servicesFlightComponent(): ServicesFlightComponent.Factory

    /** функция получения компонента для фичи поиска авиабилета */
    fun searchResultComponent(): SearchResultComponent.Factory

    /** фабрика компонента */
    @Component.Factory
    interface Factory {
        /**
         * метод создания компонента с жц приложения
         * @param appContext контекст приложения
         * @return Компонент приложения с жц приложения
         */
        fun create(
            @BindsInstance appContext: Context,
        ): AppComponent
    }
}

@Scope
annotation class ApplicationScope