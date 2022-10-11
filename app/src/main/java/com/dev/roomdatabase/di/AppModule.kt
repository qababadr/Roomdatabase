package com.dev.roomdatabase.di

import android.content.Context
import com.dev.roomdatabase.application.RoomDatabaseApplication
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

}