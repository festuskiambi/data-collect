package com.example.datacollect.listUsers.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.datacollect.data.User

/**
 * Created by Festus Kiambi on 6/17/19.
 */

class UserDiffUtilCallback: DiffUtil.ItemCallback<User>(){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}