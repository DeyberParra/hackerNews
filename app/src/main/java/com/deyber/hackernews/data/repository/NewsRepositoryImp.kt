package com.deyber.hackernews.data.repository

import com.deyber.hackernews.data.response.NewsResponse
import com.deyber.hackernews.data.service.NewsService
import com.deyber.hackernews.di.network.NewsServiceQualifier
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    @NewsServiceQualifier
    private val service: NewsService
): NewsRepository {

    override suspend fun getNews(): Response<NewsResponse> = service.getNews()
}