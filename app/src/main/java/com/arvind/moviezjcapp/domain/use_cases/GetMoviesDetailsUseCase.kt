package com.arvind.moviezjcapp.domain.use_cases

import com.arvind.moviezjcapp.domain.models.MoviesDetails
import com.arvind.moviezjcapp.utils.Resource
import com.arvind.moviezjcapp.data.repository.Repository

class GetMoviesDetailsUseCase(
    val repository: Repository
) {
    suspend operator fun invoke(filmId: Int): Resource<MoviesDetails> {
        return repository.getMoviesDetails(filmId)
    }
}