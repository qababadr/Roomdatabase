package com.dev.roomdatabase.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dev.roomdatabase.local.entity.MediaEntity
import com.dev.roomdatabase.utils.MEDIAS_TABLE

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedias(medias: List<MediaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(media: MediaEntity)

    @Query("DELETE FROM $MEDIAS_TABLE")
    suspend fun deleteAll()

    @Update
    suspend fun updateMedias(medias: List<MediaEntity>)
}