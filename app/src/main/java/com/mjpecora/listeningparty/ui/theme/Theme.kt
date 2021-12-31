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
