package com.arvind.moviezjcapp.domain.use_cases

import androidx.paging.PagingData
import com.arvind.moviezjcapp.data.repository.Repository
import com.arvind.moviezjcapp.domain.models.Film
import com.arvind.moviezjcapp.domain.models.TopRatedMovies
import com.arvind.moviezjcapp.utils.FilmType
import kotlinx.coroutines.flow.Flow

class GetNowPayingMoviesUseCase(
    private val repository: Repository
) {
    operator fun invoke(filmType: FilmType): Flow<PagingData<Film>> {
        return repository.getNowPlayingMovies(filmType = filmType)
    }
}