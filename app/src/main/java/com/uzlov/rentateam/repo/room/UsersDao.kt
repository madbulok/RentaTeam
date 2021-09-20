package com.uzlov.rentateam.repo.room

import androidx.room.*

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UsersDao {

//    @Query("SELECT * FROM UserModel")
//    fun getTeams() : Single<List<TeamEntity>>
//
//    @Query("SELECT * FROM TeamEntity WHERE id=:id")
//    fun getTeamByID(id: Int) : Single<TeamEntity>
//
//    @Insert
//    fun insert(team: TeamEntity) : Completable
//
//    @Delete
//    fun delete(team: TeamEntity) : Completable
//
////    @Query("UPDATE TeamEntity SET isActive=1 WHERE id=:teamId")
////    fun setActiveTeam(teamId: Int): Completable
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun setActiveTeam(teamId: TeamEntity): Completable
//
//    @Query("UPDATE TeamEntity SET isActive=0 WHERE id > 0")
//    fun setInactiveAllTeam(): Completable
}