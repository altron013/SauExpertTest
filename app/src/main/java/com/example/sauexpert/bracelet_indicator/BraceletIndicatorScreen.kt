package com.example.sauexpert.bracelet_indicator

import android.graphics.Paint
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.SleepData
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton


@Composable
fun BraceletIndicatorScreen() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                color = Gray30.copy(alpha = 0.19f),
                shape = RoundedCornerShape(7.dp)
            )
            .padding(16.dp)
    ) {
        TopBarForBracelet()
        Spacer(modifier = Modifier.height(28.dp))
        TabView(
            TextOfTab = listOf(
                TextOfTabData(
                    text = stringResource(id = R.string.sleep)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.sp02)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.hrv)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.pressure)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.pulse)
                )
            )


        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(7.dp)
                        )
                        .padding(16.dp).padding(bottom = 60.dp)
                ) {
                    SleepStat()
                    Spacer(modifier = Modifier.height(12.dp))
                    BarChartForSleep(
                        SleepData = listOf(
                            SleepData(positionOnX = 10f, hourOfSleep = 140f, dateName = "Пн"),
                            SleepData(positionOnX = 100f, hourOfSleep = 200f, dateName = "Вт"),
                            SleepData(
                                positionOnX = 190f,
                                hourOfSleep = 190f,
                                startTime = 40f,
                                dateName = "Ср"
                            ),
                            SleepData(
                                positionOnX = 280f,
                                hourOfSleep = 180f,
                                startTime = 60f,
                                dateName = "Чт"
                            ),
                            SleepData(positionOnX = 370f, hourOfSleep = 220f, dateName = "Пт"),
                            SleepData(
                                positionOnX = 460f,
                                hourOfSleep = 240f,
                                startTime = 80f,
                                dateName = "Сб"
                            ),
                            SleepData(positionOnX = 550f, hourOfSleep = 370f, dateName = "Вс")
                        )
                    )
                    Spacer(modifier = Modifier.height(275.dp))
                    SleepStat2()
                    Spacer(modifier = Modifier.height(16.dp))
                    ProgressBar(
                        deepSleepValue = 120,
                        deepSleepPercent = 40,
                        lightSleepValue = 115,
                        lightSleepPercent = 30
                    )

                }

                MainButton(
                    text = stringResource(id = R.string.range_customize),
                    onClick = { /*TODO*/ },
                    enableState = true,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
            1 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth().verticalScroll(rememberScrollState())
                        .padding(bottom = 60.dp)
                ) {

                    analysisSOASStat()
                    Spacer(modifier = Modifier.height(24.dp))
                    analysisSOASStat2()

                }
                MainButton(
                    text = stringResource(id = R.string.range_customize),
                    onClick = { /*TODO*/ },
                    enableState = true,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )

            }

        }
    }
}


@Composable
fun TopBarForBracelet(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = modifier.align(Alignment.CenterStart)
                .clickable {
                }
        )

        Text(
            text = stringResource(id = R.string.bracelet),
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier.align(Alignment.Center)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_calendar_icon),
            contentDescription = null,
            modifier = modifier.align(Alignment.CenterEnd)
        )


    }
}

@Composable
fun TabView(
    modifier: Modifier = Modifier,
    TextOfTab: List<TextOfTabData>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inactiveColor = Gray30
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Transparent,
        modifier = modifier.fillMaxWidth()

            .border(
                BorderStroke(1.5.dp, inactiveColor),
                RoundedCornerShape(8.dp)
            )
    ) {
        TextOfTab.forEachIndexed { index, item ->
            Tab(selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)

                }
            ) {
                Text(
                    text = item.text,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp)

                )
            }

        }

    }
}


@Composable
fun SleepStat(
    modifier: Modifier = Modifier
) {
//    val myId = "inlineContent"
//    val text = buildAnnotatedString {
//        append(stringResource(id = R.string.sleep_duration))
//        appendInlineContent(myId, "[icon]")
//    }
//
//    val inlineContent = mapOf(
//        Pair(
//            myId,
//            InlineTextContent(
//                Placeholder(
//                    width = 9.sp,
//                    height = 9.sp,
//                    placeholderVerticalAlign = PlaceholderVerticalAlign.AboveBaseline
//                )
//            ) {
//                Icon(Icons.Filled.Circle, "", tint = Color.Red.copy(alpha = 0.25f))
//            }
//        )
//    )


    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.sleep),
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Circle,
                contentDescription = "",
                tint = Color.Red.copy(alpha = 0.25f),
                modifier = modifier.size(9.dp)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = stringResource(id = R.string.sleep_duration),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = Gray30,
//                inlineContent = inlineContent
            )

        }

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 34.sp
                    )
                ) {
                    append("6")
                }
                append("ч ")
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 34.sp
                    )
                ) {
                    append("36")
                }
                append("мин")
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


@Composable
fun BarChartForSleep(
    SleepData: List<SleepData>,
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 34f
//            color = Color(0xFF0018A8).toArgb()
        }


        drawLine(
            start = Offset(10f, 238.dp.toPx()),
            end = Offset(10f, 0f),
            color = Gray30,
            strokeWidth = 2f
        )


        drawLine(
            start = Offset(10f, 0f),
            end = Offset(780f, 0f),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "22:00",
            850f,
            10.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 34.dp.toPx()),
            end = Offset(780f, 34.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "00:00",
            850f,
            44.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 68.dp.toPx()),
            end = Offset(780f, 68.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "02:00",
            850f,
            78.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 102.dp.toPx()),
            end = Offset(780f, 102.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "04:00",
            850f,
            112.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 136.dp.toPx()),
            end = Offset(780f, 136.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "06:00",
            850f,
            146.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 170.dp.toPx()),
            end = Offset(780f, 170.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "08:00",
            850f,
            180.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 204.dp.toPx()),
            end = Offset(780f, 204.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "10:00",
            850f,
            214.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 238.dp.toPx()),
            end = Offset(780f, 238.dp.toPx()),
            color = Color.Black,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "12:00",
            850f,
            248.dp.toPx(),
            paint
        )

        start = true
        for (p in SleepData) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(p.positionOnX + 20, p.startTime * heightPre),
                size = Size(60f, p.hourOfSleep * heightPre)

            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 45,
                254.dp.toPx(),
                paint
            )
        }
    }
}

@Composable
fun SleepStat2(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(width = 300.dp, height = 50.dp)
            .background(
                color = Gray30.copy(alpha = 0.19f),
                shape = RoundedCornerShape(7.dp)
            )
    ) {
        Text(
            text = stringResource(R.string.woke_up_in_middle),
            style = MaterialTheme.typography.button,
            modifier = modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 16.dp)
        )

        Text(
            text = "3 раза",
            style = MaterialTheme.typography.subtitle2,
            fontSize = 15.sp,
            modifier = modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    deepSleepValue: Int = 0,
    deepSleepPercent: Int = 0,
    lightSleepValue: Int = 0,
    lightSleepPercent: Int = 0,

    ) {

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.Blue,
                modifier = modifier.size(height = 6.dp, width = deepSleepValue.dp)
            )

            Text(
                text = stringResource(R.string.deep_sleep),
                style = MaterialTheme.typography.button,
                color = Color.Blue
            )

            Text(
                text = "$deepSleepPercent%",
                style = MaterialTheme.typography.button,
            )

        }

        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.Cyan,
                modifier = modifier.size(height = 6.dp, width = lightSleepValue.dp)
            )

            Text(
                text = stringResource(R.string.light_sleep),
                style = MaterialTheme.typography.button,
                color = Color.Cyan
            )

            Text(
                text = "$lightSleepPercent%",
                style = MaterialTheme.typography.button,
            )

        }

        Column() {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.Gray,
                modifier = modifier.size(height = 6.dp, width = 115.dp)
            )

            Text(
                text = stringResource(R.string.light_sleep),
                style = MaterialTheme.typography.button,
                color = Color.Gray
            )

            Text(
                text = "30%",
                style = MaterialTheme.typography.button,
            )

        }

    }
}


@Composable
fun SP02withLineGraph(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(7.dp)
            )
    ) {
        SP02Stat()
        Spacer(modifier = Modifier.height(12.dp))

    }
}

@Composable
fun SP02Stat(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.sp02),
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Circle,
                contentDescription = "",
                tint = Color.Green.copy(alpha = 0.25f),
                modifier = modifier.size(9.dp)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = stringResource(id = R.string.oxygen_level),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = Gray30
            )
        }

        Text(
            text = "18-20 ноября 2021",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Gray30
        )
    }
}

data class Point(val X: Float = 0f, val Y: Float = 0f)

@Composable
fun lineChartForSp02() {
    val point = listOf(
        Point(10f, 10f), Point(50f, 100f), Point(100f, 30f),
        Point(150f, 200f), Point(200f, 120f), Point(250f, 10f),
        Point(300f, 280f), Point(350f, 100f), Point(400f, 10f),
        Point(450f, 100f), Point(500f, 200f)
    )
    val path = Path()
    for ((index, item) in point.withIndex()) {
        if (index == 0) {
            path.moveTo(item.X*scale, item.Y)
        } else {
            path.lineTo(item.X*scale, item.Y)
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.White)
            // Monitor gesture scaling
            .graphicsLayer(
            )
    ) {
        // draw  X Axis  Y Axis
        drawLine(
            start = Offset(10f, 300f),
            end = Offset(10f, 0f),
            color = Color.Black,
            strokeWidth = 2f
        )
        drawLine(
            start = Offset(10f, 300f),
            end = Offset(510f, 300f),
            color = Color.Black,
            strokeWidth = 2f
        )
        // draw path
        drawPath(
            path = path,
            color = Color.Blue,
            style = Stroke(width = 2f)
        )
    }

}


@Composable
fun analysisSOASStat(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.soas_analysis),
                style = MaterialTheme.typography.subtitle2,
            )

            Text(
                text = stringResource(R.string.more_detail),
                style = MaterialTheme.typography.body2,
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(7.dp)
                )
        ) {
            analysisStatField(
                title = stringResource(R.string.severe_degree),
                value = "18",
                isIconVisible = true
            )
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            analysisStatField(title = stringResource(R.string.sleep_apnea_case), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            analysisStatField(title = stringResource(R.string.hypopnea_case), value = "18")

        }

    }
}

@Composable
fun analysisSOASStat2(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.soas_analysis),
            style = MaterialTheme.typography.subtitle2,
        )

        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(7.dp)
                )
        ) {
            analysisStatField(title = stringResource(R.string.sp02_average), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            analysisStatField(title = stringResource(R.string.breathing_rate), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            analysisStatField(title = stringResource(R.string.hypoxia_case), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            analysisStatField(title = stringResource(R.string.cardiac_pressure), value = "18")

        }

    }
}


@Composable
fun analysisStatField(
    title: String,
    value: String,
    isIconVisible: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(11.dp)
    ) {

        if (isIconVisible) {
            Icon(
                imageVector = Icons.Filled.Circle,
                contentDescription = "",
                tint = Color.Red,
                modifier = modifier.size(9.dp)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.body1,
            )

            Spacer(modifier = Modifier.width(75.dp))
        } else {
            Text(
                text = title,
                style = MaterialTheme.typography.body1,
            )
        }


        Text(
            text = value,
            style = MaterialTheme.typography.body1,
        )

    }
}


