package com.example.sauexpert.bracelet_indicator

import android.graphics.Paint
import android.widget.Toast
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.HRVData
import com.example.sauexpert.ui.theme.Gray30


@Composable
fun HRVScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth().verticalScroll(rememberScrollState())
    ) {
        HRVwithBarChart()
        Spacer(modifier = Modifier.height(24.dp))
        AnalysisHRVStat()
    }
}


@Composable
fun HRVwithBarChart(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(7.dp)
            ).padding(16.dp)
    ) {
        HRVStat()
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForHRV(
            HRVData = listOf(
                HRVData(positionOnX = 10f, hourOfHRV = 200f, dateName = "16.12"),
                HRVData(positionOnX = 120f, hourOfHRV = 370f, dateName = "17.12"),
                HRVData(positionOnX = 230f, hourOfHRV = 190f, dateName = "18.12"),
                HRVData(positionOnX = 340f, hourOfHRV = 180f, dateName = "19.12"),
                HRVData(positionOnX = 450f, hourOfHRV = 220f, dateName = "20.12"),
                HRVData(positionOnX = 560f, hourOfHRV = 240f, dateName = "21.12"),
                HRVData(positionOnX = 670f, hourOfHRV = 30f, dateName = "22.12")
            )
        )
    }
}

@Composable
fun HRVStat(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.pressure),
            style = MaterialTheme.typography.caption
        )

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 34.sp
                    )
                ) {
                    append("150 ")
                }

                append(stringResource(R.string.mmhg_average))
            },
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Gray30
        )

        Text(
            text = "18-20 ноября 2021",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Gray30
        )
    }
}

//@Composable
//fun Modifier.startGesture(
//    onStart: (offsetX: Float) -> Unit
//): Modifier {
//    val interactionSource = remember { MutableInteractionSource() }
//    return this.pointerInput(interactionSource) {
//        forEachGesture {
//            coroutineScope {
//                awaitPointerEventScope {
//                    val touch = awaitFirstDown().also { it.consumeDownChange() }
//                    onStart(touch.position.x)
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//fun Modifier.tapOrPress(
//    onStart: (offsetX: Float) -> Unit,
//    onCancel: (offsetX: Float) -> Unit,
//    onCompleted: (offsetX: Float) -> Unit
//): Modifier {
//    val interactionSource = remember { MutableInteractionSource() }
//    return this.pointerInput(interactionSource) {
//        forEachGesture {
//            coroutineScope {
//                awaitPointerEventScope {
//                    val tap = awaitFirstDown().also { it.consumeDownChange() }
//                    onStart(tap.position.x)
//                    val up = waitForUpOrCancellation()
//                    if (up == null) {
//                        onCancel(tap.position.x)
//                    } else {
//                        up.consumeDownChange()
//                        onCompleted(tap.position.x)
//                    }
//                }
//            }
//        }
//    }
//}

private fun identifyClickItem(hrvData: List<HRVData>, x: Float): Int {
    for ((index, hrvData) in hrvData.withIndex()) {
        if (x > hrvData.positionOnX + 20 && x < hrvData.positionOnX + 20 + 40) {
            return index
        }
    }
    return -1
}



@Composable
fun BarChartForHRV(
    HRVData: List<HRVData>,
) {
    val context = LocalContext.current
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(155.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        val i = identifyClickItem(HRVData, it.x)
                        Toast.makeText(context, "onTap: $i", Toast.LENGTH_SHORT).show()
                    }
                )
            }
    ) {

        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 34f
//            color = Color(0xFF0018A8).toArgb()
        }


        drawLine(
            start = Offset(10f, 140.dp.toPx()),
            end = Offset(10f, 0f),
            color = Gray30,
            strokeWidth = 2f
        )


        drawLine(
            start = Offset(10f, 0.dp.toPx()),
            end = Offset(780f, 0.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "240",
            320.dp.toPx(),
            10.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 35.dp.toPx()),
            end = Offset(780f, 35.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "200",
            320.dp.toPx(),
            45.dp.toPx(),
            paint
        )


        drawLine(
            start = Offset(10f, 70.dp.toPx()),
            end = Offset(780f, 70.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "160",
            320.dp.toPx(),
            80.dp.toPx(),
            paint
        )



        drawLine(
            start = Offset(10f, 105.dp.toPx()),
            end = Offset(780f, 105.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "120",
            320.dp.toPx(),
            115.dp.toPx(),
            paint
        )



        drawLine(
            start = Offset(10f, 140.dp.toPx()),
            end = Offset(780f, 140.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "80",
            320.dp.toPx(),
            150.dp.toPx(),
            paint
        )

        start = true
        for (p in HRVData) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(
                    p.positionOnX + 20,
                    140.dp.toPx() - (140.dp.toPx() - p.hourOfHRV) * heightPre),
                size = Size(
                    75f,
                    (140.dp.toPx() - p.hourOfHRV) * heightPre),

            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 55,
                160.dp.toPx(),
                paint
            )
        }
    }
}


@Composable
fun AnalysisHRVStat(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(7.dp)
            )
    ) {
        AnalysisStatFieldWithIconAtEnd(
            title = stringResource(R.string.highest_value),
            value = "18",
            imageVector = Icons.Filled.FlashOn)
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(
            title = stringResource(R.string.lowest_value),
            value = "18")
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(
            title = stringResource(R.string.average_value),
            value = "18")
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(
            title = stringResource(R.string.last_value),
            value = "18")
    }
}