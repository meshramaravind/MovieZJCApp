package com.arvind.moviezjcapp.domain.models.responses

import android.os.Parcelable
import com.arvind.moviezjcapp.domain.models.CastDetails
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastDetailsApiResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val casts: List<CastDetails>
) : Parcelable
