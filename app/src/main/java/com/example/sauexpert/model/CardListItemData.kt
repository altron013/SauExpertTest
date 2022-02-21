package com.example.sauexpert.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

data class CardListItemData(
    val text: String,
    val image: Painter? = null,
    val dayNumber: String? = null,
    val colorBackground: Color = Color.White,
    val colorText: Color = Color.Black
)
