package com.arvind.moviezjcapp.domain.use_cases

import com.arvind.moviezjcapp.data.repository.Repository

class DeleteOneFromMyListUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(myList: Int) {
        return repository.deleteOneFromMyList(myList = myList)
    }
}