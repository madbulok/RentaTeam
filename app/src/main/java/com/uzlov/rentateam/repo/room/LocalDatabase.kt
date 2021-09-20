package com.uzlov.rentateam.repo.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uzlov.rentateam.repo.models.UserModel

@Database(
    entities = [
        UserModel::class
    ],
    version = 1
)
abstract class LocalDatabase : RoomDatabase() {

    abstract val teamDao: UsersDao
}