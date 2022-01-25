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
import androidx.compose.ui.unit.dp
import com.example.sauexpert.ui.theme.Green57C3A7
import com.example.sauexpert.widgets.compose.MainButton

@Composable
fun MainButtonsInRow(
    modifier: Modifier = Modifier,
    textForOutlineBtn: String,
    textForMainBtn: String,
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
            onClick = onClickForOutlineBtn,
            enableState = enableStateForOutlineBtn,
            modifier = Modifier
                .weight(0.4f),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.onPrimary,
                contentColor = Color.Red,
            ),
            textColor = Color.Red
        )

        Spacer(modifier = Modifier.width(12.dp))

        MainButton(
            text = textForMainBtn,
            onClick = onClickForMainBtn,
            enableState = enableStateForMainBtn,
            modifier = Modifier
                .weight(0.6f)
        )
    }
}

@Composable
fun MainButtonsInRowWithIcon(
    modifier: Modifier = Modifier,
    textForOutlineBtn: String,
    iconForOutlineBtn: Int? = null,
    textForMainBtn: String,
    iconForMainBtn: Painter,
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
            modifier = Modifier
                .weight(0.4f),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.onPrimary,
                contentColor = Color.Red,
            ),
            textColor = Color.Red
        )

        Spacer(modifier = Modifier.width(12.dp))

        MainButtonWithIcon(
            text = textForMainBtn,
            icon = iconForMainBtn,
            onClick = onClickForMainBtn,
            enableState = enableStateForMainBtn,
            modifier = Modifier
                .weight(0.6f)
        )
    }
}