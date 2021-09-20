package com.uzlov.rentateam.repo.interfaces

import com.uzlov.rentateam.repo.models.Data
import com.uzlov.rentateam.repo.room.UsersDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val userDao: UsersDao) : ILocalUserInteractions {
    override fun getUsers(): Single<List<Data>> {
        return userDao.getUsers()
    }

    override fun saveUser(users: List<Data>) : Completable{
        return userDao.insert(users)
    }
}