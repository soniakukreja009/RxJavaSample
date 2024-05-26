package com.example.rxjavasample.data

import com.example.rxjavasample.domain.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

//    private const val CONNECT_TIMEOUT = 60L

    @Provides
    @Singleton
    fun provideApiService(
        //loggingInterceptor: HttpLoggingInterceptor,
        //networkAndServerDownCheckerInterceptor: NetworkAndServerDownCheckerInterceptor,
        ) : ApiService {
        return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(ApiService::class.java)
    }

/*    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        networkAndServerDownCheckerInterceptor: NetworkAndServerDownCheckerInterceptor,
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(networkAndServerDownCheckerInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        return httpClientBuilder.addInterceptor(loggingInterceptor).build()
    }*/

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
}