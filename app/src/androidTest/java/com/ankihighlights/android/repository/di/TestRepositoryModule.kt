package com.ankihighlights.android.repository.di

import com.ankihighlights.android.repository.FakeLocalHighlightRepository
import com.ankihighlights.android.repository.LocalHighlightRepository
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
interface TestRepositoryModule {
    @Binds
    @Singleton
    fun bindFakeLocalHighlightRepository(fakeLocalHighlightRepository: FakeLocalHighlightRepository): LocalHighlightRepository
}
