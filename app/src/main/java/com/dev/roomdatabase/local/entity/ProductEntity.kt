package com.dev.roomdatabase.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dev.roomdatabase.utils.PRODUCTS_TABLE

@Entity(tableName = PRODUCTS_TABLE)
data class ProductEntity(
   @ColumnInfo(name = "product_id") @PrimaryKey val productId: Long,
   val title: String,
   val price: Double,
   val description: String,
   @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
   @ColumnInfo(name = "updated_at") val updatedAt: Long? = 0L
)
