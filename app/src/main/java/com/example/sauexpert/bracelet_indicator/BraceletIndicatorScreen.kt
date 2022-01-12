package com.example.sauexpert.bracelet_indicator

import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
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
                        .fillMaxSize().background(
                            color = Color.White,
                            shape = RoundedCornerShape(7.dp)
                        )
                        .padding(16.dp)
                ) {
                    SleepStat()
                    Spacer(modifier = Modifier.height(10.dp))
                    BarChart()
                    Spacer(modifier = Modifier.height(250.dp))
                    SleepStat2()
                    Spacer(modifier = Modifier.height(12.dp))
                    ProgressBar(
                        deepSleepValue = 120,
                        deepSleepPercent = 40,
                        lightSleepValue = 115,
                        lightSleepPercent = 30
                    )

                }

//                MainButton(
//                    text = stringResource(id = R.string.range_customize),
//                    onClick = { /*TODO*/ },
//                    enableState = true,
//                    modifier = Modifier
//                        .align(Alignment.BottomCenter)
//                )
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

data class Point(val X: Float = 0f, val Y: Float = 0f, val startTime: Float = 0f)


@Composable
fun BarChart() {
    val point = listOf(
        Point(10f, 140f),
        Point(100f, 200f),
        Point(190f, 190f, 40f),
        Point(280f, 180f, 60f),
        Point(370f, 220f),
        Point(460f, 240f, 80f),
        Point(550f, 370f)
    )
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )
    Canvas(
        modifier = Modifier
            .fillMaxWidth()


    ) {
        drawLine(
            start = Offset(10f, 238.dp.toPx()),
            end = Offset(10f, 0f),
            color = Gray30,
            strokeWidth = 2f
        )

        drawLine(
            start = Offset(10f, 0f),
            end = Offset(850f, 0f),
            color = Gray30,
            strokeWidth = 2f
        )

        drawLine(
            start = Offset(10f, 34.dp.toPx()),
            end = Offset(850f, 34.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawLine(
            start = Offset(10f, 68.dp.toPx()),
            end = Offset(850f, 68.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawLine(
            start = Offset(10f, 102.dp.toPx()),
            end = Offset(850f, 102.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawLine(
            start = Offset(10f, 136.dp.toPx()),
            end = Offset(850f, 136.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawLine(
            start = Offset(10f, 170.dp.toPx()),
            end = Offset(850f, 170.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawLine(
            start = Offset(10f, 204.dp.toPx()),
            end = Offset(850f, 204.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawLine(
            start = Offset(10f, 238.dp.toPx()),
            end = Offset(850f, 238.dp.toPx()),
            color = Color.Black,
            strokeWidth = 2f
        )

        start = true
        for (p in point) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(p.X + 20, p.startTime * heightPre),
                size = Size(60f, p.Y * heightPre)

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


