package com.makelick.numbermagic.di

import com.makelick.numbermagic.data.remote.NumbersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    fun provideNumbersApi() : NumbersApi {
        return Retrofit.Builder()
            .baseUrl(NumbersApi.BASE_URL)
            .build()
            .create(NumbersApi::class.java)
    }
}