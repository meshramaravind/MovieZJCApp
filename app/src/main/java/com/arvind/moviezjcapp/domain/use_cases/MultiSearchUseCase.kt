package com.arvind.moviezjcapp.domain.use_cases

import androidx.paging.PagingData
import com.arvind.moviezjcapp.domain.models.MultiSearch
import com.arvind.moviezjcapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class MultiSearchUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String,includeAdult: Boolean): Flow<PagingData<MultiSearch>> {
        return repository.multiSearch(query = query,includeAdult=includeAdult)
    }
}