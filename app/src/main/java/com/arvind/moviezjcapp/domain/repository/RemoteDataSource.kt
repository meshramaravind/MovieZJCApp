package com.arvind.moviezjcapp.domain.repository

import androidx.paging.PagingData
import com.arvind.moviezjcapp.domain.models.*
import com.arvind.moviezjcapp.domain.models.responses.CastDetailsApiResponse
import com.arvind.moviezjcapp.domain.models.responses.GenresApiResponses
import com.arvind.moviezjcapp.utils.FilmType
import com.arvind.moviezjcapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getMovieDetails(filmId: Int): Resource<MoviesDetails>
    suspend fun getTVDetails(filmId: Int): Resource<TvSeriesDetails>
    suspend fun getCastDetails(filmId: Int): Resource<CastDetailsApiResponse>
    suspend fun getTvSeriesCredits(filmId: Int): Resource<CastDetailsApiResponse>

    suspend fun getMovieGenres(filmType: FilmType): Resource<GenresApiResponses>
    suspend fun getTvShowsGenres(): Resource<GenresApiResponses>


    fun getPopularMovies(): Flow<PagingData<Film>>
    fun getTopRatedMovies(filmType: FilmType): Flow<PagingData<Film>>
    fun getTrendingMovies(filmType: FilmType): Flow<PagingData<Film>>
    fun getNowPlayingMovies(filmType: FilmType): Flow<PagingData<Film>>
    fun getUpcomingMovies(): Flow<PagingData<Film>>
    fun getSimilarFilm(filmType: FilmType, filmId: Int): Flow<PagingData<Film>>


    fun getTVAiringToday(): Flow<PagingData<Film>>
    fun getOnTheAirTvSeries(): Flow<PagingData<Film>>
    fun getTVTopRated(): Flow<PagingData<Film>>
    fun getTVPopular(): Flow<PagingData<Film>>

    fun multiSearch(query: String,includeAdult: Boolean): Flow<PagingData<MultiSearch>>
}