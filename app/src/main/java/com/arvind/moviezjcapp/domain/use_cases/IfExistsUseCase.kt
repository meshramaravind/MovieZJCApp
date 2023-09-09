package com.arvind.moviezjcapp.domain.use_cases

import com.arvind.moviezjcapp.data.repository.Repository

class IfExistsUseCase(
    private val repository: Repository
) {
     suspend operator fun invoke(listId:Int):Int {
        return repository.ifExists(listId)
    }
}