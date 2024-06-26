package duke.deviluke.mangadexapp.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import duke.deviluke.mangadexapp.presentation.adapter.GenresAdapter
import duke.deviluke.mangadexapp.presentation.adapter.LatestMangaListAdapter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideGenresAdapter(): GenresAdapter {
        return GenresAdapter()
    }

    @Singleton
    @Provides
    fun provideLatestMangaListAdapter(): LatestMangaListAdapter {
        return LatestMangaListAdapter()
    }
}