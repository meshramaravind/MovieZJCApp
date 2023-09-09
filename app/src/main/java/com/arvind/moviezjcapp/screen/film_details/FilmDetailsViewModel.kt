package com.arvind.moviezjcapp.screen.film_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arvind.moviezjcapp.domain.models.Film
import com.arvind.moviezjcapp.domain.models.MoviesDetails
import com.arvind.moviezjcapp.domain.models.TvSeriesDetails
import com.arvind.moviezjcapp.domain.models.responses.CastDetailsApiResponse
import com.arvind.moviezjcapp.domain.use_cases.UseCases
import com.arvind.moviezjcapp.utils.FilmType
import com.arvind.moviezjcapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _getSimilarFilm = mutableStateOf<Flow<PagingData<Film>>>(emptyFlow())
    val similarFilm: State<Flow<PagingData<Film>>> = _getSimilarFilm


    fun getSimilarFilm(filmId: Int?, filmType: FilmType) {
        viewModelScope.launch {
            useCases.getSimilarFilmUseCase(filmType, filmId!!).also {
                _getSimilarFilm.value = it
            }.cachedIn(viewModelScope)
        }
    }


    suspend fun getMovieDetails(filmId: Int): Resource<MoviesDetails> {
        val result = useCases.getMoviesDetailsUseCase(filmId)
        Timber.d(result.data.toString())
        return result
    }

    suspend fun getTvSeriesDetails(tvId: Int): Resource<TvSeriesDetails> {
        val result = useCases.getTVDetailsUseCase(tvId)
        Timber.d(result.data.toString())
        return result
    }

    suspend fun getMovieCasts(filmId: Int): Resource<CastDetailsApiResponse> {
        val result = useCases.castDetailsUseCase(filmId = filmId)
        Timber.d(result.data.toString())
        return result

    }


    suspend fun getTvSeriesCasts(filmId: Int): Resource<CastDetailsApiResponse> {
        val result = useCases.castDetailsUseCase(filmId = filmId)
        Timber.d(result.data.toString())
        return result

    }

}