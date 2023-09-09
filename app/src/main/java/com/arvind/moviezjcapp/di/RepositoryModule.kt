package com.arvind.moviezjcapp.di

import android.app.Application
import android.content.Context
import com.arvind.moviezjcapp.data.preference.UserPreferences
import com.arvind.moviezjcapp.data.repository.DataStoreOperationsImp
import com.arvind.moviezjcapp.data.repository.Repository
import com.arvind.moviezjcapp.domain.repository.DataStoreOperations
import com.arvind.moviezjcapp.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImp(context = context)
    }

    @Provides
    @Singleton
    fun providesUseCases(repository: Repository): UseCases {
        return UseCases(
            addToMyListUseCase = AddToMyListUseCase(repository),
            deleteAllContentFromMyListUseCase = DeleteAllContentFromMyListUseCase(repository),
            deleteOneFromMyListUseCase = DeleteOneFromMyListUseCase(repository),
            castDetailsUseCase = CastDetailsUseCase(repository),
            getTvSeriesCastsUseCase = GetTvSeriesCastsUseCase(repository),
            getMovieGenresUseCase = GetMovieGenresUseCase(repository),
            getMyListUseCase = GetMyListUseCase(repository),
            getPopularMoviesUseCase = GetPopularMoviesUseCase(repository),
            getSelectedFromMyListUseCase = GetSelectedFromMyListUseCase(repository),
            getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(repository),
            getTVAiringTodayUseCase = GetTVAiringTodayUseCase(repository),
            getOnTheAirTvSeriesUseCase = GetOnTheAirTvSeriesUseCase(repository),
            getTVGenresUseCase = GetTVGenresUseCase(repository),
            getTVPopularUseCase = GetTVPopularUseCase(repository),
            getUpcomingMoviesUseCase = GetUpcomingMoviesUseCase(repository),
            multiSearchUseCase = MultiSearchUseCase(repository),
            getTvTopRatedUseCase = TVTopRatedUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            getMoviesDetailsUseCase = GetMoviesDetailsUseCase(repository),
            getTVDetailsUseCase = GetTVDetailsUseCase(repository),
            getTrendingMoviesUseCase = GetTrendingMoviesUseCase(repository),
            getNowPayingMoviesUseCase = GetNowPayingMoviesUseCase(repository),
            getSimilarFilmUseCase = GetSimilarFilmUseCase(repository),
            ifExistsUseCase = IfExistsUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun providesDataStore(application: Application): UserPreferences {
        return UserPreferences(application.applicationContext)
    }
}