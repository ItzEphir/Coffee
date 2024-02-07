package com.ephirium.coffee.app.presentation.ui.components.screens.compliment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Error() {
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "Error", modifier = Modifier.align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
internal fun ErrorPreview() {
    Error()
}