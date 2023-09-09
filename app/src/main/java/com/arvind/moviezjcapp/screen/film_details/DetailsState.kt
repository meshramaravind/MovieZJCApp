package com.arvind.moviezjcapp.screen.film_details

sealed class DetailsState{
    object Loading : DetailsState()
    data class Success(val data: Any) : DetailsState()
    data class Error(val error: String) : DetailsState()
}
