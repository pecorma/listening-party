package com.mjpecora.listeningparty.ui.createaccount

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding
import com.mjpecora.listeningparty.base.Navigator
import com.mjpecora.listeningparty.ui.theme.*
import com.mjpecora.listeningparty.util.isPasswordValid

@Composable
fun CreateAccountScreen(
    viewModel: CreateAccountViewModel
) {
    CreateAccountView(viewModel = viewModel)
}

data class InputFieldState(var input: String = "", val error: String? = null)

@Composable
private fun CreateAccountView(
    viewModel: CreateAccountViewModel,
) {
    val emailState = remember { mutableStateOf(InputFieldState()) }
    val passwordState = remember { mutableStateOf(InputFieldState()) }
    val usernameState = remember { mutableStateOf(InputFieldState()) }
    val viewState = viewModel.viewState.collectAsState()
    emailState.value = viewState.value.createAccount.let {
        InputFieldState(it.email, it.emailError)
    }
    passwordState.value = viewState.value.createAccount.let {
        InputFieldState(it.password, it.passwordError)
    }
    usernameState.value = viewState.value.createAccount.let {
        InputFieldState(it.userName, it.userNameError)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
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
                textState = emailState,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(16.dp))
            CreateAccountInputField(
                placeHolder = "password",
                leadingIcon = lockIcon,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                textState = passwordState,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(2.dp))
            Text(
                "at least 8 characters",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 32.dp)
            )
            Spacer(Modifier.height(16.dp))
            CreateAccountInputField(
                placeHolder = "username",
                leadingIcon = userIcon,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Go),
                textState = usernameState,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(16.dp))
            SignUpButton(
                viewModel,
                viewState.value,
                emailState.value.input.trim(),
                passwordState.value.input.trim(),
                usernameState.value.input.trim(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(48.dp))
            OrWithDivider(Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(32.dp))
            GoogleSignUpButton(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
private fun SignUpButton(
    viewModel: CreateAccountViewModel,
    viewState: CreateAccountViewState,
    email: String,
    password: String,
    userName: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Button(
            onClick = { viewModel.createUser(email, password, userName) },
            enabled = password.isPasswordValid(),
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
                if (viewState.isLoading) {
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
private fun GoogleSignUpButton(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Button(
            onClick = {  },
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
private fun OrWithDivider(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
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
    textState: MutableState<InputFieldState>,
    modifier: Modifier = Modifier
) {
    val isTinted = rememberSaveable { mutableStateOf(false) }
    val isVisible = rememberSaveable { mutableStateOf(false) }

    Column(modifier) {
        OutlinedTextField(
            value = textState.value.input,
            onValueChange = {
                textState.value = textState.value.copy(input = it, error = null)
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
                Icon(leadingIcon, "", tint = if (isTinted.value) { Blue200 } else { Color.White })
            },
            isError = textState.value.error != null,
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
        textState.value.error?.let {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.error,
                modifier = modifier
            )
        }
    }


}