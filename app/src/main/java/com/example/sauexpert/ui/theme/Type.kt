package com.example.sauexpert.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R

// Set of Material typography styles to start with


private val SfpDisplay = FontFamily(
    Font(R.font.sfpro_display_regular),
    Font(R.font.sfp_pro_display_bold, FontWeight.Bold)
)

private val SfpPro = FontFamily(
    Font(R.font.sfp_pro_text_regular),
    Font(R.font.sfp_pro_text_bold, FontWeight.Bold)
)
private val defaultTypography = Typography()
val appTypography = Typography(
    h1 = defaultTypography.h1.copy(fontFamily = SfpPro),
    h2 = defaultTypography.h2.copy(fontFamily = SfpPro),
    h3 = defaultTypography.h3.copy(fontFamily = SfpPro),
    h4 = defaultTypography.h4.copy(fontFamily = SfpPro),
    h5 = defaultTypography.h5.copy(fontFamily = SfpPro),
    h6 = defaultTypography.h6.copy(fontFamily = SfpPro),
    subtitle1 = defaultTypography.subtitle1.copy(fontFamily = SfpPro),
    subtitle2 = defaultTypography.subtitle2.copy(fontFamily = SfpPro),
    body1 = defaultTypography.body1.copy(fontFamily = SfpPro),
    body2 = defaultTypography.body2.copy(fontFamily = SfpPro),
    button = defaultTypography.button.copy(fontFamily = SfpPro),
    caption = defaultTypography.caption.copy(fontFamily = SfpPro),
    overline = defaultTypography.overline.copy(fontFamily = SfpPro)
)
val SauExpertTypography = Typography(
    h3 = TextStyle(
        fontFamily = SfpDisplay,
        fontWeight = FontWeight.W700,
        fontSize = 28.sp
    ),
    h4 = TextStyle(
        fontFamily = SfpDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),
    h5 = TextStyle(
        fontFamily = SfpPro,
        fontSize = 12.sp
    ),
    h6 = TextStyle(
        fontFamily = SfpPro,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = SfpPro,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = SfpPro,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    body1 = TextStyle(
        fontFamily = SfpPro,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
    ),
    body2 = TextStyle(
        fontFamily = SfpPro,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontFamily = SfpPro,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = SfpDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    overline = TextStyle(
        fontFamily = SfpDisplay,
        fontSize = 22.sp
    ),
)

val SauExpertTypography2=Typography(
    h4 = TextStyle(
        fontFamily = SfpDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),
    h5 = TextStyle(
        fontFamily = SfpPro,
        fontSize = 12.sp
    ),
    h6 = TextStyle(
        fontFamily = SfpPro,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = SfpPro,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = SfpPro,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    body1 = TextStyle(
        fontFamily = SfpPro,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
    ),
    body2 = TextStyle(
        fontFamily = SfpPro,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontFamily = SfpPro,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption= TextStyle(
        fontFamily = SfpDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    overline = TextStyle(
        fontFamily = SfpDisplay,
        fontSize = 22.sp
    )
)


