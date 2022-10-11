package com.dev.roomdatabase.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dev.roomdatabase.local.model.Media
import com.dev.roomdatabase.utils.MEDIAS_TABLE

@Entity(
    tableName = MEDIAS_TABLE,
    indices = [Index("product_id", unique = false)],
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["product_id"],
            childColumns = ["product_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class MediaEntity(
  @ColumnInfo(name = "media_id") @PrimaryKey(autoGenerate = true) val mediaId: Long = 0L,
  @ColumnInfo(name = "product_id") val productId: Long,
  val url: String
){

    fun toMedia(): Media{
        return Media(
            id = mediaId,
            url = url
        )
    }

}
