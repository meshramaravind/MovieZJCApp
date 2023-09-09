package com.arvind.moviezjcapp.domain.use_cases

import com.arvind.moviezjcapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: Repository
) {
    operator fun invoke():Flow<Boolean>{
        return repository.readOnBoardingState()
    }
}