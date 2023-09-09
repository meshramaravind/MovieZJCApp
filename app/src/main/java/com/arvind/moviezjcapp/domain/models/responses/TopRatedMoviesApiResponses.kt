package com.arvind.moviezjcapp.domain.models.responses

import com.arvind.moviezjcapp.domain.models.TopRatedMovies
import com.google.gson.annotations.SerializedName

data class TopRatedMoviesApiResponses(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TopRatedMovies>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
