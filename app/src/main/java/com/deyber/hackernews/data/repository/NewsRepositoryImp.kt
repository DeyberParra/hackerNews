package com.deyber.hackernews.data.repository

import android.content.Context
import com.deyber.database.data.doSuccess
import com.deyber.database.di.HitRepositoryQualifier
import com.deyber.database.repository.HitRepository
import com.deyber.hackernews.R
import com.deyber.hackernews.core.network.NetworkUtils
import com.deyber.hackernews.core.network.Resource
import com.deyber.hackernews.data.service.NewsService
import com.deyber.hackernews.di.network.NetworkUtilsQualifier
import com.deyber.hackernews.di.network.NewsServiceQualifier
import com.deyber.hackernews.domain.model.mappers.toEntityPairs
import com.deyber.hackernews.domain.model.mappers.toHit
import com.deyber.hackernews.domain.model.mappers.toModel
import com.deyber.hackernews.domain.model.ui.HitModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    @ApplicationContext
    private val context: Context,
    @NewsServiceQualifier
    private val service: NewsService,
    @HitRepositoryQualifier
    private val hitRepository: HitRepository,
    @NetworkUtilsQualifier
    private val networkUtils: NetworkUtils
) : NewsRepository {

    override suspend fun getHits(): Resource<List<HitModel>> {
        return if (networkUtils.isNetworkAvailable()) {
            fetchFromNetwork()
        } else {
            fetchFromDatabase()
        }
    }

    suspend fun fetchFromNetwork(): Resource<List<HitModel>> {
        return try {
            val response = service.getNews()
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    val entities = body.hits!!.toEntityPairs()
                    val result = hitRepository.addHits(entities)
                    result.doSuccess {
                        return fetchFromDatabase()
                    }
                }
            }
            Resource.Failure(
                message = context.getString(R.string.new_rep_failed_fetch_data_message),
                throwable = null
            )
        } catch (e: Throwable) {
            Resource.Failure(
                message = context.getString(R.string.new_rep_failed_fetch_network_message),
                throwable = e
            )
        }
    }

    suspend fun fetchFromDatabase(): Resource<List<HitModel>> {
        val hitsListInDB = mutableListOf<HitModel>()
        val hits = hitRepository.getHits()
        hits.doSuccess { hitEntity ->
            hitsListInDB.addAll(hitEntity.map { it.toHit().toModel() })
        }
        return Resource.Success(hitsListInDB)
    }


    override suspend fun deleteHit(id: Long, createAt: Long): Resource<Boolean> {
        return try {
            val request = hitRepository.deleteHits(id, createAt)
            var result = false
            request.doSuccess {
                result = it
            }
            Resource.Success(result)

        } catch (e: Throwable) {
            Resource.Failure(context.getString(R.string.new_rep_failed_fetch_data_message), e)
        }
    }
}