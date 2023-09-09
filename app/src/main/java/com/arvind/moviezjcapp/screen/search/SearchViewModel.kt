package com.arvind.moviezjcapp.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.arvind.moviezjcapp.data.repository.PrefsRepository
import com.arvind.moviezjcapp.domain.models.MultiSearch
import com.arvind.moviezjcapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases,
    private val prefsRepo: PrefsRepository
) : ViewModel() {

//    private val _searchResult = mutableStateOf<Flow<PagingData<MultiSearch>>>(emptyFlow())
//    val searchResult = _searchResult
//
////    val includeAdult: State<Flow<Boolean?>> = mutableStateOf(prefsRepo.readIncludeAdult())
//
//    var previousSearch = mutableStateOf("")
//
//    private val _searchQuery = mutableStateOf("")
//    val searchQuery = _searchQuery
//
//    val searchState = mutableStateOf(SearchState.READY_TO_DELETE)
//
//    fun updateSearchQuery(query: String) {
//        _searchQuery.value = query
//    }
//
//    fun searchAll(query: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _searchResult.value = useCases.multiSearchUseCase(query = query).map { pagingData ->
//                pagingData.filter {
//                    ((it.title != null || it.originalName != null || it.originalTitle != null) &&
//                            (it.mediaType == "tv" || it.mediaType == "movies"))
//                }
//            }.cachedIn(viewModelScope)
//        }
//    }


    private val _searchResult = mutableStateOf<Flow<PagingData<MultiSearch>>>(emptyFlow())
    val searchResultState: State<Flow<PagingData<MultiSearch>>> = _searchResult

    val includeAdult: State<Flow<Boolean?>> = mutableStateOf(prefsRepo.readIncludeAdult())

    var searchQuery = mutableStateOf("")
    var previousSearch = mutableStateOf("")
    var searchParamState: State<String> = searchQuery

    init {
        searchQuery.value = ""
    }

    fun searchAll(includeAdult: Boolean) {
        viewModelScope.launch {
            if (searchQuery.value.isNotEmpty()) {
                _searchResult.value = useCases.multiSearchUseCase(
                    query = searchQuery.value,
                    includeAdult
                ).map { result ->
                    result.filter {
                        ((it.title != null || it.originalName != null || it.originalTitle != null) &&
                                (it.mediaType == "tv" || it.mediaType == "movie"))
                    }
                }.cachedIn(viewModelScope)
            }
        }
    }
}