package com.example.datacollect.adduser.ui


/**
 * Created by Festus Kiambi on 6/15/19.
 */
sealed class AddUserEvent {
    data class OnSave(
        val firstName: String,
        var lastName: String ,
        var idNumber: Int ,
        var lat: Double  ,
        var long: Double ,
        var buildingImageUrl: String ,
        var productInfo: String
    ): AddUserEvent()
}