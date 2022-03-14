package com.example.sauexpert.widgets.compose.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.ui.theme.Green57C3A7
import com.example.sauexpert.widgets.compose.MainButton

@Composable
fun MainButtonsInRow(
    modifier: Modifier = Modifier,
    textForOutlineBtn: String? = null,
    iconForOutlineBtn: Int? = null,
    textForMainBtn: String? = null,
    iconForMainBtn: Int? = null,
    buttonHeight: Dp = 50.dp,
    sizeText: TextUnit = 16.sp,
    weightOfFirstButton: Float = 0.4f,
    contentColor: Color = Color.Red,
    borderColor: Color = Color.Red,
    onClickForOutlineBtn: () -> Unit,
    onClickForMainBtn: () -> Unit,
    enableStateForOutlineBtn: Boolean,
    enableStateForMainBtn: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedMainButton(
            text = textForOutlineBtn,
            icon = iconForOutlineBtn,
            onClick = onClickForOutlineBtn,
            enableState = enableStateForOutlineBtn,
            buttonHeight = buttonHeight,
            textColor = contentColor,
            borderColor = borderColor,
            sizeText = sizeText,
            modifier = Modifier
                .weight(weightOfFirstButton)

        )

        Spacer(modifier = Modifier.width(12.dp))

        MainButton(
            text = textForMainBtn,
            icon = iconForMainBtn,
            onClick = onClickForMainBtn,
            enableState = enableStateForMainBtn,
            buttonHeight = buttonHeight,
            sizeText = sizeText,
            modifier = Modifier
                .weight(1f - weightOfFirstButton)
        )
    }
}