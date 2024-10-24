package com.makelick.numbermagic.data.repository

import androidx.paging.PagingData
import com.makelick.numbermagic.data.local.HistoryItem
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getHistoryItems(): Flow<PagingData<HistoryItem>>

    companion object {
        const val PAGE_SIZE = 20
    }
}