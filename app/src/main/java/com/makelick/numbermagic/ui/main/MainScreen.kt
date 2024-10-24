package com.makelick.numbermagic.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
}

@Composable
fun InputPane(
    number: Int? = null,
    onEvent: (MainScreenIntent) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = number?.toString() ?: "",
                onValueChange = { onEvent(MainScreenIntent.ChangeNumber(it.toIntOrNull())) },
                singleLine = true,
                placeholder = { Text(stringResource(R.string.enter_number)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = MaterialTheme.shapes.large,
                keyboardActions = KeyboardActions(
                    onDone = {
                        onEvent(MainScreenIntent.MakeRequest)
                        defaultKeyboardAction(ImeAction.Done)
                    }
                )
            )

            Button(
                onClick = { onEvent(MainScreenIntent.MakeRequest) },
                shape = MaterialTheme.shapes.large,
                enabled = number != null
            ) {
                Text(
                    text = stringResource(R.string.get_fact),
                    textAlign = TextAlign.Center
                )
            }
        }

        Button(
            shape = MaterialTheme.shapes.small,
            onClick = { onEvent(MainScreenIntent.MakeRandomRequest) }
        ) {
            Text(stringResource(R.string.get_fact_about_random_number))
        }
    }
}

@Composable
fun HistoryList(
    historyItems: LazyPagingItems<HistoryItem>,
    onEvent: (MainScreenIntent) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(historyItems.itemSnapshotList) { item ->
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
private fun InputPanePreview() {
    InputPane(number = null)
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