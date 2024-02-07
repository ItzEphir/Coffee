package com.ephirium.coffee.app.presentation.ui.components.screens.compliment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.ephirium.coffee.app.presentation.ui.theme.CoffeeTheme

@Composable
fun Loading(onLaunch: suspend () -> Unit) {
    
    LaunchedEffect(key1 = Unit, block = { onLaunch() })
    
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
    
}

@PreviewScreenSizes
@Preview
@Composable
fun LoadingPreview() {
    CoffeeTheme {
        Loading(onLaunch = {})
    }
}