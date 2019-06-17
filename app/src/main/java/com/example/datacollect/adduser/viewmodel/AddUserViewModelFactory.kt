package com.example.datacollect.adduser.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datacollect.data.source.IUserDataSource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by Festus Kiambi on 6/17/19.
 */
class AddUserViewModelFactory @Inject constructor(
    private val userDataSource: IUserDataSource): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddUserViewModel::class.java)) {
            return AddUserViewModel(userDataSource, Dispatchers.Main) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}