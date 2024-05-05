package com.ephirium.coffee.feature.compliment_editor.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ephirium.coffee.preview.ThemePreview
import com.ephirium.coffee.theme.CoffeeTheme

@Composable
internal fun ErrorMessage(
    modifier: Modifier = Modifier,
    text: String,
    retryText: String,
    onRetryClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetryClick) {
            Text(
                text = retryText,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@ThemePreview
@Composable
private fun ErrorMessagePreview() {
    CoffeeTheme {
        Surface(color = colorScheme.background) {
            Box(modifier = Modifier.fillMaxSize()) {
                ErrorMessage(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Error",
                    retryText = "Retry",
                    onRetryClick = {}
                )
            }
        }
    }
}