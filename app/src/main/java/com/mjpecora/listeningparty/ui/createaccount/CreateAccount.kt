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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.mjpecora.listeningparty.ui.login.horizontalButtonGradient
import com.mjpecora.listeningparty.ui.theme.Blue200
import com.mjpecora.listeningparty.ui.theme.Pink100
import com.mjpecora.listeningparty.ui.theme.arrowLeftIcon
import com.mjpecora.listeningparty.ui.theme.eyeClosedIcon
import com.mjpecora.listeningparty.ui.theme.eyeOpenIcon
import com.mjpecora.listeningparty.ui.theme.googleGIcon
import com.mjpecora.listeningparty.ui.theme.lockIcon
import com.mjpecora.listeningparty.ui.theme.mailIcon
import com.mjpecora.listeningparty.ui.theme.userIcon

@Composable
fun CreateAccount(viewModel: CreateAccountViewModel, navigateBack: () -> Unit) {
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
            TopNavigation(navigateBack)
            Spacer(Modifier.height(48.dp))
            CreateAccountInputField(
                placeHolder = "email",
                leadingIcon = mailIcon,
                keyboardType = KeyboardType.Email
            )
            Spacer(Modifier.height(16.dp))
            CreateAccountInputField(
                placeHolder = "password",
                leadingIcon = lockIcon,
                keyboardType = KeyboardType.Password
            )
            Spacer(Modifier.height(16.dp))
            CreateAccountInputField(
                placeHolder = "username",
                leadingIcon = userIcon,
                keyboardType = KeyboardType.Text
            )
            Spacer(Modifier.height(16.dp))
            SignUpButton()
            Spacer(Modifier.height(48.dp))
            OrWithDivider()
            Spacer(modifier = Modifier.height(32.dp))
            GoogleSignUpButton(viewModel = viewModel)
        }
    }
}

@Composable
private fun SignUpButton() {
    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
        Button(
            onClick = { /* TODO() */ },
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
                Text("Sign up")
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
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
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
private fun TopNavigation(navigateBack: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { navigateBack() }, modifier = Modifier.size(24.dp)) {
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
    keyboardType: KeyboardType
) {
    val input = rememberSaveable { mutableStateOf("") }
    val isTinted = rememberSaveable { mutableStateOf(false) }
    val isVisible = rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
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
                androidx.compose.material.Icon(leadingIcon, "", tint = if (isTinted.value) { Blue200 } else { Color.White })
            },
            trailingIcon = {
                if (keyboardType == KeyboardType.Password) {
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
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isTinted.value = it.hasFocus }
        )
    }

}