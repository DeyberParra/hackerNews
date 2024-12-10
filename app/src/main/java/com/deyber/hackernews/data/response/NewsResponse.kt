package com.deyber.hackernews.data.response

import com.google.gson.annotations.SerializedName


data class NewsResponse(
    @SerializedName("exhaustive") val exhaustive: Exhaustive? = null,
    @SerializedName("exhaustiveNbHits") val exhaustiveNbHits: Boolean? = null,
    @SerializedName("exhaustiveTypo") val exhaustiveTypo: Boolean? = null,
    @SerializedName("hits") val hits: List<Hit>? = null,
    @SerializedName("hitsPerPage") val hitsPerPage: Int? = null,
    @SerializedName("nbHits") val nbHits: Int? = null,
    @SerializedName("nbPages") val nbPages: Int? = null,
    @SerializedName("page") val page: Int? = null,
    @SerializedName("params") val params: String? = null,
    @SerializedName("processingTimeMS") val processingTimeMS: Int? = null,
    @SerializedName("processingTimingsMS") val processingTimingsMS: ProcessingTimingsMS? = null,
    @SerializedName("query") val query: String? = null,
    @SerializedName("serverTimeMS") val serverTimeMS: Int? = null
)

data class Exhaustive(
    @SerializedName("nbHits") val nbHits: Boolean? = null,
    @SerializedName("typo") val typo: Boolean? = null
)

data class Hit(
    @SerializedName("_highlightResult") val highlightResult: HighlightResult? = null,
    @SerializedName("_tags") val tags: List<String>? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("comment_text") val commentText: String? = null,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("created_at_i") val createdAtI: Long? = null,
    @SerializedName("objectID") val objectID: String? = null,
    @SerializedName("parent_id") val parentId: Long? = null,
    @SerializedName("story_id") val storyId: Long? = null,
    @SerializedName("story_title") val storyTitle: String? = null,
    @SerializedName("story_url") val storyUrl: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null
)


data class HighlightResult(
    @SerializedName("author") val author: HighlightField? = null,
    @SerializedName("comment_text") val commentText: HighlightField? = null,
    @SerializedName("story_title") val storyTitle: HighlightField? = null,
    @SerializedName("story_url") val storyUrl: HighlightField? = null
)


data class HighlightField(
    @SerializedName("matchLevel") val matchLevel: String? = null,
    @SerializedName("matchedWords") val matchedWords: List<String>? = null,
    @SerializedName("value") val value: String? = null
)


data class ProcessingTimingsMS(
    @SerializedName("_request") val request: RequestTiming? = null,
    @SerializedName("afterFetch") val afterFetch: AfterFetchTiming? = null,
    @SerializedName("fetch") val fetch: FetchTiming? = null,
    @SerializedName("total") val total: Int? = null
)


data class RequestTiming(
    @SerializedName("roundTrip") val roundTrip: Int? = null
)


data class AfterFetchTiming(
    @SerializedName("format") val format: TimingDetail? = null,
    @SerializedName("merge") val merge: TimingDetail? = null,
    @SerializedName("total") val total: Int? = null
)


data class TimingDetail(
    @SerializedName("total") val total: Int? = null
)

data class FetchTiming(
    @SerializedName("query") val query: Int? = null,
    @SerializedName("scanning") val scanning: Int? = null,
    @SerializedName("total") val total: Int? = null
)
