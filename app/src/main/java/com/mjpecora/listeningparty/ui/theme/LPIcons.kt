package com.mjpecora.listeningparty.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.mjpecora.listeningparty.R


val userIcon: Painter
    @Composable get() = painterResource(id = R.drawable.ic_user)

val lockIcon: Painter
    @Composable get() = painterResource(id = R.drawable.ic_lock)

val eyeClosedIcon: Painter
    @Composable get() = painterResource(id = R.drawable.ic_eye_closed)

val eyeOpenIcon: Painter
    @Composable get() = painterResource(id = R.drawable.ic_eye_open)

val googleGIcon: Painter
    @Composable get() = painterResource(id = R.drawable.ic_google_g)

val arrowLeftIcon: Painter
    @Composable get() = painterResource(id = R.drawable.ic_arrow_left)

val mailIcon: Painter
    @Composable get() = painterResource(id = R.drawable.ic_mail)