package com.dev.roomdatabase.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["wishlist_id", "product_id"],
    indices = [Index("product_id")],
    foreignKeys = [
        ForeignKey(
            entity = WishlistEntity::class,
            parentColumns = ["wishlist_id"],
            childColumns = ["wishlist_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["product_id"],
            childColumns = ["product_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class WishlistProductCrossRef(
  @ColumnInfo(name = "wishlist_id")  val wishlistId: Long,
  @ColumnInfo(name = "product_id") val productId: Long
)
