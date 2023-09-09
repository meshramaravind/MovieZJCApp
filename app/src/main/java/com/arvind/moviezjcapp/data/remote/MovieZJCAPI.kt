package com.arvind.moviezjcapp.data.remote

import com.arvind.moviezjcapp.BuildConfig
import com.arvind.moviezjcapp.domain.models.MoviesDetails
import com.arvind.moviezjcapp.domain.models.TvSeriesDetails
import com.arvind.moviezjcapp.domain.models.responses.*
import com.arvind.moviezjcapp.utils.Constants.STARTING_PAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieZJCAPI {

    /** **Movies** */

    @GET("trending/movie/day")
    suspend fun getTrendingTodayMovies(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): FilmResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") api_key: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): GenresApiResponses

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") api_key: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en",
    ): FilmResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): FilmResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") api_key: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en",
    ): FilmResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") api_key: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en",
    ): FilmResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") filmId: Int,
        @Query("api_key") apiKey: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): MoviesDetails

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") filmId: Int,
        @Query("api_key") api_key: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en",
    ): CastDetailsApiResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") filmId: Int,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): FilmResponse


    /** **Tv Shows** */

    @GET("tv/{tv_id}")
    suspend fun getTVDetails(
        @Path("tv_id") filmId: Int,
        @Query("api_key") apiKey: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): TvSeriesDetails

    @GET("genre/tv/list")
    suspend fun getTvShowsGenres(
        @Query("api_key") api_key: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): GenresApiResponses

    @GET("tv/airing_today")
    suspend fun getTVAiringToday(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") api_key: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en",
    ): FilmResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTvSeries(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): FilmResponse

    @GET("tv/top_rated")
    suspend fun getTVTopRated(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") api_key: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): FilmResponse

    @GET("tv/popular")
    suspend fun getTVPopular(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") api_key: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): FilmResponse

    @GET("trending/tv/day")
    suspend fun getTrendingTvSeries(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") apiKey: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): FilmResponse


    @GET("tv/{tv_id}/credits")
    suspend fun getTvSeriesCredits(
        @Path("tv_id") tvSeriesId: Int,
        @Query("api_key") apiKey: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): CastDetailsApiResponse

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTvShows(
        @Path("tv_id") filmId: Int,
        @Query("page") page: Int = 0,
        @Query("api_key") apiKey: String = BuildConfig.MOVIEZ_API_KEY,
        @Query("language") language: String = "en"
    ): FilmResponse

    @GET("search/multi")
    suspend fun multiSearch(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = true,
        @Query("page") page: Int = STARTING_PAGE,
        @Query("api_key") api_key: String = BuildConfig.MOVIEZ_API_KEY,
    ): MultiSearchApiResponse
}