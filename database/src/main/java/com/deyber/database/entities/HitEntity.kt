package com.deyber.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "hit",
    indices = [Index(value = ["story_id", "created_at_i"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = HighlightResultEntity::class,
            parentColumns = ["id"],
            childColumns = ["highlight_result_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HitEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "highlight_result_id") val highlightResultId: Long = 0,
    @ColumnInfo(name = "author") val author: String = "",
    @ColumnInfo(name = "comment_text") val commentText: String = "",
    @ColumnInfo(name = "created_at") val createdAt: String = "",
    @ColumnInfo(name = "created_at_i") val createdAtI: Long = 0,
    @ColumnInfo(name = "parent_id") val parentId: Long = 0,
    @ColumnInfo(name = "story_id") val storyId: Long = 0,
    @ColumnInfo(name = "story_title") val storyTitle: String = "",
    @ColumnInfo(name = "story_url") val storyUrl: String = "",
    @ColumnInfo(name = "updated_at") val updatedAt: String = "",
    @ColumnInfo(name="isDeleted") val isDeleted : Boolean = false
)
