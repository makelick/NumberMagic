package com.makelick.numbermagic.di

import android.content.Context
import androidx.room.Room
import com.makelick.numbermagic.data.local.HistoryDatabase
import com.makelick.numbermagic.data.remote.NumbersApi
import com.makelick.numbermagic.data.repository.HistoryRepository
import com.makelick.numbermagic.data.repository.HistoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    fun provideNumbersApi(): NumbersApi {
        return Retrofit.Builder()
            .baseUrl(NumbersApi.BASE_URL)
            .build()
            .create(NumbersApi::class.java)
    }

    @Provides
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
}