package com.example.homework_2.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homework_2.data.db.entity.ProfileDbItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * from user")
    fun getAll(): Flow<List<ProfileDbItem>>

    @Query("SELECT * from user WHERE :userId == id")
    fun getUser(userId: Int): Flow<ProfileDbItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: ProfileDbItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<ProfileDbItem>)
}