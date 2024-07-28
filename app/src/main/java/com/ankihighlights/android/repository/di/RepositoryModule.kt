package com.ankihighlights.android.repository.di

import com.ankihighlights.android.repository.LocalHighlightDataRepository
import com.ankihighlights.android.repository.LocalHighlightRepository
import com.ankihighlights.android.repository.NetworkHighlightDataRepository
import com.ankihighlights.android.repository.NetworkHighlightRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindNetworkHighlightRepository(networkHighlightDataRepository: NetworkHighlightDataRepository): NetworkHighlightRepository

    @Binds
    @Singleton
    fun bindLocalHighlightRepository(localHighlightDataRepository: LocalHighlightDataRepository): LocalHighlightRepository
}
