package com.sonder.codechallenge.di

import com.sonder.data.repositories.SearchRepository
import com.sonder.data.repositories.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository(): SearchRepository {
        return SearchRepositoryImpl()
    }

}