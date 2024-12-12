package com.deyber.hackernews.di.network

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NewsServiceQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkUtilsQualifier
