package com.dev.roomdatabase.local.database

import androidx.room.*
import com.dev.roomdatabase.local.dao.*
import com.dev.roomdatabase.local.entity.*

@Database(
    version = 4,
    entities = [
        ProductEntity::class,
        MediaEntity::class,
        UserEntity::class,
        BioEntity::class,
        WishlistEntity::class,
        WishlistProductCrossRef::class,
        BookmarkEntity::class
    ],
    autoMigrations = [
        AutoMigration(from = 2, to = 3 , spec = MigrationFrom2To3::class)
    ]
)
//@TypeConverters(Converters::class)
@TypeConverters(ControlledConverter::class)
abstract class ProductDatabase:RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun mediaDao() : MediaDao

    abstract fun userDao() : UserDao

    abstract fun bioDao() : BioDao

    abstract fun wishlistDao(): WishlistDao

}