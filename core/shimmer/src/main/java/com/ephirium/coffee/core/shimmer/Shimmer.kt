package com.ephirium.coffee.core.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlin.time.Duration

@Composable
fun Shimmer(modifier: Modifier = Modifier, duration: Duration, colors: ShimmerColors = ShimmerColors()){
    Box(modifier = modifier.shimmerEffect(duration, colors))
}