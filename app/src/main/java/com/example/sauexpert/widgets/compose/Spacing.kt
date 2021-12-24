package com.example.sauexpert.widgets.compose

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacingVertical(height: Dp) {
    Spacer(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
    )
}
@Composable
fun SpacingHorizontal(width: Dp) {
    Spacer(
        modifier = Modifier
            .width(width)
            .fillMaxHeight()
    )
}