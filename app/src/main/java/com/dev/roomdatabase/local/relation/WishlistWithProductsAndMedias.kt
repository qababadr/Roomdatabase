package com.dev.roomdatabase.local.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.dev.roomdatabase.local.entity.ProductEntity
import com.dev.roomdatabase.local.entity.WishlistEntity
import com.dev.roomdatabase.local.entity.WishlistProductCrossRef
import com.dev.roomdatabase.local.model.Wishlist

data class WishlistWithProductsAndMedias(
    @Embedded val wishlist: WishlistEntity,
    @Relation(
        entity = ProductEntity::class,
        parentColumn = "wishlist_id",
        entityColumn = "product_id",
        associateBy = Junction(
            WishlistProductCrossRef::class,
            parentColumn = "wishlist_id",
            entityColumn = "product_id"
        )
    )
    val products: List<ProductWithMedias>
){

    fun toWishlist(): Wishlist{
        return Wishlist(
            id = wishlist.wishlistId,
            userId = wishlist.userId,
            title = "wishlist title ${wishlist.wishlistId}",
            products = products.map { it.toProduct() }
        )
    }

}
