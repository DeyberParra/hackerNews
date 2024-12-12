package com.deyber.hackernews.di.useCases

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class GetNewsUseCasesQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DeleteHitUseCasesQualifier
