package com.makelick.numbermagic.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_item")
data class HistoryItem(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val number: Int,
    val fact: String
)
