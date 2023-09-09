package com.arvind.moviezjcapp.domain.use_cases

import com.arvind.moviezjcapp.domain.models.TvSeriesDetails
import com.arvind.moviezjcapp.utils.Resource
import com.arvind.moviezjcapp.data.repository.Repository

class GetTVDetailsUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(filmId: Int): Resource<TvSeriesDetails> {
        return repository.getTVDetails(filmId)
    }
}