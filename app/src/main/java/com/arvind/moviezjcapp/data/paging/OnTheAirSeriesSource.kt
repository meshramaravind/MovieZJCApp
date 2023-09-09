package com.arvind.moviezjcapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arvind.moviezjcapp.data.remote.MovieZJCAPI
import com.arvind.moviezjcapp.domain.models.Film
import com.arvind.moviezjcapp.domain.models.TVAiringToday
import retrofit2.HttpException
import java.io.IOException

class OnTheAirSeriesSource(private val movieZJCAPI: MovieZJCAPI) :
    PagingSource<Int, Film>() {
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val nextPage = params.key ?: 1
            val onAirSeries = movieZJCAPI.getOnTheAirTvSeries(nextPage)
            LoadResult.Page(
                data = onAirSeries.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (onAirSeries.results.isEmpty()) null else onAirSeries.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}