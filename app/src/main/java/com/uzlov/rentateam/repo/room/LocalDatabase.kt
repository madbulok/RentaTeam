package com.uzlov.rentateam.repo.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uzlov.rentateam.repo.models.Data
import com.uzlov.rentateam.repo.models.UserPage

@Database(
    entities = [
        Data::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    abstract val usersDao: UsersDao
}