package com.dev.roomdatabase.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dev.roomdatabase.utils.BOOKMARK_TABLE

@Entity(tableName = BOOKMARK_TABLE)
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "product_id") val productId: Long,
    @ColumnInfo(name = "product_title") val productTitle: String
)
