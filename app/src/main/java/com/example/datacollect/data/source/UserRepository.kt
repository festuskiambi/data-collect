package com.example.datacollect.data.source

import com.example.datacollect.data.User
import com.example.datacollect.data.source.local.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Festus Kiambi on 6/17/19.
 */
class UserRepository @Inject constructor(private val usersDao: UserDao): IUserDataSource {
    override suspend fun saveUser(user: User)= withContext(Dispatchers.IO) {
        usersDao.insertUser(user)
    }

    override suspend fun getUsers()= withContext(Dispatchers.IO) {
         usersDao.queryUsers()
    }
}