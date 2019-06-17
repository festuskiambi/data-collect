package com.example.datacollect.di.modules

import android.app.Application
import androidx.room.Room
import com.example.datacollect.adduser.viewmodel.AddUserViewModelFactory
import com.example.datacollect.data.source.IUserDataSource
import com.example.datacollect.data.source.UserRepository
import com.example.datacollect.data.source.local.Database
import com.example.datacollect.data.source.local.UserDao
import com.example.datacollect.listUsers.viewmodel.ListUserViewModelFactory

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
    fun provideUsersDao(database: Database) = database.userDao()

    @Provides
    fun provideUserDataSource( userDao: UserDao): IUserDataSource{
        return UserRepository(userDao)
    }

    @Provides
    fun provideAddUserViewModelFactory(userDataSource: IUserDataSource): AddUserViewModelFactory {
        return AddUserViewModelFactory(userDataSource)
    }

    @Provides
    fun provideListUserViewModelFactory(userDataSource: IUserDataSource): ListUserViewModelFactory {
        return ListUserViewModelFactory(userDataSource)
    }
}