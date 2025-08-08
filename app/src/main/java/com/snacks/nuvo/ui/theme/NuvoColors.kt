package com.snacks.nuvo.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class NuvoColors(
    darkGradient1: Color = Color(0xFF32757E),
    darkGradient2: Color = Color(0xFF53A790),
    lightGradient1: Color = Color(0xFF53A790),
    lightGradient2: Color = Color(0xFF99D387),
    mainGreen: Color = Color(0xFF53A790),
    subLemon: Color = Color(0xFFF9FA83),
    subLightGreen: Color = Color(0xFF99D387),
    subNavy: Color = Color(0xFF2B3F6C),
    gray1: Color = Color(0xFFF5F5F5),
    gray2: Color = Color(0XFFF1F1F1),
    gray3: Color = Color(0xFFD9D9D9),
    gray4: Color = Color(0xFFA9A9A9),
    gray5: Color = Color(0xFF797979),
    gray6: Color = Color(0xFF525252),
    white: Color = Color(0xFFFFFFFF),
    black: Color = Color(0xFF000000),
) {
    var darkGradient1 by mutableStateOf(darkGradient1)
        private set
    var darkGradient2 by mutableStateOf(darkGradient2)
        private set
    var lightGradient1 by mutableStateOf(lightGradient1)
        private set
    var lightGradient2 by mutableStateOf(lightGradient2)
        private set
    var mainGreen by mutableStateOf(mainGreen)
        private set
    var subLemon by mutableStateOf(subLemon)
        private set
    var subLightGreen by mutableStateOf(subLightGreen)
        private set
    var subNavy by mutableStateOf(subNavy)
        private set
    var gray1 by mutableStateOf(gray1)
        private set
    var gray2 by mutableStateOf(gray2)
        private set
    var gray3 by mutableStateOf(gray3)
        private set
    var gray4 by mutableStateOf(gray4)
        private set
    var gray5 by mutableStateOf(gray5)
        private set
    var gray6 by mutableStateOf(gray6)
        private set
    var white by mutableStateOf(white)
        private set
    var black by mutableStateOf(black)
        private set

    fun copy(
        darkGradient1: Color = this.darkGradient1,
        darkGradient2: Color = this.darkGradient2,
        lightGradient1: Color = this.lightGradient1,
        lightGradient2: Color = this.lightGradient2,
        mainGreen: Color = this.mainGreen,
        subLemon: Color = this.subLemon,
        subLightGreen: Color = this.subLightGreen,
        subNavy: Color = this.subNavy,
        gray1: Color = this.gray1,
        gray2: Color = this.gray2,
        gray3: Color = this.gray3,
        gray4: Color = this.gray4,
        gray5: Color = this.gray5,
        gray6: Color = this.gray6,
        white: Color = this.white,
        black: Color = this.black,
    ): NuvoColors {
        return NuvoColors(
            darkGradient1 = darkGradient1,
            darkGradient2 = darkGradient2,
            lightGradient1 = lightGradient1,
            lightGradient2 = lightGradient2,
            mainGreen = mainGreen,
            subLemon = subLemon,
            subLightGreen = subLightGreen,
            subNavy = subNavy,
            gray1 = gray1,
            gray2 = gray2,
            gray3 = gray3,
            gray4 = gray4,
            gray5 = gray5,
            gray6 = gray6,
            white = white,
            black = black
        )
    }

    fun updateColorFrom(other: NuvoColors) {
        darkGradient1 = other.darkGradient1
        darkGradient2 = other.darkGradient2
        lightGradient1 = other.lightGradient1
        lightGradient2 = other.lightGradient2
        mainGreen = other.mainGreen
        subLemon = other.subLemon
        subLightGreen = other.subLightGreen
        subNavy = other.subNavy
        gray1 = other.gray1
        gray2 = other.gray2
        gray3 = other.gray3
        gray4 = other.gray4
        gray5 = other.gray5
        gray6 = other.gray6
        white = other.white
        black = other.black
    }
}

val LocalColors = staticCompositionLocalOf { NuvoColors() }
