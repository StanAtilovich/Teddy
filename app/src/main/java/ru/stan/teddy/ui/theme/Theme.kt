package ru.stan.teddy.ui.theme

import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun TeddyTheme(
    content: @Composable () ->Unit
) = androidx.compose.material.MaterialTheme(
    colors = lightColors(
        primary = Color(0xFF1857C5),
        primaryVariant = Color(0xFF123DAA),
        secondary = Color(0xFF03C4DD),
        secondaryVariant = Color(0xFF03B2C9),
    )
) {
    content()
}