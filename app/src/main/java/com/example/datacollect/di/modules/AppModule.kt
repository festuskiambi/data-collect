package com.example.datacollect.di.modules

import android.app.Application
import androidx.room.Room
import com.example.datacollect.data.source.local.Database

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Festus Kiambi on 6/17/19.
 */
@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideUsersDatabase(app: Application) = Room.databaseBuilder(
        app, Database::class.java, "users"
    ).build()

    @Provides
    @Singleton
    fun provideusersDao(database: Database) = database.userDao()
}