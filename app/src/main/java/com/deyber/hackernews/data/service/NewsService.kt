package com.deyber.hackernews.data.service

import com.deyber.hackernews.data.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

    @GET("search_by_date?query=android")
    suspend fun getNews(): Response<NewsResponse>
}