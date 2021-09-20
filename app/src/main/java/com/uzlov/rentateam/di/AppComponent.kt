package com.uzlov.rentateam.di

import com.uzlov.rentateam.di.modules.*
import com.uzlov.rentateam.viewmodels.UsersViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        CacheModule::class,
        NetworkModule::class,
        RxModule::class
    ]
)
interface AppComponent {
    fun inject(usersFragment: UsersViewModel)
}