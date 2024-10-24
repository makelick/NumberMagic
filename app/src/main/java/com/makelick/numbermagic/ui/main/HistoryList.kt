package com.makelick.numbermagic.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.makelick.numbermagic.R
import com.makelick.numbermagic.data.local.HistoryItem
import kotlinx.coroutines.flow.flowOf

@Composable
fun HistoryList(
    historyItems: LazyPagingItems<HistoryItem>,
    modifier: Modifier = Modifier,
    onEvent: (MainScreenIntent) -> Unit = {}
) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(historyItems.itemSnapshotList.items.size) {
        lazyListState.animateScrollToItem(0)
    }

    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        items(historyItems.itemSnapshotList, key = { it?.id ?: 0 }) { item ->
            if (item == null) Text(text = stringResource(R.string.loading))
            else HistoryItemCard(
                historyItem = item,
                modifier = modifier.clickable {
                    onEvent(MainScreenIntent.NavigateToDetail(item.id))
                }
            )
        }
    }
}

@Composable
fun HistoryItemCard(
    historyItem: HistoryItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = historyItem.number.toString(),
            style = MaterialTheme.typography.displaySmall,
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = historyItem.fact,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(5f),
            textAlign = TextAlign.End
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryListPreview() {

    val data = PagingData.from(
        listOf(
            HistoryItem(id = 1, number = 1, fact = "Fact 1"),
            HistoryItem(id = 2, number = 2, fact = "Fact 2"),
            HistoryItem(id = 3, number = 3, fact = "Fact 3")
        )
    )

    HistoryList(
        historyItems = flowOf(data).collectAsLazyPagingItems()
    )
}