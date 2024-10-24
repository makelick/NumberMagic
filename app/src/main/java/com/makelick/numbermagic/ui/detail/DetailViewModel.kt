package com.makelick.numbermagic.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makelick.numbermagic.data.local.HistoryItem
import com.makelick.numbermagic.domain.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : ViewModel() {

    var item: HistoryItem? by mutableStateOf(null)
        private set

    fun setItem(itemId: Long) {
        viewModelScope.launch {
            item = historyRepository.getHistoryItemById(itemId)
        }
    }
}