package com.arvind.moviezjcapp.data.repository

import androidx.paging.PagingData
import com.arvind.moviezjcapp.domain.models.*
import com.arvind.moviezjcapp.domain.models.responses.CastDetailsApiResponse
import com.arvind.moviezjcapp.domain.models.responses.GenresApiResponses
import com.arvind.moviezjcapp.utils.Resource
import com.arvind.moviezjcapp.domain.repository.DataStoreOperations
import com.arvind.moviezjcapp.domain.repository.LocalDataSource
import com.arvind.moviezjcapp.domain.repository.RemoteDataSource
import com.arvind.moviezjcapp.utils.FilmType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStoreOperations: DataStoreOperations
) {
    suspend fun getMoviesDetails(filmId: Int): Resource<MoviesDetails> {
        return remote.getMovieDetails(filmId = filmId)
    }

    suspend fun getTVDetails(filmId: Int): Resource<TvSeriesDetails> {
        return remote.getTVDetails(filmId = filmId)
    }

    suspend fun getMovieGenres(filmType: FilmType): Resource<GenresApiResponses> {
        return remote.getMovieGenres(filmType)
    }

    suspend fun getTvShowsGenres(): Resource<GenresApiResponses> {
        return remote.getTvShowsGenres()
    }

    fun getPopularMovies(): Flow<PagingData<Film>> {
        return remote.getPopularMovies()
    }

    fun getTopRatedMovies(filmType: FilmType): Flow<PagingData<Film>> {
        return remote.getTopRatedMovies(filmType = filmType)
    }

    fun getTrendingMovies(filmType: FilmType): Flow<PagingData<Film>> {
        return remote.getTrendingMovies(filmType = filmType)
    }

    fun getNowPlayingMovies(filmType: FilmType): Flow<PagingData<Film>> {
        return remote.getNowPlayingMovies(filmType = filmType)
    }

    fun getUpcomingMovies(): Flow<PagingData<Film>> {
        return remote.getUpcomingMovies()
    }

    fun getSimilarFilms(filmType: FilmType, filmId: Int): Flow<PagingData<Film>> {
        return remote.getSimilarFilm(filmType = filmType, filmId = filmId)
    }

    fun getTVAiringToday(): Flow<PagingData<Film>> {
        return remote.getTVAiringToday()
    }

    fun getOnTheAirTvSeries(): Flow<PagingData<Film>> {
        return remote.getOnTheAirTvSeries()
    }

    fun getTVTopRated(): Flow<PagingData<Film>> {
        return remote.getTVTopRated()
    }

    fun getTVPopular(): Flow<PagingData<Film>> {
        return remote.getTVPopular()
    }

    suspend fun getCastDetails(filmId: Int): Resource<CastDetailsApiResponse> {
        return remote.getCastDetails(filmId = filmId)
    }

    suspend fun getTvSeriesCredits(filmId: Int): Resource<CastDetailsApiResponse> {
        return remote.getTvSeriesCredits(filmId = filmId)
    }

    fun multiSearch(query: String,includeAdult: Boolean): Flow<PagingData<MultiSearch>> {
        return remote.multiSearch(query = query,includeAdult=includeAdult)
    }

    fun getMyList(): Flow<List<MyList>> {
        return local.getMyList()
    }

    fun getSelectedFromMyList(listId: Int): MyList {
        return local.getSelectedFromMyList(listId = listId)
    }

    suspend fun addToMyList(myList: MyList) {
        return local.addToMyList(myList = myList)
    }

    suspend fun ifExists(listId: Int): Int {
        return local.ifExists(listId)
    }

    suspend fun deleteOneFromMyList(myList: Int) {
        return local.deleteOneFromMyList(myList = myList)
    }

    suspend fun deleteAllContentFromMyList() {
        return local.deleteAllContentFromMyList()
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStoreOperations.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStoreOperations.readOnBoardingState()
    }

}