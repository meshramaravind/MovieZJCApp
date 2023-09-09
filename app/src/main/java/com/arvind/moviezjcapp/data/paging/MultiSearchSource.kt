package com.arvind.moviezjcapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arvind.moviezjcapp.data.remote.MovieZJCAPI
import com.arvind.moviezjcapp.domain.models.MultiSearch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MultiSearchSource @Inject constructor(
    private val movieZJCAPI: MovieZJCAPI,
    private val query: String,
    private val includeAdult: Boolean
) : PagingSource<Int, MultiSearch>() {
    override fun getRefreshKey(state: PagingState<Int, MultiSearch>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MultiSearch> {
        return try {
            val nextPage = params.key ?: 1
            val multiSearch =
                movieZJCAPI.multiSearch(query = query, includeAdult = includeAdult, nextPage)
            LoadResult.Page(
                data = multiSearch.searches,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (multiSearch.searches.isEmpty()) null else multiSearch.page + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}