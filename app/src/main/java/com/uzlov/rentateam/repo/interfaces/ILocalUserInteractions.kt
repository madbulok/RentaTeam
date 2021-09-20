package com.uzlov.rentateam.repo.interfaces

import com.uzlov.rentateam.repo.models.Data
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ILocalUserInteractions {
    fun getUsers() : Single<List<Data>>
    fun saveUser(users: List<Data>) : Completable
}