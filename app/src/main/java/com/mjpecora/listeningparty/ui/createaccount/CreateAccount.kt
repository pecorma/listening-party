package com.mjpecora.listeningparty.ui.createaccount

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.mjpecora.listeningparty.base.Navigator
import com.mjpecora.listeningparty.ui.theme.*

@Composable
fun CreateAccountScreen(
    viewModel: CreateAccountViewModel
) {
    val viewState = viewModel.viewState.collectAsState()

    CreateAccountView(viewModel = viewModel, viewState = viewState.value)

}


@Composable
private fun CreateAccountView(
    viewModel: CreateAccountViewModel,
    viewState: CreateAccountViewState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        val emailState = remember { mutableStateOf("") }
        val passwordState = remember { mutableStateOf("") }
        val usernameState = remember { mutableStateOf("") }

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .systemBarsPadding()
        ) {
            TopNavigation(viewModel)
            Spacer(Modifier.height(48.dp))
            CreateAccountInputField(
                placeHolder = "email",
                leadingIcon = mailIcon,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                textState = emailState
            )
            Spacer(Modifier.height(16.dp))
            CreateAccountInputField(
                placeHolder = "password",
                leadingIcon = lockIcon,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                textState = passwordState
            )
            Spacer(Modifier.height(16.dp))
            CreateAccountInputField(
                placeHolder = "username",
                leadingIcon = userIcon,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Go),
                textState = usernameState
            )
            Spacer(Modifier.height(16.dp))
            SignUpButton(
                viewModel,
                viewState,
                emailState.value.trim(),
                passwordState.value.trim(),
                usernameState.value.trim()
            )
            Spacer(Modifier.height(48.dp))
            OrWithDivider()
            Spacer(modifier = Modifier.height(32.dp))
            GoogleSignUpButton(viewModel = viewModel)
        }
    }
}

@Composable
private fun SignUpButton(
    viewModel: CreateAccountViewModel,
    viewState: CreateAccountViewState,
    email: String,
    password: String,
    userName: String
) {
    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
        Button(
            onClick = { viewModel.createUser(email, password, userName) },
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
                if (viewState is CreateAccountViewState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(ButtonDefaults.MinHeight.times(0.9f))
                    )
                } else {
                    Text("Sign up")
                }
            }
        }
    }
}

@Composable
private fun GoogleSignUpButton(viewModel: CreateAccountViewModel) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            // TODO(): query [CreateAccountViewModel]
        } catch (e: ApiException) {
            Log.d("GoogleAuth", "failed to sign in")
        }
    }
    val context = LocalContext.current
    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
        Button(
            onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("17774155879-i64ne7v32pda7vatjupn9iiflg0a0i31.apps.googleusercontent.com")
                    .requestEmail()
                    .build()
                val googleSignInClient = GoogleSignIn.getClient(context, gso)
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
                Text("Google sign up")
            }
        }
    }
}

@Composable
private fun OrWithDivider() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(Modifier.weight(1.0f), color = Color.White)
        Spacer(Modifier.width(12.dp))
        Text("or with", style = MaterialTheme.typography.caption)
        Spacer(Modifier.width(12.dp))
        Divider(Modifier.weight(1.0f), color = Color.White)
    }
}

@Composable
private fun TopNavigation(viewModel: CreateAccountViewModel) {
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
        Text("Create account", style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.width(24.dp))
    }
}

@Composable
private fun CreateAccountInputField(
    placeHolder: String,
    leadingIcon: Painter,
    keyboardOptions: KeyboardOptions,
    textState: MutableState<String>
) {
    val isTinted = rememberSaveable { mutableStateOf(false) }
    val isVisible = rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        OutlinedTextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
            },
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
                androidx.compose.material.Icon(leadingIcon, "", tint = if (isTinted.value) { Blue200 } else { Color.White })
            },
            trailingIcon = {
                if (keyboardOptions.keyboardType == KeyboardType.Password) {
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
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isTinted.value = it.hasFocus }
        )
    }

}