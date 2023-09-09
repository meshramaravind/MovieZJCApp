package com.arvind.moviezjcapp.domain.use_cases

import com.arvind.moviezjcapp.domain.models.responses.GenresApiResponses
import com.arvind.moviezjcapp.utils.Resource
import com.arvind.moviezjcapp.data.repository.Repository

class GetTVGenresUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): Resource<GenresApiResponses> {
        return repository.getTvShowsGenres()
    }
}