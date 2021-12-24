package com.example.sauexpert.widgets.compose.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OutlinedMainButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: Int? =null,
    onClick: () -> Unit,
    enableState: Boolean,
    sizeText: Int = 16,
    height: Dp = 50.dp
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(height = height)
//            .absolutePadding(
//                left = 12.dp,
//                right = 12.dp
//            ),
                ,
        enabled = enableState,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            contentColor = Color.Red
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.Red
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
            Text(
                text = text,
                fontSize = sizeText.sp,
                style = MaterialTheme.typography.button
            )
        }
    }
}