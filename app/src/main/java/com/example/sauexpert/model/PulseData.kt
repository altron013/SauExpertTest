package com.example.sauexpert.model

import androidx.compose.ui.graphics.Color

data class PulseData(
    val positionOnX: Float = 0f,
    val pulseInMinuteAverage: Float = 0f,
    val time: String = "",
    var colorFocus: Color = Color.Blue
)
