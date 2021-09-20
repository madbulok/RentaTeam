package com.uzlov.rentateam.repo.room

import androidx.room.*
import com.uzlov.rentateam.repo.models.Data

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UsersDao {

    @Query("SELECT * FROM Data")
    fun getUsers(): Single<List<Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(team: Data): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(team: List<Data>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg team: Data): Completable

    @Delete
    fun delete(team: Data): Completable

    @Delete
    fun delete(team: List<Data>): Completable

    @Delete
    fun delete(vararg team: Data): Completable
}