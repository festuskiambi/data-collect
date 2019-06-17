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
    val id: Int,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "id_number")
    val idNumber: Int,

    val lat: Int,

    val long: Int,

    @ColumnInfo(name = "building_image_url")
    val buildingImageUrl: String,

    @ColumnInfo(name = "product_info")
    val productInfo: String

)