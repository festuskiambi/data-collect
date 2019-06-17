package com.example.datacollect.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.datacollect.data.User

/**
 * Created by Festus Kiambi on 6/17/19.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun queryPosts(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(user: User)
}