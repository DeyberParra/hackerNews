package com.deyber.hackernews.data.repository

import com.deyber.hackernews.data.response.NewsResponse
import retrofit2.Response

interface NewsRepository {
    @Throws(Exception::class)
    suspend fun getNews(): Response<NewsResponse>
}