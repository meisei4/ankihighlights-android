package com.ankihighlights.android.model.cache.di

import android.content.Context
import androidx.room.Room
import com.ankihighlights.android.model.cache.HighlightCacheDatabase
import com.ankihighlights.android.model.cache.HighlightDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheDatabaseModule {
    @Provides
    @Singleton
    fun provideHighlightDatabase(
        @ApplicationContext context: Context,
    ): HighlightCacheDatabase {
        return Room.databaseBuilder(
            context,
            HighlightCacheDatabase::class.java,
            "highlight_db",
        ).build()
    }

    @Provides
    @Singleton
    fun provideHighlightDao(database: HighlightCacheDatabase): HighlightDAO {
        return database.highlightDao()
    }
}
