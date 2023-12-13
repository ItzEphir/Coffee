package com.ephirium.coffee.app.ui.components

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.*
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.ephirium.coffee.app.R
import com.ephirium.coffee.app.preferences.PreferenceManager
import org.koin.compose.koinInject
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun CoffeeWidgetLayout(preferenceManager: PreferenceManager = koinInject()) {

    var compliment by remember { mutableStateOf(preferenceManager.compliment.toString()) }
    val context = LocalContext.current

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
                text = compliment, style = TextStyle(
                    fontSize = 16.sp,
                    color = GlanceTheme.colors.onPrimary,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ), maxLines = 2, modifier = GlanceModifier.padding(4.dp)
            )

            Button(text = context.applicationContext.resources.getString(R.string.widget_refresh_button), onClick = {
                val compliments = context.applicationContext.resources.getStringArray(R.array.compliments).toList()

                fun getIndex(currentIndex: Int): Int =
                    when (val index = Random.nextInt(compliments.indices)) {
                        currentIndex -> getIndex(currentIndex)
                        else         -> index
                    }

                compliment = compliments[getIndex(compliments.indexOf(compliment))]
                preferenceManager.compliment = compliment
            })
        }
    }
}