package com.uzlov.rentateam.di.modules

import android.content.Context
import androidx.room.Room
import com.uzlov.rentateam.repo.interfaces.ILocalUserInteractions
import com.uzlov.rentateam.repo.interfaces.LocalRepositoryImpl
import com.uzlov.rentateam.repo.room.LocalDatabase
import com.uzlov.rentateam.repo.room.UsersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun getLocalName() : String = "com.uzlov.database"

    @Singleton
    @Provides
    fun database(app: Context) = Room.databaseBuilder(app, LocalDatabase::class.java, getLocalName())
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun userDao(database: LocalDatabase) : UsersDao = database.usersDao
   
    @Singleton
    @Provides
    fun localRepo(usersDao: UsersDao) : ILocalUserInteractions= LocalRepositoryImpl(usersDao)
}