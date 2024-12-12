package com.deyber.hackernews.di.repository

import android.content.Context
import com.deyber.database.di.HitRepositoryQualifier
import com.deyber.database.repository.HitRepository
import com.deyber.hackernews.core.network.NetworkUtils
import com.deyber.hackernews.data.repository.NewsRepository
import com.deyber.hackernews.data.repository.NewsRepositoryImp
import com.deyber.hackernews.data.service.NewsService
import com.deyber.hackernews.di.network.NetworkUtilsQualifier
import com.deyber.hackernews.di.network.NewsServiceQualifier
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
    @NewsRepositoryQualifier
    fun provideNewsRepository(
        @ApplicationContext
        context: Context,
        @NewsServiceQualifier
        service: NewsService,
        @HitRepositoryQualifier
        hitRepository: HitRepository,
        @NetworkUtilsQualifier
        networkUtils: NetworkUtils

    ): NewsRepository = NewsRepositoryImp(context, service, hitRepository, networkUtils)
}
