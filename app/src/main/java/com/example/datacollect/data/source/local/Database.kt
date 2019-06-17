package com.example.datacollect.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datacollect.data.User

/**
 * Created by Festus Kiambi on 6/17/19.
 */
@Database(
    entities = [User::class], version = 1
)
 abstract class Database: RoomDatabase() {
    abstract fun userDao(): UserDao
}