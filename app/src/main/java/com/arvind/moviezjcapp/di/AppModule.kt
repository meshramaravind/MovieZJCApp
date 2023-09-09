package com.arvind.moviezjcapp.di

import android.content.Context
import androidx.room.Room
import com.arvind.moviezjcapp.data.local.MovieZJCDatabase
import com.arvind.moviezjcapp.data.repository.LocalDataSourceImp
import com.arvind.moviezjcapp.domain.repository.LocalDataSource
import com.arvind.moviezjcapp.utils.Constants.MOVIEZ_JC_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieZJCDatabase {
        return Room.databaseBuilder(
            context,
            MovieZJCDatabase::class.java,
            MOVIEZ_JC_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(movieZJCDatabase: MovieZJCDatabase): LocalDataSource {
        return LocalDataSourceImp(movieZJCDatabase = movieZJCDatabase)
    }
}