package com.arvind.moviezjcapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arvind.moviezjcapp.data.remote.MovieZJCAPI
import com.arvind.moviezjcapp.domain.models.Film
import com.arvind.moviezjcapp.utils.FilmType
import retrofit2.HttpException
import java.io.IOException

class SimilarFilmSource(
    private val movieZJCAPI: MovieZJCAPI,
    val filmId: Int,
    private val filmType: FilmType
) :
    PagingSource<Int, Film>() {
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val nextPage = params.key ?: 1
            val similarMovies = if (filmType == FilmType.MOVIE) movieZJCAPI.getSimilarMovies(
                page = nextPage, filmId = filmId
            )
            else movieZJCAPI.getSimilarTvShows(page = nextPage, filmId = filmId)

            LoadResult.Page(
                data = similarMovies.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (similarMovies.results.isEmpty()) null else similarMovies.page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}