package com.deyber.database.data

data class HitWithHighlightResult(
    val hitId: Long,
    val hitAuthor: String?,
    val hitCommentText: String?,
    val hitCreatedAt: String?,
    val hitCreatedAtI: Long?,
    val hitParentId: Long?,
    val hitStoryId: Long?,
    val hitStoryTitle: String?,
    val hitStoryUrl: String?,
    val hitUpdatedAt: String?,
    val highlightAuthor: String?,
    val highlightCommentText: String?,
    val highlightStoryTitle: String?,
    val highlightStoryUrl: String?
)
