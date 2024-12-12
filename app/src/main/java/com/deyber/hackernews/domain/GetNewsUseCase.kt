package com.deyber.hackernews.domain

import com.deyber.hackernews.core.network.Resource
import com.deyber.hackernews.data.repository.NewsRepository
import com.deyber.hackernews.di.repository.NewsRepositoryQualifier
import com.deyber.hackernews.domain.model.ui.HitModel
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    @NewsRepositoryQualifier
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): Resource<List<HitModel>> {
        return repository.getHits()
    }
}