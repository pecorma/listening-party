package com.mjpecora.listeningparty.ui.splashscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.systemBarsPadding
import com.mjpecora.listeningparty.ui.theme.blueToBlackGradient


@Composable
fun SplashScreen(viewModel: SplashScreenViewModel) {
    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .background(brush = blueToBlackGradient)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
                .systemBarsPadding()
        ) {
            Text("Splash splash", style = MaterialTheme.typography.h5, color = Color.White)
            viewModel.authenticate()
        }
    }
}