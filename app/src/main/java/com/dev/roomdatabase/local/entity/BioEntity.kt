package com.dev.roomdatabase.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dev.roomdatabase.utils.BIOS_TABLE
import java.util.*

@Entity(
    tableName = BIOS_TABLE,
    indices = [Index("user_id", unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class BioEntity(
   @PrimaryKey(autoGenerate = true) val id: Long = 0L,
   @ColumnInfo(name = "user_id") val userId: Long,
   val address: String,
   val phone: String,
   @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
   @ColumnInfo(name = "updated_at") val updatedAt: Long? = 0L
)
