package com.arvind.moviezjcapp.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class Genres(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String
) : Parcelable