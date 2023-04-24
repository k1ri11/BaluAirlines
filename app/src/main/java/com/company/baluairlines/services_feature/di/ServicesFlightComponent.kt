package com.company.baluairlines.services_feature.di

import androidx.fragment.app.Fragment
import com.company.baluairlines.services_feature.presentation.adapter.ServicesFlightAdapter
import com.company.baluairlines.services_feature.presentation.viewmodels.StatusViewModel
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@ServicesFlightFragmentScope
@Subcomponent
/**
 * Компонент привязанный к жизненному циклу фрагмента
 * @property adapter адаптер для recyclerview
 * @property fragment инстанс фрагмента
 * @property viewModel StatusViewModel для запросов информации о полетах по различным параметрам
 */
interface ServicesFlightComponent {
    val adapter: ServicesFlightAdapter
    val fragment: Fragment
    val viewModel: StatusViewModel
    //todo: переделать inject viewmodel

    /**
     * метод предоставления фабрики для создания компонента
     */
    fun servicesFlightListViewComponent(): ServicesFlightViewComponent.Factory

    /**
     * фабрика для создания компонента
     */
    @Subcomponent.Factory
    interface Factory {
        /**
         * метод создания компонента
         * @param fragment инстанс фрагмента
         * @param viewModel StatusViewModel для запросов информации о полетах по различным параметрам
         * @return Компонент привязанный к жизненному циклу фрагмента
         */
        fun create(
            @BindsInstance fragment: Fragment,
            @BindsInstance viewModel: StatusViewModel
        ): ServicesFlightComponent
    }
}

/**
 * скоуп жизненного цикла фрагмента
 */
@Scope
annotation class ServicesFlightFragmentScope