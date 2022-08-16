package com.ogurt.login.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import room.DataBase

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        DataBase::class.java,
        TABLE_NAME
    ).build()

    @Provides
    @Singleton
    fun providesDao(db: DataBase) = db.dao()

    companion object {
        const val TABLE_NAME = "user_info"
    }
}
