package com.example.datacollect.data.source

import com.example.datacollect.data.User
import com.example.datacollect.data.source.local.UserDao
import javax.inject.Inject

/**
 * Created by Festus Kiambi on 6/17/19.
 */
class UserRepository @Inject constructor(private val usersDao: UserDao): IUserDataSource {
    override suspend fun saveUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getUsers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}