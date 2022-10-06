package com.example.criptomonedasapp.services

import com.example.criptomonedasapp.mvvm.data.database.CoinsDatabase
import com.example.criptomonedasapp.mvvm.data.database.domain.repository.CryptocurrenciesDataSource
import com.example.criptomonedasapp.mvvm.data.database.repository.CryptocurrenciesDataSourceImpl
import com.example.criptomonedasapp.mvvm.data.repository.CryptocurrenciesDetailUseCaseImpl
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesDetailUseCase
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
    ): CryptocurrenciesDetailUseCase {
        return CryptocurrenciesDetailUseCaseImpl(apiService)
    }

    @Singleton
    @Provides
    fun cryptocurrenciesDataSource(
        data: CoinsDatabase
    ): CryptocurrenciesDataSource {
        return CryptocurrenciesDataSourceImpl(data)
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
    fun provideAPIService(retrofit: Retrofit): APIService{
        return retrofit.create(APIService::class.java)
    }

}