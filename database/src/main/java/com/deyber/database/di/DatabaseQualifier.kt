package com.deyber.database.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DatabaseQualifier


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HitDaoQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HitRepositoryQualifier