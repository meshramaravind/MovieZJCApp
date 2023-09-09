package com.arvind.moviezjcapp.domain.use_cases

import com.arvind.moviezjcapp.domain.models.MyList
import com.arvind.moviezjcapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetMyListUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<MyList>> {
        return repository.getMyList()
    }
}