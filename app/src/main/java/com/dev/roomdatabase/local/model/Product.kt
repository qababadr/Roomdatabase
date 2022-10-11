package com.dev.roomdatabase.local.model

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val medias: List<Media>
)
