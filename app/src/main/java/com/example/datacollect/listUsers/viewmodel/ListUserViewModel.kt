package com.example.datacollect.listUsers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.datacollect.data.User
import com.example.datacollect.data.source.IUserDataSource
import com.example.datacollect.listUsers.ui.ListUserEvent
import com.example.kittytinder.common.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by Festus Kiambi on 6/17/19.
 */
class ListUserViewModel(
    private val userDataSource: IUserDataSource,
    uiContext: CoroutineContext
) : BaseViewModel<ListUserEvent>(uiContext) {

    private val usersListState = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>> get() = usersListState

    override fun handleEvent(event: ListUserEvent) {

        when(event){
            is ListUserEvent.OnStart -> getUserList()
        }
    }

    private fun getUserList()= launch {
        val result = userDataSource.getUsers()
        usersListState.value = result
    }
}