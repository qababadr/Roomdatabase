package com.dev.roomdatabase.local.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dev.roomdatabase.utils.BOOKMARK_TABLE

object MigrationFrom3To4 : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS $BOOKMARK_TABLE" +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `product_id` INTEGER NOT NULL,`product_title` TEXT NOT NULL)"
        )
    }

}