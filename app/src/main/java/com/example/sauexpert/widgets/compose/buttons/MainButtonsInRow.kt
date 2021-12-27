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
                .weight(0.4f)
                .padding(start = 10.dp)

        )

        Spacer(modifier = Modifier.width(12.dp))

        MainButton(
            text = textForMainBtn,
            onClick = onClickForMainBtn,
            enableState = enableStateForMainBtn,
            modifier = Modifier
                .weight(0.6f)
                .padding(end = 10.dp)

        )
    }
}