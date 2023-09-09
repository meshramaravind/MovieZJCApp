package com.arvind.moviezjcapp.domain.models.responses

import com.arvind.moviezjcapp.domain.models.TVAiringToday
import com.google.gson.annotations.SerializedName

data class TVAiringTodayApiResponses(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TVAiringToday>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)