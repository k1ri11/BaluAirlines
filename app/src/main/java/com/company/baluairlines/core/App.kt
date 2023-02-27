package com.company.baluairlines.core

import android.app.Application
import android.content.Context
import com.company.baluairlines.core.di.AppComponent
import com.company.baluairlines.core.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this.applicationContext)
    }

}