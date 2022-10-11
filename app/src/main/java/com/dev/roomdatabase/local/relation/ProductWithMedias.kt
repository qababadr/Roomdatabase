package com.dev.roomdatabase.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.dev.roomdatabase.local.entity.MediaEntity
import com.dev.roomdatabase.local.entity.ProductEntity
import com.dev.roomdatabase.local.model.Product

data class ProductWithMedias(

    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "product_id",
        entityColumn = "product_id"
    )
    val medias: List<MediaEntity>
){

    fun toProduct(): Product{
        return Product(
            id = product.productId,
            title = product.title,
            price = product.price,
            description = product.description,
            medias = medias.map { it.toMedia() }
        )
    }

}
