package com.arvind.moviezjcapp.domain.use_cases

import com.arvind.moviezjcapp.domain.models.responses.GenresApiResponses
import com.arvind.moviezjcapp.utils.Resource
import com.arvind.moviezjcapp.data.repository.Repository
import com.arvind.moviezjcapp.utils.FilmType

class GetMovieGenresUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(filmType:FilmType): Resource<GenresApiResponses> {
        return repository.getMovieGenres(filmType)
    }
}