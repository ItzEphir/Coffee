package com.ephirium.coffee.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import kotlin.annotation.AnnotationRetention.BINARY
import kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

@Retention(BINARY)
@Target(ANNOTATION_CLASS, FUNCTION)
@Preview(
    name = "RedLightEn",
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    apiLevel = 31,
    locale = "en",
)
@Preview(
    name = "RedDarkEn",
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    apiLevel = 31,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    locale = "en"
)
@Preview(
    name = "BlueLightEn",
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
    apiLevel = 31,
    locale = "en"
)
@Preview(
    name = "BlueDarkEn",
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
    apiLevel = 31,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    locale = "en"
)
@Preview(
    name = "GreenLightEn",
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
    apiLevel = 31,
    locale = "en"
)
@Preview(
    name = "GreenDarkEn",
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
    apiLevel = 31,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    locale = "en"
)
@Preview(
    name = "YellowLightEn",
    wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE,
    apiLevel = 31,
    locale = "en"
)
@Preview(
    name = "YellowDarkEn",
    wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE,
    apiLevel = 31,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    locale = "en"
)
@Preview(
    name = "RedLightRu",
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    apiLevel = 31,
    locale = "ru",
)
@Preview(
    name = "RedDarkRu",
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    apiLevel = 31,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    locale = "ru"
)
@Preview(
    name = "BlueLightRu",
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
    apiLevel = 31,
    locale = "ru"
)
@Preview(
    name = "BlueDarkRu",
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
    apiLevel = 31,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    locale = "ru"
)
@Preview(
    name = "GreenLightRu",
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
    apiLevel = 31,
    locale = "ru"
)
@Preview(
    name = "GreenDarkRu",
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
    apiLevel = 31,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    locale = "ru"
)
@Preview(
    name = "YellowLightRu",
    wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE,
    apiLevel = 31,
    locale = "ru"
)
@Preview(
    name = "YellowDarkRu",
    wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE,
    apiLevel = 31,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    locale = "ru"
)
annotation class ThemePreview