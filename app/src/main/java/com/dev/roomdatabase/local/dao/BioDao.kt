package com.dev.roomdatabase.local.dao

import androidx.room.*
import com.dev.roomdatabase.local.entity.BioEntity
import com.dev.roomdatabase.utils.BIOS_TABLE

@Dao
interface BioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBio(bio: BioEntity)

    @Query("DELETE FROM $BIOS_TABLE")
    suspend fun deleteAll()

    @Update
    suspend fun updateBio(bio: BioEntity)
}