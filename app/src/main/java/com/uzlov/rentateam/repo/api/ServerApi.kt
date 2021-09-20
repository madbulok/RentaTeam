package com.uzlov.rentateam.repo.api

import com.uzlov.rentateam.repo.models.UserPage
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ServerApi {

    @GET("users/")
    fun getUsers(): Single<UserPage>
}