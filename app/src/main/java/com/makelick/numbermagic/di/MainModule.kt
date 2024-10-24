package com.makelick.numbermagic.di

import android.content.Context
import androidx.room.Room
import com.makelick.numbermagic.data.local.HistoryDatabase
import com.makelick.numbermagic.data.remote.NumbersApi
import com.makelick.numbermagic.data.repository.HistoryRepositoryImpl
import com.makelick.numbermagic.data.repository.NetworkRepositoryImpl
import com.makelick.numbermagic.domain.HistoryRepository
import com.makelick.numbermagic.domain.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun provideNumbersApi(): NumbersApi {
        return Retrofit.Builder()
            .baseUrl(NumbersApi.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(NumbersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHistoryDatabase(@ApplicationContext context: Context): HistoryDatabase {
        return Room.databaseBuilder(
            context,
            HistoryDatabase::class.java,
            "history_database"
        ).build()
    }

    @Provides
    fun provideHistoryRepository(historyDatabase: HistoryDatabase): HistoryRepository {
        return HistoryRepositoryImpl(historyDatabase.historyDao)
    }

    @Provides
    fun provideNetworkRepository(
        api: NumbersApi,
        historyDatabase: HistoryDatabase
    ): NetworkRepository {
        return NetworkRepositoryImpl(api, historyDatabase.historyDao)
    }
}