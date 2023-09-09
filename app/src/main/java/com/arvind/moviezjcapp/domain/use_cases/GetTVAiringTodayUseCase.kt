package com.arvind.moviezjcapp.domain.use_cases

import androidx.paging.PagingData
import com.arvind.moviezjcapp.domain.models.TVAiringToday
import com.arvind.moviezjcapp.data.repository.Repository
import com.arvind.moviezjcapp.domain.models.Film
import kotlinx.coroutines.flow.Flow

class GetTVAiringTodayUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Film>>{
        return repository.getTVAiringToday()
    }
}

