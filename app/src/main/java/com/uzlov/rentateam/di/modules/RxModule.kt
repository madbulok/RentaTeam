package com.uzlov.rentateam.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class RxModule {

    @Singleton
    @Provides
    @Named("io")
    fun io(): Scheduler = Schedulers.io()

    @Singleton
    @Provides
    @Named("computation")
    fun computation(): Scheduler = Schedulers.computation()

    @Singleton
    @Provides
    @Named("ui")
    fun ui(): Scheduler = AndroidSchedulers.mainThread()
}