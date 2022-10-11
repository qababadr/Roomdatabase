package com.dev.roomdatabase.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.dev.roomdatabase.local.entity.ProductEntity
import com.dev.roomdatabase.local.relation.ProductWithMedias
import com.dev.roomdatabase.utils.MEDIAS_TABLE
import com.dev.roomdatabase.utils.PRODUCTS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

   @Transaction
   @Query("SELECT * FROM $PRODUCTS_TABLE")
   fun getProducts(): Flow<List<ProductWithMedias>>

   @Query("DELETE FROM $PRODUCTS_TABLE")
   suspend fun deleteAll()

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertProducts(products: List<ProductEntity>)

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addProduct(product: ProductEntity)

   @Update
   suspend fun updateProduct(product: ProductEntity)

   @Query("SELECT * FROM $PRODUCTS_TABLE WHERE product_id=:id")
   fun getProduct(id: Long): Flow<ProductWithMedias>


}