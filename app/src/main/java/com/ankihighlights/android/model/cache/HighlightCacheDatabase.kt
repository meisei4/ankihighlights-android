package com.ankihighlights.android.model.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HighlightCacheEntity::class], version = 1)
abstract class HighlightCacheDatabase : RoomDatabase() {
    abstract fun highlightDao(): HighlightDAO
}
