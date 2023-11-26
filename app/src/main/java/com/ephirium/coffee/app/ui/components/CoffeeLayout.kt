package com.ephirium.coffee.app.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.ephirium.coffee.app.preferences.PreferenceManager

@Composable
fun CoffeeLayout(preferenceManager: PreferenceManager) {
    Row(
        modifier = GlanceModifier.fillMaxSize().background(GlanceTheme.colors.primary)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = GlanceModifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = preferenceManager.compliment.toString(), style = TextStyle(
                    fontSize = 16.sp,
                    color = GlanceTheme.colors.onPrimary,
                    fontWeight = FontWeight.Bold
                ), maxLines = 1, modifier = GlanceModifier.padding(4.dp)
            )
        }
    }
}