package com.dev.roomdatabase.local.model

data class Wishlist(
    val id: Long,
    val userId: Long,
    val title: String,
    val products: List<Product>
)
