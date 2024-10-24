package com.makelick.numbermagic.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.makelick.numbermagic.data.local.HistoryItem
import com.makelick.numbermagic.domain.HistoryRepository
import com.makelick.numbermagic.domain.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    historyRepository: HistoryRepository
) : ViewModel() {

    var isError by mutableStateOf(false)
        private set

    var number: Int? by mutableStateOf(null)
        private set

    val historyItems: Flow<PagingData<HistoryItem>> =
        historyRepository.getHistoryItems().cachedIn(viewModelScope)

    fun handleIntent(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.ChangeNumber -> {
                this.number = intent.number
            }

            is MainScreenIntent.MakeRequest -> {
                viewModelScope.launch {
                    if (networkRepository.getFact(number ?: return@launch).isSuccess)
                        number = null
                    else
                        isError = true
                }
            }

            is MainScreenIntent.MakeRandomRequest -> {
                viewModelScope.launch {
                    if (networkRepository.getRandomFact().isFailure)
                        isError = true
                }
            }

            is MainScreenIntent.DismissError -> {
                isError = false
            }

            else -> Unit
        }
    }
}