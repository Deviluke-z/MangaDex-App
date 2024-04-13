package duke.deviluke.mangadexapp.presentation.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import duke.deviluke.mangadexapp.domain.usecases.GetLatestMangaListUseCase
import duke.deviluke.mangadexapp.domain.usecases.GetMangaCoverPathUseCase
import duke.deviluke.mangadexapp.domain.usecases.GetRandomMangaUseCase
import duke.deviluke.mangadexapp.domain.usecases.LoginUseCase
import duke.deviluke.mangadexapp.presentation.viewmodel.MainViewModelFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideMainViewModelFactory(
        application: Application,
        loginUseCase: LoginUseCase,
        getRandomMangaUseCase: GetRandomMangaUseCase,
        getLatestMangaListUseCase: GetLatestMangaListUseCase,
        getMangaCoverPathUseCase: GetMangaCoverPathUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            application,
            loginUseCase,
            getRandomMangaUseCase,
            getLatestMangaListUseCase,
            getMangaCoverPathUseCase
        )
    }
}