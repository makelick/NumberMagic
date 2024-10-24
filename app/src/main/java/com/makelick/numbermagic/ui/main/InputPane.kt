package com.makelick.numbermagic.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makelick.numbermagic.R

@Composable
fun InputPane(
    number: Int? = null,
    modifier: Modifier = Modifier,
    onEvent: (MainScreenIntent) -> Unit = {}
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

@Preview(showBackground = true)
@Composable
private fun InputPanePreview() {
    InputPane(number = null)
}
