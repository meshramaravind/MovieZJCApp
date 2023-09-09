package com.arvind.moviezjcapp.domain.models.responses

import com.arvind.moviezjcapp.domain.models.TVTopRated
import com.google.gson.annotations.SerializedName

data class TVTopRatedApiResponses(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val searches: List<TVTopRated>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
