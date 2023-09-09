package com.arvind.moviezjcapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arvind.moviezjcapp.data.remote.MovieZJCAPI
import com.arvind.moviezjcapp.domain.models.Film
import com.arvind.moviezjcapp.domain.models.TVPopular
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TVPopularSource @Inject constructor(
    private val movieZJCAPI: MovieZJCAPI
) : PagingSource<Int, Film>() {
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val nextPage = params.key ?: 1
            val tvPopular = movieZJCAPI.getTVPopular(nextPage)
            LoadResult.Page(
                data = tvPopular.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (tvPopular.results.isEmpty()) null else tvPopular.page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}