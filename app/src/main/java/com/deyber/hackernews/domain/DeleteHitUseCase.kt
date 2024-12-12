package com.deyber.hackernews.domain

import com.deyber.hackernews.core.network.Resource
import com.deyber.hackernews.data.repository.NewsRepository
import com.deyber.hackernews.di.repository.NewsRepositoryQualifier
import javax.inject.Inject


class DeleteHitUseCase @Inject constructor(
    @NewsRepositoryQualifier
    private val repository: NewsRepository
) {
    suspend operator fun invoke(id: Long, createAt: Long): Resource<Boolean> {
        return repository.deleteHit(id, createAt)
    }
}