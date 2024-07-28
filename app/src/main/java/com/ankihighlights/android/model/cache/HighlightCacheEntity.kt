package com.ankihighlights.android.model.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "highlight_cache")
data class HighlightCacheEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val highlightedText: String,
    val timestamp: Long,
)
