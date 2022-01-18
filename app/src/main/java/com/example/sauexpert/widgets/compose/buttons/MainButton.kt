package com.example.sauexpert.widgets.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.wrapContentWidth
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
    buttonHeight: Dp = 50.dp
) {
    SauExpertTheme() {
        Button(
            modifier = modifier
               // .fillMaxWidth()
                .height(height = buttonHeight)
//                .absolutePadding(
//                    left = 10.dp,
//                    right = 10.dp
//                )
            ,
            enabled = enableState,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.W500,
                letterSpacing = 0.sp,
                color= Color.White
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
    enableState: Boolean,
) {
    MainButton(
        modifier=Modifier.wrapContentSize(),
        text = text,
        onClick = onClick,
        enableState = enableState
    )
}
