package com.example.nycopendata.di

import com.example.nycopendata.common.BASE_URL
import com.example.nycopendata.model.remote.NycApi
import com.example.nycopendata.model.remote.Repository
import com.example.nycopendata.model.remote.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Framework that interacts with the NYC OpenData API.
@Module
@InstallIn(SingletonComponent::class)
class ServiceModel {

    @Provides
    fun provideNycService(): NycApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NycApi::class.java)

    @Provides
    fun provideRepositoryLayer(service: NycApi): Repository =
        RepositoryImpl(service)

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideCoroutineScope(dispatcher: CoroutineDispatcher): CoroutineScope =
        CoroutineScope(dispatcher)
}