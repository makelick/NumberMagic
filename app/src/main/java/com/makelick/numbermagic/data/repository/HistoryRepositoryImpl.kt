package com.makelick.numbermagic.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.makelick.numbermagic.data.local.HistoryDao
import com.makelick.numbermagic.data.local.HistoryItem
import com.makelick.numbermagic.domain.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyDao: HistoryDao
) : HistoryRepository {

    override fun getHistoryItems(): Flow<PagingData<HistoryItem>> {
        return Pager(
            config = PagingConfig(pageSize = HistoryRepository.PAGE_SIZE),
            pagingSourceFactory = { historyDao.getAllItems() }
        ).flow
    }

    override suspend fun getHistoryItemById(id: Long): HistoryItem {
        return historyDao.getItemById(id)
    }

}