package com.example.sauexpert.widgets.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.ui.theme.SauExpertTypography

@Composable
fun MainButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String? = null,
    icon: Int? = null,
    onClick: () -> Unit,
    enableState: Boolean,
    sizeText: TextUnit = 16.sp,
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
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                icon?.let {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = textColor,
                    )
                }

                if (icon != null && text != null) {
                    Spacer(modifier = Modifier.width(10.dp))
                }

                text?.let {
                    Text(
                        text = text,
                        fontWeight = FontWeight.W600,
                        style = SauExpertTypography.body1,
                        letterSpacing = 0.sp,
                        fontSize = sizeText,
                        color = textColor
                    )

                }
            }
            Text(
                text = text?:"",
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
