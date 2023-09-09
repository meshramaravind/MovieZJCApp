package com.arvind.moviezjcapp.domain.models.responses

import com.arvind.moviezjcapp.domain.models.MultiSearch
import com.google.gson.annotations.SerializedName

data class MultiSearchApiResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val searches: List<MultiSearch>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
