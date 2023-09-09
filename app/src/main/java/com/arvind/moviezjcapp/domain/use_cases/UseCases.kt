package com.arvind.moviezjcapp.domain.use_cases

class UseCases(
    val getTVDetailsUseCase: GetTVDetailsUseCase,
    val getMoviesDetailsUseCase: GetMoviesDetailsUseCase,
    val addToMyListUseCase: AddToMyListUseCase,
    val deleteAllContentFromMyListUseCase: DeleteAllContentFromMyListUseCase,
    val deleteOneFromMyListUseCase: DeleteOneFromMyListUseCase,
    val castDetailsUseCase: CastDetailsUseCase,
    val getMovieGenresUseCase: GetMovieGenresUseCase,
    val getTvSeriesCastsUseCase: GetTvSeriesCastsUseCase,
    val getMyListUseCase: GetMyListUseCase,
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val getSelectedFromMyListUseCase: GetSelectedFromMyListUseCase,
    val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    val getSimilarFilmUseCase: GetSimilarFilmUseCase,
    val getTVAiringTodayUseCase: GetTVAiringTodayUseCase,
    val getOnTheAirTvSeriesUseCase: GetOnTheAirTvSeriesUseCase,
    val getTVGenresUseCase: GetTVGenresUseCase,
    val getTVPopularUseCase: GetTVPopularUseCase,
    val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    val multiSearchUseCase: MultiSearchUseCase,
    val getTvTopRatedUseCase: TVTopRatedUseCase,
    val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    val getNowPayingMoviesUseCase: GetNowPayingMoviesUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val ifExistsUseCase: IfExistsUseCase
)