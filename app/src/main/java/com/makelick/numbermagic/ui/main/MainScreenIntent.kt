package com.makelick.numbermagic.ui.main

sealed class MainScreenIntent {
    data class ChangeNumber(val number: Int?) : MainScreenIntent()
    data object MakeRequest : MainScreenIntent()
    data object MakeRandomRequest : MainScreenIntent()
    data class NavigateToDetail(val itemId: Long) : MainScreenIntent()
    data object DismissError : MainScreenIntent()
}