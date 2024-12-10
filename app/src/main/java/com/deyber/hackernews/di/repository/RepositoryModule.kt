package com.deyber.hackernews.di.repository

import com.deyber.hackernews.data.repository.NewsRepository
import com.deyber.hackernews.data.repository.NewsRepositoryImp
import com.deyber.hackernews.data.service.NewsService
import com.deyber.hackernews.di.network.NewsServiceQualifier
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
    @NewsRepositoryQualifier
    fun provideNewsRepository(
        @NewsServiceQualifier
        service: NewsService
    ): NewsRepository = NewsRepositoryImp(service)
}