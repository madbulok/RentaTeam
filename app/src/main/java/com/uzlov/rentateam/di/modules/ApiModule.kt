package com.uzlov.rentateam.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.uzlov.rentateam.repo.api.ServerApi
import com.uzlov.rentateam.repo.interfaces.ILocalUserInteractions
import com.uzlov.rentateam.repo.interfaces.IUserInteraction
import com.uzlov.rentateam.repo.interfaces.RemoteRepoImpl
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://reqres.in/api/"

    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String = baseUrl()): ServerApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .client(client())
            .build()
            .create(ServerApi::class.java)
    }

    @Singleton
    @Provides
    fun client(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRemoteRepository(
        api: ServerApi,
        @Named("io") scheduler: Scheduler,
        userInteractions: ILocalUserInteractions
    ): IUserInteraction = RemoteRepoImpl(api, userInteractions, scheduler)
}