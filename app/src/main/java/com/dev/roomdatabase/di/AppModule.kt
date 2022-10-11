package com.dev.roomdatabase.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.dev.roomdatabase.application.RoomDatabaseApplication
import com.dev.roomdatabase.local.database.ControlledConverter
import com.dev.roomdatabase.local.database.MigrationFrom3To4
import com.dev.roomdatabase.local.database.ProductDatabase
import com.dev.roomdatabase.utils.PRODUCT_DATABASE
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRoomDatabaseApplication(@ApplicationContext application: Context): RoomDatabaseApplication {
        return application as RoomDatabaseApplication
    }

    @Singleton
    @Provides
    fun providesDatabase(application: Application): ProductDatabase {
        return Room.databaseBuilder(
            application,
            ProductDatabase::class.java,
            PRODUCT_DATABASE,
        ).addMigrations(MigrationFrom3To4)
            .addTypeConverter(ControlledConverter(Gson()))
            .fallbackToDestructiveMigration()
            .build()
    }
}