package com.dev.roomdatabase.local.dao

import androidx.room.*
import com.dev.roomdatabase.local.entity.WishlistEntity
import com.dev.roomdatabase.local.entity.WishlistProductCrossRef
import com.dev.roomdatabase.local.relation.WishlistWithProductsAndMedias
import com.dev.roomdatabase.utils.WISHLISTS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishes(wishlists: List<WishlistEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToWishlist(wish: WishlistEntity): Long

    @Query("DELETE FROM $WISHLISTS_TABLE")
    suspend fun deleteAll()

    @Query("DELETE FROM $WISHLISTS_TABLE WHERE wishlist_id=:id")
    suspend fun deleteWishlist(id: Long)

    @Query("DELETE FROM $WISHLISTS_TABLE WHERE product_id =:productId")
    suspend fun removeFromWishlist(productId: Long)

    @Query("SELECT EXISTS(SELECT * FROM $WISHLISTS_TABLE WHERE product_id =:productId)")
    fun inWishlist(productId: Long): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishlistProductCrossRef(wishlistCrossRef: WishlistProductCrossRef)

    @Transaction
    @Query("SELECT * FROM $WISHLISTS_TABLE WHERE user_id=:userId")
    fun getAllWishlistWithDetail(userId: Long): Flow<List<WishlistWithProductsAndMedias>>
}