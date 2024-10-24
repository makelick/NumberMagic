package com.makelick.numbermagic.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onEvent: (MainScreenIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        InputPane(
            number = viewModel.number,
            onEvent = onEvent
        )
        Spacer(modifier = Modifier.height(16.dp))
        HistoryList(
            historyItems = viewModel.historyItems.collectAsLazyPagingItems(),
            onEvent = onEvent
        )
    }

    AnimatedVisibility(
        visible = viewModel.isError,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = modifier.fillMaxSize()
        ) {
            ErrorSnackbar(onEvent = onEvent)
        }
    }
}
