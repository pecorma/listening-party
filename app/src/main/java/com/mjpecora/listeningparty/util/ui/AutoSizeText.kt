package com.mjpecora.listeningparty.util.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

fun autoSizeText(
    readyToDraw: MutableState<Boolean>,
    scaledTextStyle: MutableState<TextStyle>
): (TextLayoutResult) -> Unit = {
    if (it.hasVisualOverflow) {
        scaledTextStyle.value =
            scaledTextStyle.value.copy(fontSize = scaledTextStyle.value.fontSize * 0.95)
    } else {
        readyToDraw.value = true
    }
}

@Composable
fun AutoSizeText(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.h2
) {
    val scaledTextStyle = remember { mutableStateOf(textStyle) }
    val readyToDraw = remember { mutableStateOf(false) }

    Text(
        text = text,
        softWrap = false,
        style = scaledTextStyle.value,
        modifier = modifier.drawWithContent { if (readyToDraw.value) drawContent() },
        textAlign = TextAlign.Center,
        onTextLayout = autoSizeText(readyToDraw, scaledTextStyle)
    )
}
