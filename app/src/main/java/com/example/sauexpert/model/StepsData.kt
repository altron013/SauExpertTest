package com.example.sauexpert.model

import androidx.compose.ui.graphics.Color

data class StepsData(
    val positionOnX: Float = 0f,
    val stepsPerDay: Float = 0f,
    val dateName: String = "16.12",
    var colorFocus: Color = Color(250, 218, 221)
)