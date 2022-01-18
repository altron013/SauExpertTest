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
import androidx.compose.material.AlertDialog
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
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.sauexpert.R
import com.example.sauexpert.model.HRVData
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton


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
            ),
            ListNumberData = listOf(
                ListNumberOfYForTableData("240"),
                ListNumberOfYForTableData("200"),
                ListNumberOfYForTableData("160"),
                ListNumberOfYForTableData("120"),
                ListNumberOfYForTableData("80"),
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
        if (x > hrvData.positionOnX + 20 && x < hrvData.positionOnX + 80) {
            return index
        }
    }
    return -1
}

//fun Modifier.customDialogPosition(x: Int, y: Int) = layout { measurable, constraints ->
//    val placeable = measurable.measure(constraints)
//    layout(constraints.maxWidth, constraints.maxHeight) {
//        placeable.place(x - 400, y + 400, 10f)
//    }
//}


@Composable
fun BarChartForHRV(
    HRVData: List<HRVData>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    val context = LocalContext.current
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )


    val openDialog = remember { mutableStateOf(false) }
    val itemID = remember { mutableStateOf(1) }
//    val xPosition = remember { mutableStateOf(1) }
//    val yPosition = remember { mutableStateOf(1) }


    if (openDialog.value && itemID.value != -1) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(
                    text = "${itemID.value} | ${HRVData[itemID.value].hourOfHRV} | ${HRVData[itemID.value].dateName}",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(16.dp)
                )
            },
            buttons = {
                MainButton(
                    text = stringResource(id = R.string.understand),
                    onClick = { openDialog.value = false },
                    enableState = true,
                    modifier = Modifier.padding(16.dp).size(70.dp)
                )
            },
//            modifier = Modifier.customDialogPosition(xPosition.value, yPosition.value),
            properties = DialogProperties()
        )
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(155.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        itemID.value = identifyClickItem(HRVData, it.x)
                        Toast.makeText(context, "onTap: ${itemID.value}", Toast.LENGTH_SHORT).show()
                        openDialog.value = true
//                        xPosition.value = it.x.toInt()
//                        yPosition.value = it.y.toInt()
                    }
                )
            }
    ) {
        var height = 0
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

        for (i in ListNumberData) {
            drawLine(
                start = Offset(10f, height.dp.toPx()),
                end = Offset(780f, height.dp.toPx()),
                color = Gray30,
                strokeWidth = 2f
            )

            drawContext.canvas.nativeCanvas.drawText(
                i.number,
                320.dp.toPx(),
                10.dp.toPx() + height.dp.toPx(),
                paint
            )

            height += 35
        }

        start = true
        for (p in HRVData) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(
                    p.positionOnX + 20,
                    140.dp.toPx() - (140.dp.toPx() - p.hourOfHRV) * heightPre
                ),
                size = Size(
                    75f,
                    (140.dp.toPx() - p.hourOfHRV) * heightPre
                ),

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
            imageVector = Icons.Filled.FlashOn
        )
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(
            title = stringResource(R.string.lowest_value),
            value = "18"
        )
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(
            title = stringResource(R.string.average_value),
            value = "18"
        )
        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )
        AnalysisStatField(
            title = stringResource(R.string.last_value),
            value = "18"
        )
    }
}