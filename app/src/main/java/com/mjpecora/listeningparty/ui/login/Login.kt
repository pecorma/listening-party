package com.mjpecora.listeningparty.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mjpecora.listeningparty.ui.theme.eyeClosedIcon
import com.mjpecora.listeningparty.ui.theme.eyeOpenIcon
import com.mjpecora.listeningparty.ui.theme.lockIcon
import com.mjpecora.listeningparty.ui.theme.userIcon

@Composable
fun Login(navigateToHome: () -> Unit) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Login",
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            LoginInputField("username", userIcon, LoginInputField.USERNAME)
            Spacer(modifier = Modifier.height(16.dp))
            LoginInputField("password", lockIcon, LoginInputField.PASSWORD)
            Spacer(modifier = Modifier.height(24.dp))
            LoginButton(navigateToHome)
        }
    }
}

enum class LoginInputField { PASSWORD, USERNAME }

val horizontalButtonGradient = Brush.horizontalGradient(
    colors = listOf(Color(0xFF008FF1), Color(0xFF61BBFE))
)

@Composable
fun LoginButton(navigate: () -> Unit) {
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
fun LoginInputField(placeHolder: String, leadingIcon: Painter, field: LoginInputField) {
    val input = rememberSaveable { mutableStateOf("") }
    val visible = rememberSaveable { mutableStateOf(false) }
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
            Icon(leadingIcon, "", tint = Color.White)
        },
        visualTransformation = if (field == LoginInputField.PASSWORD && !visible.value) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if (field == LoginInputField.PASSWORD) {
                Icon(
                    if (visible.value) { eyeOpenIcon} else { eyeClosedIcon },
                    "",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable {
                            visible.value = visible.value.not()
                        }
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = if (field == LoginInputField.PASSWORD) {
            KeyboardType.Password
        } else {
            KeyboardType.Text
        }),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun Preview() = Login {}
