package com.uzlov.rentateam.app

import android.app.Application
import com.uzlov.rentateam.di.AppComponent
import com.uzlov.rentateam.di.DaggerAppComponent
import com.uzlov.rentateam.di.modules.AppModule

class App : Application(){
    companion object {

        lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }
}