package com.example.datacollect.adduser.viewmodel

import com.example.datacollect.adduser.ui.AddUserEvent
import com.example.datacollect.data.User
import com.example.datacollect.data.source.IUserDataSource
import com.example.kittytinder.common.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by Festus Kiambi on 6/17/19.
 */
class AddUserViewModel(
    private val userDataSource: IUserDataSource,
    uiContext: CoroutineContext

) : BaseViewModel<AddUserEvent>(uiContext) {

    override fun handleEvent(event: AddUserEvent) {
        when (event) {
            is AddUserEvent.OnSave -> saveUserDataToLocalStorage(event)
        }
    }

    private fun saveUserDataToLocalStorage(event: AddUserEvent.OnSave)= launch {
        val user = User(
            firstName = event.firstName,
            lastName = event.lastName,
            lat = event.lat,
            long = event.long,
            buildingImageUrl = event.buildingImageUrl,
            productInfo = event.productInfo
        )

        userDataSource.saveUser(user)
    }
}
