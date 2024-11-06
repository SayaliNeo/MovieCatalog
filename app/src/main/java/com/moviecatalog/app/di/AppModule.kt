package com.moviecatalog.app.di

import com.moviecatalog.app.data.MovieDataSource
import com.moviecatalog.app.data.MovieDataSourceImpl
import com.moviecatalog.app.domain.repository.MovieDataRepoImpl
import com.moviecatalog.app.domain.repository.MovieRepository
import com.moviecatalog.app.domain.usecase.GetMovieInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideGetMovieDetailsUseCase(): GetMovieInfoUseCase = GetMovieInfoUseCase(
        provideMoviesRepository()
    )

    @Provides
    fun provideMoviesDataSource(): MovieDataSource = MovieDataSourceImpl()

    @Provides
    fun provideMoviesRepository(): MovieRepository = MovieDataRepoImpl(provideMoviesDataSource())
}
