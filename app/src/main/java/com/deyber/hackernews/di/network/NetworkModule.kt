package com.deyber.hackernews.di.network

import android.content.Context
import com.deyber.hackernews.core.network.NetworkConfig.API_BASE_URL
import com.deyber.hackernews.core.network.NetworkUtils
import com.deyber.hackernews.core.utils.HtmlDeserializer
import com.deyber.hackernews.data.service.NewsService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .serializeNulls()
            .registerTypeAdapter(String::class.java, HtmlDeserializer())
            .create()
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
    ): OkHttpClient = OkHttpClient.Builder()
        .build()

    @Provides
    @Singleton
    @NewsServiceQualifier
    fun provideNewService(retrofit: Retrofit) = retrofit.create(NewsService::class.java)

    @Provides
    @Singleton
    @NetworkUtilsQualifier
    fun provideNetworkUtils(@ApplicationContext context: Context) = NetworkUtils(context)
}
