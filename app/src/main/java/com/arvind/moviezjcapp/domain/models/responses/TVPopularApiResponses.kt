package com.arvind.moviezjcapp.domain.models.responses

import com.arvind.moviezjcapp.domain.models.TVPopular
import com.google.gson.annotations.SerializedName

data class TVPopularApiResponses(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val searches: List<TVPopular>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
