package duke.deviluke.mangadexapp.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import duke.deviluke.mangadexapp.domain.repository.AuthDataRepository
import duke.deviluke.mangadexapp.domain.repository.MangaDataRepository
import duke.deviluke.mangadexapp.domain.usecases.GetLatestMangaListUseCase
import duke.deviluke.mangadexapp.domain.usecases.GetMangaCoverPathUseCase
import duke.deviluke.mangadexapp.domain.usecases.GetRandomMangaUseCase
import duke.deviluke.mangadexapp.domain.usecases.LoginUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(
        authDataRepository: AuthDataRepository
    ): LoginUseCase {
        return LoginUseCase(authDataRepository)
    }

    @Singleton
    @Provides
    fun provideGetRandomMangaUseCase(
        mangaDataRepository: MangaDataRepository
    ): GetRandomMangaUseCase {
        return GetRandomMangaUseCase(mangaDataRepository)
    }

    @Singleton
    @Provides
    fun provideGetLatestMangaListUseCase(
        mangaDataRepository: MangaDataRepository
    ): GetLatestMangaListUseCase {
        return GetLatestMangaListUseCase(mangaDataRepository)
    }

    @Singleton
    @Provides
    fun provideGetMangaCoverPathUseCase(
        mangaDataRepository: MangaDataRepository
    ): GetMangaCoverPathUseCase {
        return GetMangaCoverPathUseCase(mangaDataRepository)
    }
}