package com.deyber.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "highlight_result")
data class HighlightResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "author") val author: String? = null,
    @ColumnInfo(name = "comment_text") val commentText: String? = null,
    @ColumnInfo(name = "story_title") val storyTitle: String? = null,
    @ColumnInfo(name = "story_url") val storyUrl: String? = null
)
