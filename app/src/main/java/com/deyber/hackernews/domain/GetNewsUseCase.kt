package com.deyber.hackernews.domain

import com.deyber.hackernews.core.network.Resource
import com.deyber.hackernews.data.repository.NewsRepository
import com.deyber.hackernews.di.repository.NewsRepositoryQualifier
import com.deyber.hackernews.domain.model.mappers.toModel
import com.deyber.hackernews.domain.model.ui.NewsResponseModel
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    @NewsRepositoryQualifier
    private val repository: NewsRepository
)
{
    suspend operator fun invoke() : Resource<NewsResponseModel> {
        return try {
            val response = repository.getNews()
            if(response.isSuccessful){
                Resource.Success(response.body()!!.toModel())
            }else{
                Resource.Failure(message = "", throwable = null)
            }
        } catch (e : Throwable){
            Resource.Failure(message = "", throwable = e)
        }
    }
}