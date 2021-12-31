/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.listeningparty.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

val Red300 = Color(0xFFEA6D7E)
val Black900 = Color(0xFF121212)
val Blue200 = Color(0xFF8FCEFF)
val Blue = Color(0xFF004dad)
val Pink100 = Color(0xFFF0BCFE)

val LPColors = darkColors(
    primary = Blue200,
    onPrimary = Black900,
    primaryVariant = Blue,
    secondary = Pink100,
    onSecondary = Black900,
    error = Red300,
    onError = Black900
)