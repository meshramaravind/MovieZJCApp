package com.arvind.moviezjcapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arvind.moviezjcapp.data.remote.MovieZJCAPI
import com.arvind.moviezjcapp.domain.models.Film
import com.arvind.moviezjcapp.domain.models.TVPopular
import retrofit2.HttpException
import java.io.IOException

class TrendingSeriesSource(private val movieZJCAPI: MovieZJCAPI) :
    PagingSource<Int, Film>() {
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val nextPage = params.key ?: 1
            val trendingSeriesList = movieZJCAPI.getTrendingTvSeries(nextPage)
            LoadResult.Page(
                data = trendingSeriesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (trendingSeriesList.results.isEmpty()) null else trendingSeriesList.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}