package com.example.sauexpert.widgets.compose.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OutlinedMainButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: Int? = null,
    onClick: () -> Unit,
    enableState: Boolean,
    sizeText: TextUnit = 16.sp,
    buttonHeight: Dp = 50.dp,
    backgroundColor: Color = MaterialTheme.colors.onPrimary,
    textColor: Color = Color.Gray,
    borderColor: Color = Color.Gray
) {
    val selected = remember { mutableStateOf(false) }
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(height = buttonHeight)
//            .absolutePadding(
//                left = 12.dp,
//                right = 12.dp
//            ),
        ,
        onClick = onClick,
        enabled = enableState,
//        onClick = {
//            selected.value = !selected.value
//                  },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = textColor
        ),
        border = BorderStroke(
            width = 1.dp,
            color =
            // if (selected.value) Color.Red else
            borderColor
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
                )
            }
            if (icon != null && text != null) {
                Spacer(modifier = Modifier.width(16.dp))
            }
            text?.let {
                Text(
                    text = text,
                    fontSize = sizeText,
                    style = MaterialTheme.typography.button
                )

            }
        }
    }
}