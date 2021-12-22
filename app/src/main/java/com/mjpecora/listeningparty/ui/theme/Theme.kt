/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.listeningparty.ui.theme

import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun LPTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
        MaterialTheme(
            colors = LPColors,
            content = content,
            typography = LPTypography,
            shapes = LPShapes
        )
    }
}
