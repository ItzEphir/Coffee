package com.ephirium.coffee.core.shimmer

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import kotlin.time.Duration
import kotlin.time.DurationUnit.MILLISECONDS

fun Modifier.shimmerEffect(duration: Duration, colors: ShimmerColors): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "ShimmerTransition")
    val startOffset by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(duration.toInt(MILLISECONDS), easing = LinearOutSlowInEasing)
        ),
        label = "ShimmerOffset"
    )
    
    val brushColors = listOf(
        parseColor(colors.primary, MaterialTheme.colorScheme.primaryContainer),
        parseColor(colors.secondary, MaterialTheme.colorScheme.tertiaryContainer),
        parseColor(colors.tertiary, MaterialTheme.colorScheme.secondaryContainer),
    )
    
    background(
        brush = Brush.linearGradient(
            colors = brushColors,
            start = Offset(startOffset, 0f),
            end = Offset(startOffset + size.width.toFloat(), size.height.toFloat()),
        )
    ).onGloballyPositioned {
        size = it.size
    }
}

private fun parseColor(color: Color, default: Color) = when (color) {
    Color.Unspecified -> default
    else              -> color
}