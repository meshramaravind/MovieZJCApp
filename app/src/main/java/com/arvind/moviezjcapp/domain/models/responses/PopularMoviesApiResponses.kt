package com.arvind.moviezjcapp.domain.models.responses

import com.arvind.moviezjcapp.domain.models.PopularMovies
import com.google.gson.annotations.SerializedName

data class PopularMoviesApiResponses(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val searches: List<PopularMovies>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
