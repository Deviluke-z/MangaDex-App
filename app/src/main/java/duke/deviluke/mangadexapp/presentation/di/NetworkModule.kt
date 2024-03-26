package duke.deviluke.mangadexapp.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import duke.deviluke.mangadexapp.BuildConfig
import duke.deviluke.mangadexapp.data.api.AuthAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
    }.build()

    private val AUTH_URL = BuildConfig.AUTH_URL

    @Singleton
    @Provides
    fun provideAuthRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AUTH_URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthAPIService(retrofit: Retrofit): AuthAPIService {
        return retrofit.create(AuthAPIService::class.java)
    }
}