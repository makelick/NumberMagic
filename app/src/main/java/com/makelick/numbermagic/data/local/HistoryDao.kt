package com.makelick.numbermagic.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

    @Insert
    suspend fun insertItem(historyItem: HistoryItem)

    @Query("SELECT * FROM history_item")
    fun getAllItems(): PagingSource<Int, HistoryItem>

}