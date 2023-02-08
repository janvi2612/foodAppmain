package com.example.foodapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
open class BaseResponse : Parcelable {
    @SerializedName("MESSAGE")
    val message: String? = null
    @SerializedName("STATUS")
    val status: String? = null
}
