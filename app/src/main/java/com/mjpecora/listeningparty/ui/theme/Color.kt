package com.mjpecora.listeningparty.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

val Red300 = Color(0xFFEA6D7E)
val Black900 = Color(0xFF121212)
val Blue200 = Color(0xFF8FCEFF)
val Blue = Color(0xFF004DAD)
val Pink100 = Color(0xFFEBEEFF)

val LPColors = darkColors(
    primary = Blue200,
    onPrimary = Black900,
    primaryVariant = Blue,
    secondary = Pink100,
    onSecondary = Black900,
    error = Red300,
    onError = Black900,
)