package com.example.sauexpert.model

import androidx.compose.ui.graphics.Color
import com.example.sauexpert.ui.theme.Gray50

data class HRVData(
    val positionOnX: Float = 0f,
    val hourOfHRV: Float = 0f,
    val dateName: String = "",
    var colorFocus: Color = Gray50
)
