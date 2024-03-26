package duke.deviluke.mangadexapp.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import duke.deviluke.mangadexapp.domain.repository.AuthDataRepository
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
}