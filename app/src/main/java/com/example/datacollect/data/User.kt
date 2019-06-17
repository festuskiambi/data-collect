package com.example.datacollect.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Festus Kiambi on 6/17/19.
 */

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "first_name")
    var firstName: String ="",

    @ColumnInfo(name = "last_name")
    var lastName: String ="",

    @ColumnInfo(name = "id_number")
    var idNumber: Int =0,

    var lat: Float = 0f,

    var long: Float = 0f,

    @ColumnInfo(name = "building_image_url")
    var buildingImageUrl: String ="",

    @ColumnInfo(name = "product_info")
    var productInfo: String = ""

)