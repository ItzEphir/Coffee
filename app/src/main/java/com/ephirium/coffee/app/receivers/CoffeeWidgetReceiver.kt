package com.ephirium.coffee.app.receivers

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.ephirium.coffee.app.ui.widget.CoffeeWidget

class CoffeeWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = CoffeeWidget()
}