package com.ephirium.coffee.app.ui.widget

import android.content.Context
import androidx.glance.*
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Box
import com.ephirium.coffee.app.R
import com.ephirium.coffee.app.preferences.PreferenceManager
import com.ephirium.coffee.app.ui.activity.MainActivity
import com.ephirium.coffee.app.ui.components.CoffeeWidgetLayout
import kotlin.random.Random
import kotlin.random.nextInt

class CoffeeWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val preferenceManager = PreferenceManager(context)

        val compliments = context.resources.getStringArray(R.array.compliments).toList()

        if (preferenceManager.compliment == null) {
            preferenceManager.compliment = compliments[Random.nextInt(compliments.indices)]
        }

        provideContent {
            GlanceTheme {
                Box(
                    modifier = GlanceModifier.clickable(
                        actionStartActivity<MainActivity>()
                    ).background(ImageProvider(R.drawable.background_widget))
                ) {
                    CoffeeWidgetLayout(preferenceManager = preferenceManager)
                }
            }
        }
    }

}