package com.example.sauexpert.model

import androidx.compose.ui.graphics.Color
import com.example.sauexpert.ui.theme.Blue4289
import com.example.sauexpert.ui.theme.Pink4294

data class GlucoseData(
    val positionOnX: Float = 0f,
    val glucoseBeforeFood: Float = 0f,
    val glucoseAfterFood: Float = 0f,
    val dateName: String = "",
    var colorFocusBeforeFood: Color = Pink4294,
    var colorFocusAfterFood: Color = Blue4289
)
