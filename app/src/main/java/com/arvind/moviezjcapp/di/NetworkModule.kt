package com.arvind.moviezjcapp.di

import com.arvind.moviezjcapp.data.remote.MovieZJCAPI
import com.arvind.moviezjcapp.data.repository.RemoteDataSourceImp
import com.arvind.moviezjcapp.domain.repository.RemoteDataSource
import com.arvind.moviezjcapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(okHttpClient: OkHttpClient): MovieZJCAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieZJCAPI::class.java)
    }


    @Provides
    @Singleton
    fun providesRemoteDataSource(
        movieZJCAPI: MovieZJCAPI,
    ): RemoteDataSource {
        return RemoteDataSourceImp(
            movieZJCAPI = movieZJCAPI
        )
    }
}