package com.arvind.moviezjcapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arvind.moviezjcapp.data.paging.*
import com.arvind.moviezjcapp.data.remote.MovieZJCAPI
import com.arvind.moviezjcapp.domain.models.*
import com.arvind.moviezjcapp.domain.models.responses.CastDetailsApiResponse
import com.arvind.moviezjcapp.domain.models.responses.GenresApiResponses
import com.arvind.moviezjcapp.domain.repository.RemoteDataSource
import com.arvind.moviezjcapp.utils.Constants.ITEMS_PER_PAGE
import com.arvind.moviezjcapp.utils.FilmType
import com.arvind.moviezjcapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class RemoteDataSourceImp(
    private val movieZJCAPI: MovieZJCAPI
) : RemoteDataSource {
    override suspend fun getMovieDetails(filmId: Int): Resource<MoviesDetails> {
        val response = try {
            movieZJCAPI.getMovieDetails(filmId)
        } catch (e: Exception) {
            return Resource.Error("Unknown Error")
        }

        return Resource.Success(response)
    }

    override suspend fun getTVDetails(filmId: Int): Resource<TvSeriesDetails> {
        val response = try {
            movieZJCAPI.getTVDetails(filmId)
        } catch (e: Exception) {
            return Resource.Error("Unknown Error")
        }
        return Resource.Success(response)
    }

    override suspend fun getMovieGenres(filmType: FilmType): Resource<GenresApiResponses> {
        val response = try {
            if (filmType == FilmType.MOVIE) movieZJCAPI.getMovieGenres() else movieZJCAPI.getTvShowsGenres()
        } catch (e: java.lang.Exception) {
            return Resource.Error("Unknown error occurred!")
        }
        return Resource.Success(response)
    }

    override suspend fun getTvShowsGenres(): Resource<GenresApiResponses> {
        val response = try {
            movieZJCAPI.getTvShowsGenres()
        } catch (e: Exception) {
            return Resource.Error("Unknown Error")
        }
        return Resource.Success(response)
    }

    override fun getPopularMovies(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                PopularMoviesSource(movieZJCAPI = movieZJCAPI)
            }
        ).flow
    }

    override fun getTopRatedMovies(filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                TopRatedMoviesSource(movieZJCAPI = movieZJCAPI, filmType = filmType)
            }
        ).flow
    }

    override fun getTrendingMovies(filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                TrendingMoviesSource(movieZJCAPI = movieZJCAPI, filmType = filmType)
            }
        ).flow
    }

    override fun getNowPlayingMovies(filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                NowPlayingMoviesSource(movieZJCAPI = movieZJCAPI, filmType = filmType)
            }
        ).flow
    }

    override fun getUpcomingMovies(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                UpcomingMoviesSource(movieZJCAPI = movieZJCAPI)
            }
        ).flow
    }

    override fun getSimilarFilm(filmType: FilmType, filmId: Int): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SimilarFilmSource(movieZJCAPI = movieZJCAPI, filmId = filmId, filmType = filmType)
            }
        ).flow
    }

    override fun getTVAiringToday(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                TVAiringTodaySource(movieZJCAPI = movieZJCAPI)
            }
        ).flow
    }

    override fun getOnTheAirTvSeries(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                OnTheAirSeriesSource(movieZJCAPI = movieZJCAPI)
            }
        ).flow
    }

    override fun getTVTopRated(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                TVTopRatedSource(movieZJCAPI = movieZJCAPI)
            }
        ).flow
    }

    override fun getTVPopular(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                TVPopularSource(movieZJCAPI = movieZJCAPI)
            }
        ).flow
    }


    override suspend fun getCastDetails(filmId: Int): Resource<CastDetailsApiResponse> {
        val response = try {
            movieZJCAPI.getMovieCredits(filmId = filmId)
        } catch (e: Exception) {
            return Resource.Error("Unexpected Error")
        }
        Log.d("MovieCredits", "$response")
        return Resource.Success(response)
    }

    override suspend fun getTvSeriesCredits(filmId: Int): Resource<CastDetailsApiResponse> {
        val response = try {
            movieZJCAPI.getTvSeriesCredits(tvSeriesId = filmId)
        } catch (e: Exception) {
            return Resource.Error("Unexpected Error")
        }
        Log.d("MovieCredits", "$response")
        return Resource.Success(response)
    }

    override fun multiSearch(query: String, includeAdult: Boolean): Flow<PagingData<MultiSearch>> {
        val pagerResult = Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                MultiSearchSource(
                    movieZJCAPI = movieZJCAPI,
                    query = query,
                    includeAdult = includeAdult
                )
            }
        ).flow
        return pagerResult
    }
}