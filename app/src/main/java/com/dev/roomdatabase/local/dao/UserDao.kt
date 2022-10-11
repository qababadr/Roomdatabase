package com.dev.roomdatabase.local.dao

import androidx.room.*
import com.dev.roomdatabase.local.entity.UserEntity
import com.dev.roomdatabase.local.relation.UserAndBio
import com.dev.roomdatabase.utils.USERS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("DELETE FROM $USERS_TABLE")
    suspend fun deleteAll()

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("SELECT * FROM USERS_TABLE ORDER BY id DESC LIMIT 1")
    fun checkUser(): Flow<UserEntity?>


    @Transaction
    @Query("SELECT * FROM $USERS_TABLE ORDER BY id DESC LIMIT 1")
    fun getLastUser(): Flow<UserAndBio?>
}