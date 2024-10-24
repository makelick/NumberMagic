package com.makelick.numbermagic.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makelick.numbermagic.R

@Composable
fun ErrorSnackbar(
    onEvent: (MainScreenIntent) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Snackbar(
        modifier = modifier.padding(16.dp),
        dismissAction = {
            TextButton(onClick = { onEvent(MainScreenIntent.DismissError) }) {
                Text(stringResource(R.string.dismiss), color = MaterialTheme.colorScheme.onError)
            }
        },
        containerColor = MaterialTheme.colorScheme.error
    ) {
        Text(text = stringResource(R.string.error), color = MaterialTheme.colorScheme.onError)
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorSnackbarPreview() {
    ErrorSnackbar()
}