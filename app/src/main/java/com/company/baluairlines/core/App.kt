package com.company.baluairlines.core

import android.app.Application
import android.content.Context
import com.company.baluairlines.core.di.AppComponent
import com.company.baluairlines.core.di.DaggerAppComponent

/**
 * класс приложения
 * @property appComponent даггер компонент, содержит основные зависимости приложения
 */
class App : Application() {

    lateinit var appComponent: AppComponent

    /**
     * функция для возврата класса приложения, чтобы через него получить доступ к компоненту [appComponent]
     */
    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this.applicationContext)
    }

}