package com.arvind.moviezjcapp.domain.use_cases

import com.arvind.moviezjcapp.domain.models.MyList
import com.arvind.moviezjcapp.data.repository.Repository

class GetSelectedFromMyListUseCase(
    private val repository: Repository
) {
    operator fun invoke(listId: Int): MyList {
        return repository.getSelectedFromMyList(listId = listId)
    }
}