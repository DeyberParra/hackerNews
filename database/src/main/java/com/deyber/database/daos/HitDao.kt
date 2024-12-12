package com.deyber.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.deyber.database.data.HitWithHighlightResult
import com.deyber.database.entities.HighlightResultEntity
import com.deyber.database.entities.HitEntity

@Dao
interface HitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHit(hitEntity: HitEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHighlightResult(highlightResultEntity: HighlightResultEntity): Long

    @Transaction
    suspend fun insertHitsWithHighlightResults(hits: List<Pair<HitEntity, HighlightResultEntity?>>) {
        hits.forEach { (hitEntity, highlightResultEntity) ->
            val highlightResultId = highlightResultEntity?.let {
                insertHighlightResult(it)
            } ?: 0L
            insertHit(hitEntity.copy(highlightResultId = highlightResultId))
        }
    }


    @Query(
        """
        SELECT 
            h.id AS hitId, 
            h.author AS hitAuthor, 
            h.comment_text AS hitCommentText, 
            h.created_at AS hitCreatedAt, 
            h.created_at_i AS hitCreatedAtI, 
            h.parent_id AS hitParentId, 
            h.story_id AS hitStoryId, 
            h.story_title AS hitStoryTitle, 
            h.story_url AS hitStoryUrl, 
            h.updated_at AS hitUpdatedAt,
            hr.author AS highlightAuthor, 
            hr.comment_text AS highlightCommentText, 
            hr.story_title AS highlightStoryTitle, 
            hr.story_url AS highlightStoryUrl
        FROM hit h
        LEFT JOIN highlight_result hr ON h.highlight_result_id = hr.id
         WHERE h.isDeleted != 1
    """
    )
    suspend fun getHitsWithHighlightResults(): List<HitWithHighlightResult>

    @Query("SELECT id FROM hit WHERE story_id = :storyId AND created_at_i = :createAt LIMIT 1")
    suspend fun getHitId(storyId: Long, createAt: Long): Long


    @Query("UPDATE hit SET isDeleted = :isDeleted WHERE id = :hitId")
    suspend fun deleteHit(hitId: Long, isDeleted: Boolean = true)
}