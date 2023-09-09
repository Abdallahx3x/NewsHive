package com.example.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Pink80 = Color(0xFFEFB8C8)
val Pink40 = Color(0xFF7D5260)

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val Mauve = Color(0xFF65228F)
val Green = Color(0xFF44C969)
val Yellow = Color(0xABFFEB3B)
val Orange = Color(0xFFEF882A)
val transparent= Color(0x00000000)

val Gray = Color(0xFFBEBEBE)
val LightGray = Color(0xFFEFEFEF)
val DarkGray = Color(0xAB4D4D4D)

val LightPrimary = Color(0xFFFB6A00)
val OnLightPrimary = Color(0xFFFFFFFF)
val LightCard = Color(0xFFFFFFFF)
val LightBorder = Color(0xCCEAEAEA)
val LightBackground = Color(0xFFF5F5F5)
val OnLightBackground = Color(0xFFFFFFFF)
val OnLightBackground87 = Color(0xDE000000)
val OnLightBackground60 = Color(0x99121212)
val OnLightBackground38 = Color(0x61F6F6F6)
val LightStatusBar = Color(0xFF000000)
val LightRed60 = Color(0x99FF3838)
val LightRed = Color(0xFFE82424)

val DarkPrimary = Color(0xFFFB6A00)
val OnDarkPrimary = Color(0xFFFFFFFF)
val DarkCard = Color(0xFF262626)
val DarkBorder = Color(0xFF302F2F)
val DarkBackground = Color(0xFF1F1F1F)
val OnDarkBackground = Color(0xFF262626)
val OnDarkBackground87 = Color(0xDEF6F6F6)
val OnDarkBackground60 = Color(0x99F6F6F6)
val OnDarkBackground38 = Color(0x61F6F6F6)
val DarkStatusBar = Color(0xFFFFFFFF)
val DarkRed60 = Color(0x99FF3838)
val DarkRed = Color(0xFFFF6B6B)

@Immutable
data class CustomColorsPalette(
    val primary: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val border: Color = Color.Unspecified,
    val card: Color = Color.Unspecified,
    val statusBar: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val onBackground87: Color = Color.Unspecified,
    val onBackground60: Color = Color.Unspecified,
    val onBackground38: Color = Color.Unspecified,
    val gray: Color = Color.Unspecified,
    val lightGray: Color = Color.Unspecified,
    val darkGray: Color = Color.Unspecified,
    val yellow: Color = Color.Unspecified,
    val green: Color = Color.Unspecified,
    val mauve: Color = Color.Unspecified,
    val orange: Color = Color.Unspecified,
    val white: Color = Color.Unspecified,
    val black: Color = Color.Unspecified,
    val red: Color = Color.Unspecified,
    val red60: Color = Color.Unspecified,
    val transparent: Color = Color.Unspecified
)