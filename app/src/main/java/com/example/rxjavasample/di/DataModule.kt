package com.example.rxjavasample.di

import android.content.Context
import androidx.room.Room
import com.example.rxjavasample.data.datasource.RemoteSource
import com.example.rxjavasample.data.datasource.RemoteSourceImpl
import com.example.rxjavasample.data.RepositoryImpl
import com.example.rxjavasample.ApiConstants
import com.example.rxjavasample.data.ApiService
import com.example.rxjavasample.data.datasource.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val CONNECT_TIMEOUT = 60L

    @Provides
    @Singleton
    fun provideApiService(
        loggingInterceptor: HttpLoggingInterceptor,
        networkAndServerDownCheckerInterceptor: NetworkAndServerDownCheckerInterceptor,
        ) : ApiService {
        return Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(providesOkHttpClient(loggingInterceptor, networkAndServerDownCheckerInterceptor))
            .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        networkAndServerDownCheckerInterceptor: NetworkAndServerDownCheckerInterceptor,
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(networkAndServerDownCheckerInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        return httpClientBuilder.addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun providesConnectivityInterceptor(
        @ApplicationContext context: Context,
        networkChecker: NetworkChecker,
    ): NetworkAndServerDownCheckerInterceptor {
        return NetworkAndServerDownCheckerInterceptor(context, networkChecker)
    }

    @Singleton
    @Provides
    fun providesConnectivityChecker(
        @ApplicationContext context: Context,
    ): NetworkChecker = NetworkCheckerImpl(context)
    @Provides
    @Singleton
    fun provideRemoteSource(apiService: ApiService): RemoteSourceImpl {
        return RemoteSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideRepository(remoteSource: RemoteSource): RepositoryImpl {
        return RepositoryImpl(remoteSource)
    }
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "postDatabase"
        ).build()
    }
}