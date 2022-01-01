package com.mjpecora.listeningparty.ui.login

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.mjpecora.listeningparty.ui.theme.*

@Composable
fun Login(loginViewModel: LoginViewModel, navigate: (Navigate) -> Unit) {
    val viewState: LoginViewState by loginViewModel.viewState.collectAsState()
    if (viewState is LoginViewState.CreateAccount) {
        navigate(Navigate.CREATE_ACCOUNT)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(120.dp))
            Text(
                text = "Login",
                style = MaterialTheme.typography.h5,
            )
            Spacer(modifier = Modifier.height(24.dp))
            LoginInputField("username", userIcon, LoginInputField.USERNAME)
            Spacer(modifier = Modifier.height(16.dp))
            LoginInputField("password", lockIcon, LoginInputField.PASSWORD)
            Spacer(modifier = Modifier.height(24.dp))
            LoginButton(navigate)
            Spacer(modifier = Modifier.height(16.dp))
            SignUpView()
            Spacer(modifier = Modifier.height(64.dp))
            ConnectWithDivider()
            Spacer(modifier = Modifier.height(32.dp))
            GoogleLoginButton(loginViewModel)
        }
    }
}

private enum class LoginInputField { PASSWORD, USERNAME }

private val horizontalButtonGradient = Brush.horizontalGradient(
    colors = listOf(Color(0xFF008FF1), Color(0xFF61BBFE))
)


@Composable
private fun SignUpView() {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Row {
        Text(
            text = "Don't have an account?",
            style = MaterialTheme.typography.caption,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Sign up.",
            style = MaterialTheme.typography.overline,
            color = if (isPressed) Blue else { Blue200 },
            modifier = Modifier
                .clickable(interactionSource = interactionSource, indication = null) {

                }
        )
    }
}

@Composable
private fun LoginButton(navigate: (Navigate) -> Unit) {
    Button(
        onClick = { navigate(Navigate.HOME) },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        elevation = ButtonDefaults.elevation(4.dp),
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
private fun GoogleLoginButton(viewModel: LoginViewModel) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            viewModel.signInWithAuthCredential(credential)
        } catch (e: ApiException) {
            Log.d("GoogleAuth", "failed to sign in")
        }
    }
    val context = LocalContext.current
    Button(
        onClick = {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("17774155879-i64ne7v32pda7vatjupn9iiflg0a0i31.apps.googleusercontent.com")
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(context, gso)
            launcher.launch(googleSignInClient.signInIntent)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Pink100),
        contentPadding = PaddingValues(),
        elevation = ButtonDefaults.elevation(4.dp),
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
        Divider(Modifier.weight(1.0f), color = Color.White)
        Spacer(Modifier.width(12.dp))
        Text("or connect with", style = MaterialTheme.typography.caption)
        Spacer(Modifier.width(12.dp))
        Divider(Modifier.weight(1.0f), color = Color.White)
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
        singleLine = true,
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
                            interactionSource = MutableInteractionSource(),
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
@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL)
fun Preview() = Login(hiltViewModel()) {}
