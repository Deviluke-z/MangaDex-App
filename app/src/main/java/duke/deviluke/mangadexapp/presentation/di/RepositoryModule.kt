package duke.deviluke.mangadexapp.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import duke.deviluke.mangadexapp.data.AuthDataRepositoryImpl
import duke.deviluke.mangadexapp.data.MangaDataRepositoryImpl
import duke.deviluke.mangadexapp.data.repository.dataSource.AuthRemoteDataSource
import duke.deviluke.mangadexapp.data.repository.dataSource.MangaDataRemoteDataSource
import duke.deviluke.mangadexapp.domain.repository.AuthDataRepository
import duke.deviluke.mangadexapp.domain.repository.MangaDataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthDataRepository(
        authRemoteDataSource: AuthRemoteDataSource
    ): AuthDataRepository {
        return AuthDataRepositoryImpl(authRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideMangaDataRepository(
        mangaDataRemoteDataSource: MangaDataRemoteDataSource
    ): MangaDataRepository {
        return MangaDataRepositoryImpl(mangaDataRemoteDataSource)
    }
}