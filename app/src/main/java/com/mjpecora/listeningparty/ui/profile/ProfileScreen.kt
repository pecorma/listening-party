package com.mjpecora.listeningparty.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding
import com.mjpecora.listeningparty.base.Navigator
import com.mjpecora.listeningparty.ui.theme.Blue
import com.mjpecora.listeningparty.ui.theme.Blue200
import com.mjpecora.listeningparty.ui.theme.arrowLeftIcon
import com.mjpecora.listeningparty.util.ui.AutoSizeText

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val userName = remember { mutableStateOf<String?>(null) }
    val email = remember { mutableStateOf<String?>(null) }
    val user = viewModel.userStateFlow.collectAsState()
    userName.value = user.value?.userName
    email.value = user.value?.email
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(16.dp)
        ) {
            TopNavigation(viewModel = viewModel)
            Spacer(modifier = Modifier.height(32.dp))
            UserImage()
            Spacer(modifier = Modifier.height(48.dp))
            ProfileInputField(
                label = "username",
                input = userName
            )
            Spacer(modifier = Modifier.height(12.dp))
            ProfileInputField(
                label = "email",
                input = email,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            SignOutButton(viewModel = viewModel, modifier = Modifier.align(Alignment.Start))
        }
    }
}

@Composable
private fun UserImage() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(90.dp)
            .width(90.dp)
            .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(50))
            .clip(RoundedCornerShape(50))
            .background(color = Color.White.copy(alpha = 0.13f))
            .padding(6.dp)
    ) {
        AutoSizeText(text = "MM")
    }
}

@Composable
private fun SignOutButton(viewModel: ProfileViewModel, modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val context = LocalContext.current
    Text(
        text = "Sign out",
        style = MaterialTheme.typography.overline,
        color = if (isPressed) Blue else { Blue200 },
        modifier = modifier
            .clickable(interactionSource = interactionSource, indication = null) {
                viewModel.signOut(context)
            }
    )
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

@Composable
private fun ProfileInputField(
    modifier: Modifier = Modifier,
    label: String,
    input: MutableState<String?>,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    maxLines: Int = 1
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = input.value ?: "",
        onValueChange = { input.value = it },
        shape = RoundedCornerShape(8.dp),
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        label = { Text(text = label) }
    )
}