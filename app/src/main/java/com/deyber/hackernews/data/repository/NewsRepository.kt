package com.deyber.hackernews.data.repository

import com.deyber.hackernews.core.network.Resource
import com.deyber.hackernews.domain.model.ui.HitModel

interface NewsRepository {
    @Throws(Exception::class)
    suspend fun getHits(): Resource<List<HitModel>>
    suspend fun deleteHit(id: Long, createAt: Long): Resource<Boolean>
}