package com.example.sauexpert.widgets.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.ui.theme.SauExpertTheme

@Composable
fun MainButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    onClick: () -> Unit,
    enableState: Boolean,
    buttonHeight: Dp = 50.dp,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    textColor: Color = Color.White
) {
    SauExpertTheme() {
        Button(
            modifier = modifier
                .height(height = buttonHeight),
            enabled = enableState,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = textColor
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.W500,
                letterSpacing = 0.sp,
                color = textColor
            )
        }
    }
}


@Composable
fun MainButtonM(
    text: String,
    onClick: () -> Unit,
    enableState: Boolean
) {
    MainButton(
        text = text,
        onClick = onClick,
        enableState = enableState,
        buttonHeight = 56.dp
    )
}

@Composable
fun MainButtonS(
    text: String,
    onClick: () -> Unit,
    enableState: Boolean
) {
    MainButton(
        modifier = Modifier.wrapContentSize(),
        text = text,
        onClick = onClick,
        enableState = enableState
    )
}
