package com.makelick.numbermagic.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

    @Insert
    suspend fun insertItem(historyItem: HistoryItem) : Long

    @Query("SELECT * FROM history_item ORDER BY id DESC")
    fun getAllItems(): PagingSource<Int, HistoryItem>

    @Query("SELECT * FROM history_item WHERE id = :id")
    suspend fun getItemById(id: Long): HistoryItem

}