package duke.deviluke.mangadexapp.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import duke.deviluke.mangadexapp.data.api.AuthAPIService
import duke.deviluke.mangadexapp.data.repository.dataSource.AuthRemoteDataSource
import duke.deviluke.mangadexapp.data.repository.dataSourceImpl.AuthRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(
        authAPIService: AuthAPIService
    ): AuthRemoteDataSource {
        return AuthRemoteDataSourceImpl(authAPIService)
    }
}