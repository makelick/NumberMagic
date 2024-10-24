package com.makelick.numbermagic.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makelick.numbermagic.R
import com.makelick.numbermagic.data.local.HistoryItem

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    Column {
        InputPane(modifier = modifier)
        Spacer(modifier = Modifier.height(16.dp))
        HistoryList(modifier = modifier)
    }
}

@Composable
fun InputPane(
    modifier: Modifier = Modifier
) {
    var number by remember { mutableStateOf("") }
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
                value = number,
                onValueChange = { number = it },
                singleLine = true,
                placeholder = { Text(stringResource(R.string.enter_number)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = MaterialTheme.shapes.large,
                keyboardActions = KeyboardActions(
                    onDone = { /*TODO*/ }
                )
            )

            Button(
                onClick = { /*TODO*/ },
                shape = MaterialTheme.shapes.large,
                enabled = number.isNotBlank()
            ) {
                Text(
                    text = stringResource(R.string.get_fact),
                    textAlign = TextAlign.Center
                )
            }
        }

        Button(
            shape = MaterialTheme.shapes.small,
            onClick = { /*TODO*/ }
        ) {
            Text(stringResource(R.string.get_fact_about_random_number))
        }
    }
}

@Composable
fun HistoryList(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.clickable { /*TODO*/ }
    ) {
        items(10) {
            HistoryItemCard(
                HistoryItem(
                    0,
                    52,
                    "The answer to the ultimate question of life, the universe, and everything"
                )
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
            modifier = Modifier.weight(5f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreen_preview() {
    MainScreen()
}