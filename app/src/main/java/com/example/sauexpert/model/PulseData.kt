package com.example.sauexpert.model

import androidx.compose.ui.graphics.Color
import com.example.sauexpert.ui.theme.Blue4285

data class PulseData(
    val positionOnX: Float = 0f,
    val pulseInMinuteAverage: Float = 0f,
    val time: String = "",
    var colorFocus: Color = Blue4285
)
