package com.example.sauexpert.widgets.compose.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
    buttonHeight: Dp = 50.dp
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        OutlinedMainButton(
            text = textForOutlineBtn,
            onClick = onClickForOutlineBtn,
            enableState = enableStateForOutlineBtn,
            modifier = Modifier
                .size(width = 139.dp, height = buttonHeight)
                .padding(start = 10.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        MainButton(
            text = textForMainBtn,
            onClick = onClickForMainBtn,
            enableState = enableStateForMainBtn,
            modifier = Modifier
                .size(width = 192.dp, height = buttonHeight)
                
        )
    }
}