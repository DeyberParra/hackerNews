package com.deyber.hackernews.domain.model.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class NewsResponseModel(
    val exhaustive: ExhaustiveModel? = null,
    val exhaustiveNbHits: Boolean? = null,
    val exhaustiveTypo: Boolean? = null,
    val hits: List<HitModel>? = null,
    val hitsPerPage: Int? = null,
    val nbHits: Int? = null,
    val nbPages: Int? = null,
    val page: Int? = null,
    val params: String? = null,
    val processingTimeMS: Int? = null,
    val processingTimingsMS: ProcessingTimingsMSModel? = null,
    val query: String? = null,
    val serverTimeMS: Int? = null
)

data class ExhaustiveModel(
    val nbHits: Boolean? = null,
    val typo: Boolean? = null
)

@Parcelize
data class HitModel(
    val highlightResult: HighlightResultModel? = null,
    val tags: List<String>? = null,
    val author: String? = null,
    val commentText: String? = null,
    val createdAt: String? = null,
    val createdAtI: Long? = null,
    val objectID: String? = null,
    val parentId: Long? = null,
    val storyId: Long? = null,
    val storyTitle: String? = null,
    val storyUrl: String? = null,
    val updatedAt: String? = null
) : Parcelable

@Parcelize
data class HighlightResultModel(
    val author: HighlightFieldModel? = null,
    val commentText: HighlightFieldModel? = null,
    val storyTitle: HighlightFieldModel? = null,
    val storyUrl: HighlightFieldModel? = null
) : Parcelable
@Parcelize
data class HighlightFieldModel(
    val matchLevel: String? = null,
    val matchedWords: List<String>? = null,
    val value: String? = null
):Parcelable

data class ProcessingTimingsMSModel(
    val request: RequestTimingModel? = null,
    val afterFetch: AfterFetchTimingModel? = null,
    val fetch: FetchTimingModel? = null,
    val total: Int? = null
)

data class RequestTimingModel(
    val roundTrip: Int? = null
)

data class AfterFetchTimingModel(
    val format: TimingDetailModel? = null,
    val merge: TimingDetailModel? = null,
    val total: Int? = null
)

data class TimingDetailModel(
    val total: Int? = null
)

data class FetchTimingModel(
    val query: Int? = null,
    val scanning: Int? = null,
    val total: Int? = null
)
