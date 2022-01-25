package com.example.sauexpert.widgets.compose.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.ui.theme.SauExpertTheme

@Composable
fun MainButtonWithIcon(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    onClick: () -> Unit,
    enableState: Boolean,
    icon: Painter,
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

            Icon(
                painter = icon,
                contentDescription = null,
                tint = textColor,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = text,
                fontWeight = FontWeight.W600,
                letterSpacing = 0.sp,
                color = textColor
            )
        }
    }
}