package com.example.sauexpert.model

import androidx.compose.ui.graphics.Color
import com.example.sauexpert.ui.theme.Blue4289
import com.example.sauexpert.ui.theme.Pink4294

data class GlucoseData(
    val positionOnX: Float = 0f,
    val positionOnYBeforeFood: Float = 0f,
    val positionOnYAfterFood: Float = 0f,
    val glucoseBeforeFood: Int = 0,
    val glucoseAfterFood: Int = 0,
    val dateName: String = "",
    var colorFocusBeforeFood: Color = Pink4294,
    var colorFocusAfterFood: Color = Blue4289
)
