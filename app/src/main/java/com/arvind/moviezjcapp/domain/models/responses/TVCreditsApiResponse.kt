package com.arvind.moviezjcapp.domain.models.responses

import com.arvind.moviezjcapp.domain.models.TVCredits
import com.google.gson.annotations.SerializedName

data class TVCreditsApiResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("casts")
    val casts: List<TVCredits>
)
