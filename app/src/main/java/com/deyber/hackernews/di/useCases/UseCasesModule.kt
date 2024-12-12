package com.deyber.hackernews.di.useCases

import com.deyber.hackernews.data.repository.NewsRepository
import com.deyber.hackernews.di.repository.NewsRepositoryQualifier
import com.deyber.hackernews.domain.DeleteHitUseCase
import com.deyber.hackernews.domain.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    @GetNewsUseCasesQualifier
    fun providesGetNewsUseCase(
        @NewsRepositoryQualifier
        repository: NewsRepository
    ): GetNewsUseCase = GetNewsUseCase(repository)

    @Provides
    @Singleton
    @DeleteHitUseCasesQualifier
    fun providesDeleteHitUseCase(
        @NewsRepositoryQualifier
        repository: NewsRepository
    ): DeleteHitUseCase = DeleteHitUseCase(repository)
}
