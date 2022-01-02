package com.mjpecora.listeningparty.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun Home(viewModel: HomeViewModel) {
    val viewState = viewModel.viewState.collectAsState()
    Scaffold(Modifier.fillMaxSize()) {
        when (val currentViewState = viewState.value) {
            HomeViewState.Loading -> Loading()
            is HomeViewState.Idle -> Idle(currentViewState)
        }
    }
}

@Composable
private fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            strokeWidth = 4.dp
        )
    }
}

@Composable
private fun Idle(viewState: HomeViewState.Idle) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .systemBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text("Welcome, ${viewState.user.userName}")
    }
}