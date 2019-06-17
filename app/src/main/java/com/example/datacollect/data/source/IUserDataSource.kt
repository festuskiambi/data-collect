package com.example.datacollect.data.source

import com.example.datacollect.data.User

/**
 * Created by Festus Kiambi on 6/17/19.
 */
interface IUserDataSource {

    suspend fun getUsers(): List<User>

    suspend fun saveUser(user: User)
}