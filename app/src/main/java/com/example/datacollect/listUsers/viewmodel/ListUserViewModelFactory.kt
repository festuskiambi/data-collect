package com.example.datacollect.listUsers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datacollect.data.source.IUserDataSource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by Festus Kiambi on 6/17/19.
 */
class ListUserViewModelFactory @Inject constructor(
    private val userDataSource: IUserDataSource
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListUserViewModel::class.java)) {
            return ListUserViewModel(userDataSource, Dispatchers.Main) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}