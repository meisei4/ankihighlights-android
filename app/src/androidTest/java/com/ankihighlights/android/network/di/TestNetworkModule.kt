package com.ankihighlights.android.network.di

import com.ankihighlights.android.repository.FakeNetworkHighlightRepository
import com.ankihighlights.android.repository.NetworkHighlightRepository
import com.ankihighlights.android.repository.di.RepositoryModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class],
)
interface TestNetworkModule {
    @Binds
    @Singleton
    fun bindHighlightRepository(fakeHighlightRepository: FakeNetworkHighlightRepository): NetworkHighlightRepository
}
