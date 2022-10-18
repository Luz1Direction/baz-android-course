package com.example.criptomonedasapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Singleton
    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Singleton
    @Provides
    fun provideSchedulers(): Scheduler = Schedulers.io()

}