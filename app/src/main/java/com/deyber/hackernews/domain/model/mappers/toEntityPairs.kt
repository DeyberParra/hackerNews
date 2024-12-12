package com.deyber.hackernews.domain.model.mappers

import com.deyber.database.entities.HighlightResultEntity
import com.deyber.database.entities.HitEntity
import com.deyber.hackernews.data.response.HighlightResult
import com.deyber.hackernews.data.response.Hit

fun List<Hit>.toEntityPairs(): List<Pair<HitEntity, HighlightResultEntity?>> {
    return this.map { it.toEntityPair() }
}

fun Hit.toEntityPair(): Pair<HitEntity, HighlightResultEntity?> {
    val highlightResultEntity = highlightResult?.toEntity()
    val hitEntity = HitEntity(
        author = author.orEmpty(),
        commentText = commentText.orEmpty(),
        createdAt = createdAt.orEmpty(),
        createdAtI = createdAtI?:0L,
        parentId = parentId?:0L,
        storyId = storyId?:0L,
        storyTitle = storyTitle.orEmpty(),
        storyUrl = storyUrl.orEmpty(),
        updatedAt = updatedAt.orEmpty(),
    )
    return hitEntity to highlightResultEntity
}

fun HighlightResult.toEntity(): HighlightResultEntity {
    return HighlightResultEntity(
        author = author?.value,
        commentText = commentText?.value,
        storyTitle = storyTitle?.value,
        storyUrl = storyUrl?.value
    )
}
