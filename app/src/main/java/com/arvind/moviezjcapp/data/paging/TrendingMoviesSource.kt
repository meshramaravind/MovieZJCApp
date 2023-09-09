package com.arvind.moviezjcapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arvind.moviezjcapp.data.remote.MovieZJCAPI
import com.arvind.moviezjcapp.domain.models.Film
import com.arvind.moviezjcapp.domain.models.TopRatedMovies
import com.arvind.moviezjcapp.utils.FilmType
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TrendingMoviesSource @Inject constructor(
    private val movieZJCAPI: MovieZJCAPI,
    private val filmType: FilmType,
) : PagingSource<Int, Film>() {
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val nextPage = params.key ?: 1
            val trendingFilms =
                if (filmType == FilmType.MOVIE) movieZJCAPI.getTrendingTodayMovies(page = nextPage)
                else movieZJCAPI.getTrendingTvSeries(page = nextPage)
            LoadResult.Page(
                data = trendingFilms.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (trendingFilms.results.isEmpty()) null else trendingFilms.page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}