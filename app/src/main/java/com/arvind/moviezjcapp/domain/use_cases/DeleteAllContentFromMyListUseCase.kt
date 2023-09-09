package com.arvind.moviezjcapp.domain.use_cases

import com.arvind.moviezjcapp.data.repository.Repository

class DeleteAllContentFromMyListUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        return repository.deleteAllContentFromMyList()
    }
}