package com.deyber.hackernews.domain.model.mappers

import com.deyber.database.data.HitWithHighlightResult
import com.deyber.hackernews.data.response.HighlightField
import com.deyber.hackernews.data.response.HighlightResult
import com.deyber.hackernews.data.response.Hit

fun HitWithHighlightResult.toHit(): Hit {
    val highlightResult = HighlightResult(
        author = highlightAuthor?.let { HighlightField(it) },
        commentText = highlightCommentText?.let { HighlightField(it) },
        storyTitle = highlightStoryTitle?.let { HighlightField(it) },
        storyUrl = highlightStoryUrl?.let { HighlightField(it) }
    )

    return Hit(
        highlightResult = highlightResult,
        author = hitAuthor,
        commentText = hitCommentText,
        createdAt = hitCreatedAt,
        createdAtI = hitCreatedAtI,
        parentId = hitParentId,
        storyId = hitStoryId,
        storyTitle = hitStoryTitle,
        storyUrl = hitStoryUrl,
        updatedAt = hitUpdatedAt
    )
}
