package com.uzlov.rentateam.di.modules

import android.content.Context
import com.uzlov.rentateam.network_android.AndroidNetworkStatus
import com.uzlov.rentateam.network_android.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkStatus(context: Context): INetworkStatus = AndroidNetworkStatus(context)

}