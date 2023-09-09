package com.arvind.moviezjcapp.domain.use_cases

import com.arvind.moviezjcapp.data.repository.Repository
import com.arvind.moviezjcapp.domain.models.responses.CastDetailsApiResponse
import com.arvind.moviezjcapp.utils.Resource

class CastDetailsUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(filmId: Int): Resource<CastDetailsApiResponse> {
        return repository.getCastDetails(filmId = filmId)
    }
}