package com.aasif.test.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    @SerializedName("address")
    val address: String,

    @SerializedName("category_id")
    val categoryId: Int,

    val id: Int,

    val image_url: String,

    val name: String,

    val phone_number: String


) : Parcelable