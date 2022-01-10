package com.mjpecora.listeningparty.util


fun String.isPasswordValid() =
    "[~`!@#\$%^&*()-_+=|}\\]{\\[\"':;?/>.<,a-zA-Z0-9<\\n]{8,}".toRegex().matches(this)