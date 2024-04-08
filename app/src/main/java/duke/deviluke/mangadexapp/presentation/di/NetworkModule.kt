package duke.deviluke.mangadexapp.presentation.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import duke.deviluke.mangadexapp.BuildConfig
import duke.deviluke.mangadexapp.data.api.AuthAPIService
import duke.deviluke.mangadexapp.data.api.MangaAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
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
    private val BASE_URL = BuildConfig.BASE_URL

    @Singleton
    @Provides
    @Named("auth") // for AUTH_URL
    fun provideAuthRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .baseUrl(AUTH_URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    @Named("base") // for BASE_URL
    fun provideMangaRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthAPIService(
        @Named("auth")
        retrofit: Retrofit
    ): AuthAPIService {
        return retrofit.create(AuthAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideMangaAPIService(
        @Named("base")
        retrofit: Retrofit
    ): MangaAPIService {
        return retrofit.create(MangaAPIService::class.java)
    }
}