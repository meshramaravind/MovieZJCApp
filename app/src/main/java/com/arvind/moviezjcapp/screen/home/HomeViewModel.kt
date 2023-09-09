package com.arvind.moviezjcapp.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.arvind.moviezjcapp.domain.models.*
import com.arvind.moviezjcapp.domain.use_cases.UseCases
import com.arvind.moviezjcapp.utils.FilmType
import com.arvind.moviezjcapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private var _filmGenres = mutableStateListOf(Genres(null, "All"))
    val filmGenres: SnapshotStateList<Genres> = _filmGenres

    var selectedGenre: MutableState<Genres> = mutableStateOf(Genres(null, "All"))
    var selectedFilmType: MutableState<FilmType> = mutableStateOf(FilmType.MOVIE)

    /**
     * Movies states
     */

    private var _getTrendingMovies = mutableStateOf<Flow<PagingData<Film>>>(emptyFlow())
    val trendingMovies: State<Flow<PagingData<Film>>> = _getTrendingMovies

    private val _getUpcomingMovies = mutableStateOf<Flow<PagingData<Film>>>(emptyFlow())
    val upcomingMovies: State<Flow<PagingData<Film>>> = _getUpcomingMovies

    private val _topRatedMovies = mutableStateOf<Flow<PagingData<Film>>>(emptyFlow())
    val topRatedMovies: State<Flow<PagingData<Film>>> = _topRatedMovies

    private val _getNowPayingMovies = mutableStateOf<Flow<PagingData<Film>>>(emptyFlow())
    val nowPlayingMovies: State<Flow<PagingData<Film>>> = _getNowPayingMovies

    private val _getPopularMovies = mutableStateOf<Flow<PagingData<Film>>>(emptyFlow())
    val popularMovies: State<Flow<PagingData<Film>>> = _getPopularMovies

    private val _getMovieGenres = mutableStateOf<List<Genres>>(emptyList())
    val movieGenres: State<List<Genres>> = _getMovieGenres

    /**
     * Tv Series states
     */

    private val _getTVTopRated = mutableStateOf<Flow<PagingData<Film>>>(emptyFlow())
    val getTVTopRated: State<Flow<PagingData<Film>>> = _getTVTopRated

    private val _getOnAirTvSeries = mutableStateOf<Flow<PagingData<Film>>>(emptyFlow())
    val onAirTvSeries: State<Flow<PagingData<Film>>> = _getOnAirTvSeries

    private val _getTvPopularSeries = mutableStateOf<Flow<PagingData<Film>>>(emptyFlow())
    val tvPopular: State<Flow<PagingData<Film>>> = _getTvPopularSeries

    private val _getAiringTodayTvSeries =
        mutableStateOf<Flow<PagingData<Film>>>(emptyFlow())
    val tvAiringToday: State<Flow<PagingData<Film>>> = _getAiringTodayTvSeries

    private val _getTVGenres = mutableStateOf<List<Genres>>(emptyList())
    val tvGenres: State<List<Genres>> = _getTVGenres

    private val _tvSeriesGenres = mutableStateOf<List<Genres>>(emptyList())
    val tvSeriesGenres: State<List<Genres>> = _tvSeriesGenres


    init {
        refreshAll()
    }

    fun refreshAll(
        genreId: Int? = selectedGenre.value.id,
        filmType: FilmType = selectedFilmType.value
    ) {
        if (filmGenres.size == 1) {
            getFilmGenre(selectedFilmType.value)
        }
        if (genreId == null) {
            selectedGenre.value = Genres(null, "All")
        }
        getTrendingMovies(genreId, filmType)
        getNowPayingMovies(genreId, filmType)
        getUpComingMovies(genreId)
        getTopRatedMovies(genreId, filmType)
        getPopularMovies(genreId)
//        getMovieGenres()

        getAiringTodayTvSeries(genreId)
        getOnTheAirTvSeries(genreId)
        getTVPopularSeries(genreId)
        getTVTopRatedSeries(genreId)
//        getSeriesGenres()
    }

    fun filterBySetSelectedCategories(genre: Genres) {
        selectedGenre.value = genre
        refreshAll(genre.id)
    }

    /**
     * Movies
     */

    fun getTrendingMovies(genreId: Int?, filmType: FilmType) {
        viewModelScope.launch {
            _getTrendingMovies.value = if (genreId != null) {
                useCases.getTrendingMoviesUseCase(filmType = filmType).map { pagingData ->
                    pagingData.filter {
                        it.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                useCases.getTrendingMoviesUseCase(filmType).cachedIn(viewModelScope)
            }
        }
    }

    fun getNowPayingMovies(genreId: Int?, filmType: FilmType) {
        viewModelScope.launch {
            _getNowPayingMovies.value = if (genreId != null) {
                useCases.getNowPayingMoviesUseCase(filmType = filmType).map { pagingData ->
                    pagingData.filter {
                        it.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                useCases.getNowPayingMoviesUseCase(filmType).cachedIn(viewModelScope)
            }
        }
    }

    fun getUpComingMovies(genreId: Int?) {
        viewModelScope.launch {
            _getUpcomingMovies.value = if (genreId != null) {
                useCases.getUpcomingMoviesUseCase().map { pagingData ->
                    pagingData.filter {
                        it.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                useCases.getUpcomingMoviesUseCase().cachedIn(viewModelScope)
            }
        }
    }

    fun getTopRatedMovies(genreId: Int?, filmType: FilmType) {
        viewModelScope.launch {
            _topRatedMovies.value = if (genreId != null) {
                useCases.getTopRatedMoviesUseCase(filmType = filmType).map { pagingData ->
                    pagingData.filter {
                        it.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                useCases.getTopRatedMoviesUseCase(filmType).cachedIn(viewModelScope)
            }
        }
    }

    fun getPopularMovies(genreId: Int?) {
        viewModelScope.launch {
            _getPopularMovies.value = if (genreId != null) {
                useCases.getPopularMoviesUseCase().map { pagingData ->
                    pagingData.filter {
                        it.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                useCases.getPopularMoviesUseCase().cachedIn(viewModelScope)
            }
        }
    }

    fun getFilmGenre(filmType: FilmType = selectedFilmType.value) {
        viewModelScope.launch {
            val defaultGenre = Genres(null, "All")
            when (val results = useCases.getMovieGenresUseCase(filmType)) {
                is Resource.Success -> {
                    _filmGenres.clear()
                    _filmGenres.add(defaultGenre)
                    selectedGenre.value = defaultGenre
                    results.data?.genres?.forEach {
                        _filmGenres.add(it)
                    }
                }
                is Resource.Error -> {
                    Timber.e("Error loading Genres")
                }
                else -> {}
            }
        }

    }

    /**
     * Tv Series
     */

    fun getTVTopRatedSeries(genreId: Int?) {
        viewModelScope.launch {
            _getTVTopRated.value = if (genreId != null) {
                useCases.getTvTopRatedUseCase().map { pagingData ->
                    pagingData.filter {
                        it.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                useCases.getTvTopRatedUseCase().cachedIn(viewModelScope)
            }
        }
    }

    fun getTVPopularSeries(genreId: Int?) {
        viewModelScope.launch {
            _getTvPopularSeries.value = if (genreId != null) {
                useCases.getTVPopularUseCase().map { pagingData ->
                    pagingData.filter {
                        it.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                useCases.getTVPopularUseCase().cachedIn(viewModelScope)
            }
        }
    }

    fun getAiringTodayTvSeries(genreId: Int?) {
        viewModelScope.launch {
            _getAiringTodayTvSeries.value = if (genreId != null) {
                useCases.getTVAiringTodayUseCase().map { pagingData ->
                    pagingData.filter {
                        it.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                useCases.getTVAiringTodayUseCase().cachedIn(viewModelScope)
            }
        }
    }

    fun getOnTheAirTvSeries(genreId: Int?) {
        viewModelScope.launch {
            _getOnAirTvSeries.value = if (genreId != null) {
                useCases.getOnTheAirTvSeriesUseCase().map { pagingData ->
                    pagingData.filter {
                        it.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                useCases.getOnTheAirTvSeriesUseCase().cachedIn(viewModelScope)
            }
        }
    }
}