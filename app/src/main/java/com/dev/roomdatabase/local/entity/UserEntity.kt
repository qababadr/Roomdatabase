package com.dev.roomdatabase.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dev.roomdatabase.utils.USERS_TABLE

@Entity(
    tableName = USERS_TABLE,
    indices = [Index("email", unique = true)]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val email: String,
    @ColumnInfo(name = "first_and_last_name", defaultValue = "") val firstAndLastname: String
)
