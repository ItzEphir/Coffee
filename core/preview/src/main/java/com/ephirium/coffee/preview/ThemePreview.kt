package com.ephirium.coffee.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers

@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Preview(name = "Red", wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE, apiLevel = 31)
@Preview(name = "Blue", wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE, apiLevel = 31)
@Preview(name = "Green", wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE, apiLevel = 31)
@Preview(name = "Yellow", wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE, apiLevel = 31)
annotation class ThemePreview