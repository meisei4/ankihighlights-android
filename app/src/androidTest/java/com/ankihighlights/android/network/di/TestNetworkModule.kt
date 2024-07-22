package com.ankihighlights.android.network.di

import com.ankihighlights.android.repository.HighlightRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
interface TestNetworkModule {

    @Binds
    fun bindHighlightRepository(
        fakeHighlightRepository: com.ankihighlights.android.repository.FakeHighlightRepository
    ): HighlightRepository
}
