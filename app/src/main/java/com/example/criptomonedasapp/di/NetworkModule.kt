package com.example.criptomonedasapp.di

import com.example.criptomonedasapp.data.local.database.CoinsDatabase
import com.example.criptomonedasapp.data.local.CryptocurrenciesLocalDataSource
import com.example.criptomonedasapp.data.local.dataSources.CryptocurrenciesLocalDataSourceImpl
import com.example.criptomonedasapp.data.network.dataSources.CryptocurrenciesRemoteDataSourceImpl
import com.example.criptomonedasapp.data.network.CryptocurrenciesRemoteDataSource
import com.example.criptomonedasapp.data.network.services.APIService
import com.example.criptomonedasapp.data.network.services.CryptoEndPoints
import com.example.criptomonedasapp.domain.dataSources.CryptocurrenciesRepositoryImpl
import com.example.criptomonedasapp.domain.repository.CryptocurrenciesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun cryptocurrenciesRemoteDataSource(
        apiService: APIService
    ): CryptocurrenciesRemoteDataSource {
        return CryptocurrenciesRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun cryptocurrenciesDataSource(
       remoteDataSource: CryptocurrenciesRemoteDataSource,
       localDataSource: CryptocurrenciesLocalDataSource
    ): CryptocurrenciesRepository {
        return CryptocurrenciesRepositoryImpl(remoteDataSource,localDataSource)
    }

    @Singleton
    @Provides
    fun cryptocurrenciesRepository(
        data: CoinsDatabase
    ): CryptocurrenciesLocalDataSource {
        return CryptocurrenciesLocalDataSourceImpl(data)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient():OkHttpClient{
        val interceptor = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

        return OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val request = chain.request()
                val builder = request
                    .newBuilder()
                    .header("User-Agent", request.url.host)
                val mutatedRequest = builder.build()
                val response = chain.proceed(mutatedRequest)
                response
            }
            addInterceptor(interceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(CryptoEndPoints.URL_BASE_)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

}