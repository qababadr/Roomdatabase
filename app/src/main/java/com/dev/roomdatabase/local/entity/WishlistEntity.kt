package com.dev.roomdatabase.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dev.roomdatabase.utils.WISHLISTS_TABLE

@Entity(tableName = WISHLISTS_TABLE)
data class WishlistEntity(
  @ColumnInfo(name = "wishlist_id") @PrimaryKey(autoGenerate = true) val wishlistId: Long = 0L,
  @ColumnInfo(name = "product_id") val productId: Long,
  @ColumnInfo(defaultValue = "") val title: String,
  @ColumnInfo(name = "user_id") val userId: Long
)
