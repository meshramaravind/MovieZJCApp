package com.arvind.moviezjcapp.domain.use_cases

import androidx.paging.PagingData
import com.arvind.moviezjcapp.data.repository.Repository
import com.arvind.moviezjcapp.domain.models.Film
import com.arvind.moviezjcapp.utils.FilmType
import kotlinx.coroutines.flow.Flow

class GetSimilarFilmUseCase(
    private val repository: Repository
) {
    operator fun invoke(filmType: FilmType,filmId: Int): Flow<PagingData<Film>> {
        return repository.getSimilarFilms(filmType,filmId)
    }
}