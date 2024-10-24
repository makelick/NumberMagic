package com.makelick.numbermagic.domain

import androidx.paging.PagingData
import com.makelick.numbermagic.data.local.HistoryItem
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getHistoryItems(): Flow<PagingData<HistoryItem>>

    suspend fun getHistoryItemById(id: Long): HistoryItem

    companion object {
        const val PAGE_SIZE = 20
    }
}