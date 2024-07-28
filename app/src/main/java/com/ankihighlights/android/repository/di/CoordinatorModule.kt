package com.ankihighlights.android.repository.di

import com.ankihighlights.android.repository.HighlightDataCoordinator
import com.ankihighlights.android.repository.LocalHighlightRepository
import com.ankihighlights.android.repository.NetworkHighlightRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoordinatorModule {
    @Provides
    @Singleton
    fun provideHighlightCoordinator(
        networkRepo: NetworkHighlightRepository,
        localRepo: LocalHighlightRepository,
    ): HighlightDataCoordinator {
        return HighlightDataCoordinator(networkRepo, localRepo)
    }
}
