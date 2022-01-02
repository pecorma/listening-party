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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType.Companion.Email
import androidx.compose.ui.text.input.KeyboardType.Companion.Password
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
import com.mjpecora.listeningparty.ui.Screen
import com.mjpecora.listeningparty.ui.theme.Blue
import com.mjpecora.listeningparty.ui.theme.Blue200
import com.mjpecora.listeningparty.ui.theme.Pink100
import com.mjpecora.listeningparty.ui.theme.eyeClosedIcon
import com.mjpecora.listeningparty.ui.theme.eyeOpenIcon
import com.mjpecora.listeningparty.ui.theme.googleGIcon
import com.mjpecora.listeningparty.ui.theme.lockIcon
import com.mjpecora.listeningparty.ui.theme.mailIcon

@Composable
fun LoginScreen(viewModel: LoginViewModel, navigate: (Screen.Login.Destination) -> Unit) {
    val viewState: LoginViewState by viewModel.viewState.collectAsState()
    if (viewState is LoginViewState.Success) {
        navigate(Screen.Login.Destination.HOME)
    } else {
        LoginView(viewModel)
    }
}

@Composable
private fun LoginView(viewModel: LoginViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        val emailState = remember { mutableStateOf("") }
        val passwordState = remember { mutableStateOf("") }
        val focusRequester = List(3) { FocusRequester() }

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
            LoginInputField(
                viewModel,
                "email",
                mailIcon,
                KeyboardOptions(keyboardType = Email, imeAction = ImeAction.Next),
                emailState,
                focusRequester[0],
                focusRequester[1],
                emailState.value.trim(),
                passwordState.value.trim()
            )
            Spacer(modifier = Modifier.height(16.dp))
            LoginInputField(
                viewModel,
                "password",
                lockIcon,
                KeyboardOptions(keyboardType = Password, imeAction = ImeAction.Go),
                passwordState,
                focusRequester[1],
                focusRequester[2],
                emailState.value.trim(),
                passwordState.value.trim()
            )
            Spacer(modifier = Modifier.height(24.dp))
            LoginButton(viewModel, emailState.value.trim(), passwordState.value.trim())
            Spacer(modifier = Modifier.height(16.dp))
            SignUpView(viewModel)
            Spacer(modifier = Modifier.height(64.dp))
            ConnectWithDivider()
            Spacer(modifier = Modifier.height(32.dp))
            GoogleLoginButton(viewModel)
        }
    }
}

internal val horizontalButtonGradient = Brush.horizontalGradient(
    colors = listOf(Color(0xFF008FF1), Color(0xFF61BBFE))
)

@Composable
private fun SignUpView(viewModel: LoginViewModel) {
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
                    viewModel.updateViewState(
                        LoginViewState.Success(Screen.Login.Destination.CREATE_ACCOUNT)
                    )
                }
        )
    }
}

@Composable
private fun LoginButton(viewModel: LoginViewModel, email: String, password: String) {
    Button(
        onClick = { viewModel.signInWithEmailPassword(email, password) },
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
private fun LoginInputField(
    viewModel: LoginViewModel,
    placeHolder: String,
    leadingIcon: Painter,
    keyboardOptions: KeyboardOptions,
    input: MutableState<String>,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester,
    email: String,
    password: String
) {
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
                placeholderColor = Color.White,
                backgroundColor = Color.Transparent
            ),
        leadingIcon = {
            Icon(leadingIcon, "", tint = if (isTinted.value) { Blue200 } else { Color.White })
        },
        visualTransformation = if (keyboardOptions.keyboardType == Password && !isVisible.value) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if (keyboardOptions.keyboardType == Password) {
                Icon(
                    if (isVisible.value) { eyeOpenIcon } else { eyeClosedIcon },
                    "",
                    tint = if (isTinted.value) { Blue200 } else { Color.White },
                    modifier = Modifier
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            isVisible.value = isVisible.value.not()
                        }
                )
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onNext = {
                nextFocusRequester.requestFocus()
            },
            onGo = {
                viewModel.signInWithEmailPassword(email, password)
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (keyboardOptions.keyboardType == Password && it.key.keyCode == Key.Enter.keyCode) {
                    viewModel.signInWithEmailPassword(email, password)
                    true
                } else if (keyboardOptions.keyboardType == Email && it.key.keyCode == Key.Tab.keyCode) {
                    nextFocusRequester.requestFocus()
                    true
                } else {
                    false
                }
            }
            .onFocusChanged { isTinted.value = it.hasFocus }
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL)
fun Preview() = LoginScreen(hiltViewModel()) {}
