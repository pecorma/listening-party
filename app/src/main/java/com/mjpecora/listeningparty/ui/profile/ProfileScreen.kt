package com.mjpecora.listeningparty.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding
import com.mjpecora.listeningparty.base.Navigator
import com.mjpecora.listeningparty.ui.theme.arrowLeftIcon

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(16.dp)
        ) {
            TopNavigation(viewModel = viewModel)
        }
    }
}

@Composable
private fun TopNavigation(viewModel: ProfileViewModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = { viewModel.navigate(Navigator.NavTarget.Pop) },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(painter = arrowLeftIcon, contentDescription = "", tint = Color.White)
        }
        Text("Profile", style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.width(24.dp))
    }
}