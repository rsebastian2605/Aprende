package com.example.aprende.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val AprendeDarkColors = darkColorScheme(
    background       = BackgroundDark,
    surface          = SurfaceDark,
    primary          = AccentCyan,
    primaryContainer = AccentCyanDark,
    onPrimary        = TextPrimary,
    onBackground     = TextPrimary,
    onSurface        = TextPrimary,
)

@Composable
fun AprendeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AprendeDarkColors,
        typography  = Typography(),
        content     = content
    )
}

@Composable
fun outlinedTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor   = AccentCyan,
    unfocusedBorderColor = SurfaceDark,
    focusedTextColor     = TextPrimary,
    unfocusedTextColor   = TextPrimary,
    cursorColor          = AccentCyan,
    focusedContainerColor   = CardDark,
    unfocusedContainerColor = CardDark
)
