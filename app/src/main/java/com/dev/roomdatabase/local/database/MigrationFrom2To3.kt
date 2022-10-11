package com.dev.roomdatabase.local.database

import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec
import com.dev.roomdatabase.utils.USERS_TABLE

@RenameColumn(
    tableName = USERS_TABLE,
    fromColumnName = "full_name",
    toColumnName = "first_and_last_name"
)
class MigrationFrom2To3: AutoMigrationSpec