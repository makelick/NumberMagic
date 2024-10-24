package com.makelick.numbermagic.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.makelick.numbermagic.data.local.HistoryItem
import com.makelick.numbermagic.data.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    historyRepository: HistoryRepository
) : ViewModel() {

    var number : Int? by mutableStateOf(null)
        private set

    val historyItems: Flow<PagingData<HistoryItem>> =
        historyRepository.getHistoryItems().cachedIn(viewModelScope)

    fun handleIntent(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.ChangeNumber -> {
                this.number = intent.number
            }
            is MainScreenIntent.MakeRequest -> {
                // Make request
            }
            is MainScreenIntent.MakeRandomRequest -> {
                // Load history
            }
            else -> Unit
        }
    }
}