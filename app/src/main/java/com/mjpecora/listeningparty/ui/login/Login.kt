package com.mjpecora.listeningparty.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mjpecora.listeningparty.ui.theme.*

@Composable
fun Login(navigateToHome: () -> Unit) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
        ) {
            Spacer(modifier = Modifier.height(120.dp))
            Text(
                text = "Login",
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(24.dp))
            LoginInputField("username", userIcon, LoginInputField.USERNAME)
            Spacer(modifier = Modifier.height(16.dp))
            LoginInputField("password", lockIcon, LoginInputField.PASSWORD)
            Spacer(modifier = Modifier.height(24.dp))
            LoginButton(navigateToHome)
            Spacer(modifier = Modifier.height(64.dp))
            ConnectWithDivider()
            Spacer(modifier = Modifier.height(32.dp))
            GoogleLoginButton()
        }
    }
}

private enum class LoginInputField { PASSWORD, USERNAME }

private val horizontalButtonGradient = Brush.horizontalGradient(
    colors = listOf(Color(0xFF008FF1), Color(0xFF61BBFE))
)

@Composable
private fun LoginButton(navigate: () -> Unit) {
    Button(
        onClick = { navigate() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(horizontalButtonGradient)
                .fillMaxWidth()
                .height(ButtonDefaults.MinHeight)
        ) {
            Text("Log in")
        }
    }
}

@Composable
private fun GoogleLoginButton() {
    Button(
        onClick = {  TODO() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Pink100),
        contentPadding = PaddingValues(),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .height(ButtonDefaults.MinHeight)
        ) {
            Image(painter = googleGIcon, contentDescription = "")
            Spacer(modifier = Modifier.width(24.dp))
            Text("Google sign in")
        }
    }
}

@Composable
private fun ConnectWithDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(Modifier.weight(1.0f))
        Spacer(Modifier.width(12.dp))
        Text("or connect with", style = MaterialTheme.typography.caption)
        Spacer(Modifier.width(12.dp))
        Divider(Modifier.weight(1.0f))
    }
}

@Composable
private fun LoginInputField(placeHolder: String, leadingIcon: Painter, field: LoginInputField) {
    val input = rememberSaveable { mutableStateOf("") }
    val isVisible = rememberSaveable { mutableStateOf(false) }
    val isTinted = rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = input.value,
        onValueChange = { input.value = it },
        textStyle = MaterialTheme.typography.body1,
        placeholder = { Text(placeHolder) },
        colors = TextFieldDefaults
            .textFieldColors(
                textColor = Color.White,
                placeholderColor = Color.White
            ),
        leadingIcon = {
            Icon(leadingIcon, "", tint = if (isTinted.value) { Blue200 } else { Color.White })
        },
        visualTransformation = if (field == LoginInputField.PASSWORD && !isVisible.value) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if (field == LoginInputField.PASSWORD) {
                Icon(
                    if (isVisible.value) { eyeOpenIcon } else { eyeClosedIcon },
                    "",
                    tint = if (isTinted.value) { Blue200 } else { Color.White },
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { isVisible.value = isVisible.value.not() }
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = if (field == LoginInputField.PASSWORD) {
            KeyboardType.Password
        } else {
            KeyboardType.Text
        }),
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { isTinted.value = it.hasFocus }
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun Preview() = Login {}
