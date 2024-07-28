package com.ankihighlights.android.model.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HighlightDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHighlight(highlight: HighlightCacheEntity)

    @Query("SELECT * FROM highlight_cache ORDER BY timestamp DESC LIMIT 10")
    fun getRecentHighlights(): Flow<List<HighlightCacheEntity>>
}
