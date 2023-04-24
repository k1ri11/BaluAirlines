package com.company.baluairlines.services_feature.di

import androidx.lifecycle.LifecycleOwner
import com.company.baluairlines.services_feature.presentation.controllers.ServicesFlightListController
import com.company.myapplication.databinding.FragmentServicesFlightListBinding
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@ServicesFlightViewScope
@Subcomponent
/**
 * Компонент привязанный к жизненному циклу view фрагмента
 * @property root binding класс с элементами пользовательского интерфейса
 * @property viewLifecycleOwner хранит изменения жизненного цикла
 * @property servicesFlightListController содержит всю логику управления пользовательским интерфейсом фрагмента
 * */
interface ServicesFlightViewComponent {
    val root: FragmentServicesFlightListBinding
    val viewLifecycleOwner: LifecycleOwner
    val servicesFlightListController: ServicesFlightListController


    /**
     * фабрика для создания компонента
     */
    @Subcomponent.Factory
    interface Factory {
        /**
         * метод создания компонента
         * @param root binding класс с элементами пользовательского интерфейса
         * @param viewLifecycleOwner хранит изменения жизненного цикла
         * @return Компонент привязанный к жизненному циклу view фрагмента
         */
        fun create(
            @BindsInstance root: FragmentServicesFlightListBinding,
            @BindsInstance viewLifecycleOwner: LifecycleOwner,
        ): ServicesFlightViewComponent
    }
}


/**
 * скоуп жизненного цикла view фрагмента
 */
@Scope
annotation class ServicesFlightViewScope


