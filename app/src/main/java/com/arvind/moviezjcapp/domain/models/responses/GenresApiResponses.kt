package com.arvind.moviezjcapp.domain.models.responses

import android.os.Parcelable
import com.arvind.moviezjcapp.domain.models.Genres
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class GenresApiResponses(
    @SerializedName("genres")
    val genres: List<Genres>
) : Parcelable
