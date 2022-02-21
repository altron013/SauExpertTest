package com.example.sauexpert.general_report

import android.graphics.Paint
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.dpToPxValue
import com.example.sauexpert.bracelet_indicator.identifyHeightForYPointForString
import com.example.sauexpert.model.CardListItemData
import com.example.sauexpert.model.ListStringOfYForTableData
import com.example.sauexpert.model.WellBeingData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Green15B83D
import com.example.sauexpert.ui.theme.Orange4294
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle

@Composable
fun WellBeingReportScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Gray30.copy(alpha = 0.19f)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            ActionToolBarWithSubtitle(
                titleText = stringResource(R.string.well_being),
                subtitleText = "Декабрь 2021",
                iconBackClick = Icons.Default.ArrowBack,
                onBackClick = {},
                onRightClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            WellBeingReportWithBarChart()

            Spacer(modifier = Modifier.height(16.dp))

            WellBeingStatisticsReportSection()

        }
    }
}


@Composable
fun WellBeingReportWithBarChart(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListStringOfYForTableData(stringResource(id = R.string.full_well)),
        ListStringOfYForTableData(stringResource(id = R.string.good)),
        ListStringOfYForTableData(stringResource(id = R.string.normal)),
        ListStringOfYForTableData(stringResource(id = R.string.bad)),
        ListStringOfYForTableData(stringResource(id = R.string.very_bad)),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ).padding(16.dp)
    ) {
        Text(
            text = "${stringResource(id = R.string.well_being)} ${Emoji.grinningFace}",
            style = MaterialTheme.typography.caption
        )


        Spacer(modifier = Modifier.height(12.dp))

        BarChartForWellBeing(
            WellBeingData = listOf(
                WellBeingData(
                    positionOnX = (screenWidth * 0),
                    wellBeing = stringResource(id = R.string.good),
                    positionOnY = identifyHeightForYPointForString(
                        dataList = listNumberData,
                        text = stringResource(id = R.string.good)
                    ),
                    dateName = "16"
                ),
                WellBeingData(
                    positionOnX = (screenWidth * 1),
                    wellBeing = stringResource(id = R.string.bad),
                    positionOnY = identifyHeightForYPointForString(
                        dataList = listNumberData,
                        text = stringResource(id = R.string.bad)
                    ),
                    dateName = "17",
                    colorFocus = Orange4294
                ),
                WellBeingData(
                    positionOnX = (screenWidth * 2),
                    wellBeing = stringResource(id = R.string.normal),
                    positionOnY = identifyHeightForYPointForString(
                        dataList = listNumberData,
                        text = stringResource(id = R.string.normal)
                    ),
                    dateName = "18",
                    colorFocus = Gray30
                ),
                WellBeingData(
                    positionOnX = (screenWidth * 3),
                    wellBeing = stringResource(id = R.string.good),
                    positionOnY = identifyHeightForYPointForString(
                        dataList = listNumberData,
                        text = stringResource(id = R.string.good)
                    ),
                    dateName = "19"
                ),
                WellBeingData(
                    positionOnX = (screenWidth * 4),
                    wellBeing = stringResource(id = R.string.good),
                    positionOnY = identifyHeightForYPointForString(
                        dataList = listNumberData,
                        text = stringResource(id = R.string.good)
                    ),
                    dateName = "20"
                ),
                WellBeingData(
                    positionOnX = (screenWidth * 5),
                    wellBeing = stringResource(id = R.string.good),
                    positionOnY = identifyHeightForYPointForString(
                        dataList = listNumberData,
                        text = stringResource(id = R.string.good)
                    ),
                    dateName = "21"
                )
            ),
            ListNumberData = listNumberData
        )
    }
}


@Composable
fun BarChartForWellBeing(
    WellBeingData: List<WellBeingData>,
    ListNumberData: List<ListStringOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    val listSize = WellBeingData.size - 1
    val heightForGraph = (ListNumberData.size * 35).dp


    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightForGraph)
    ) {
        var height = 0
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        val paint2 = Paint().apply {
            textAlign = Paint.Align.RIGHT
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {
            drawContext.canvas.nativeCanvas.drawText(
                i.text,
                WellBeingData[listSize].positionOnX + 100.dp.toPx(),
                (height + 4).dp.toPx(),
                paint2
            )

            height += 35
        }

        start = true
        for (p in WellBeingData) {
            drawRect(
                color = p.colorFocus,
                topLeft = Offset(
                    x = p.positionOnX,
                    y = (height - 35).dp.toPx() - ((height - 35).dp.toPx() - p.positionOnY) * heightPre
                ),
                size = Size(
                    width = 8.dp.toPx(),
                    height = ((height - 35).dp.toPx() - p.positionOnY) * heightPre
                )
            )

            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 3.2.dp.toPx(),
                (height - 15).dp.toPx(),
                paint
            )
        }
    }
}


@Composable
fun WellBeingStatisticsReportSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        ProgressBarForWellBeing(
            veryGoodDay = 6,
            goodDay = 8,
            fineDay = 6,
            badDay = 4,
            veryBadDay = 3
        )

//        Row(modifier = modifier.padding(horizontal = 6.dp)) {
        CardItemForWellBeingReportScreen(
            cardList = listOf(
                CardListItemData(
                    text = "${Emoji.grinningFace} ${stringResource(id = R.string.full_well)}",
                    dayNumber = "6 дня",
                    colorBackground = Color.Green.copy(alpha = 0.1f),
                    colorText = Green15B83D
                ),
                CardListItemData(
                    text = "${Emoji.smilingFace} ${stringResource(id = R.string.good)}",
                    dayNumber = "8 дней",
                    colorBackground = Color.Green.copy(alpha = 0.05f),
                    colorText = Color.Green
                ),
                CardListItemData(
                    text = "${Emoji.slightlySmilingFace} ${stringResource(id = R.string.normal)}",
                    dayNumber = "6 дня",
                    colorBackground = Color.Gray.copy(alpha = 0.1f),
                    colorText = Gray30
                ),
                CardListItemData(
                    text = "${Emoji.worriedFace} ${stringResource(id = R.string.bad)}",
                    dayNumber = "4 дня",
                    colorBackground = Orange4294.copy(alpha = 0.1f),
                    colorText = Orange4294
                ),
                CardListItemData(
                    text = "${Emoji.headBandageFace} ${stringResource(id = R.string.very_bad)}",
                    dayNumber = "3 дня",
                    colorBackground = Color.Red.copy(alpha = 0.1f)
                )
            )
        )

//        }

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = modifier
                .padding(horizontal = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.missed_measurements),
                style = MaterialTheme.typography.body1,
                color = Color.Red
            )

            Text(
                text = "4 ${stringResource(R.string.day)}",
                style = MaterialTheme.typography.body1,
                color = Color.Red
            )

        }

    }
}


@Composable
fun ProgressBarForWellBeing(
    modifier: Modifier = Modifier,
    veryGoodDay: Int = 0,
    goodDay: Int = 0,
    fineDay: Int = 0,
    badDay: Int = 0,
    veryBadDay: Int = 0,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val widthForProgressBar = (screenWidth - 64.dp) / 100.dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        LinearProgressIndicator(
            progress = 1f,
            color = Green15B83D,
            modifier = Modifier
                .size(
                    height = 6.dp,
                    width = (veryGoodDay * 9).dp
                )
        )

        Spacer(modifier = Modifier.width(1.dp))

        LinearProgressIndicator(
            progress = 1f,
            color = Color.Green,
            modifier = Modifier
                .size(
                    height = 6.dp,
                    width = (goodDay * 9).dp
                )
        )

        Spacer(modifier = Modifier.width(1.dp))

        LinearProgressIndicator(
            progress = 1f,
            color = Gray30,
            modifier = Modifier
                .size(
                    height = 6.dp,
                    width = (fineDay * 9).dp
                )
        )

        Spacer(modifier = Modifier.width(1.dp))

        LinearProgressIndicator(
            progress = 1f,
            color = Orange4294,
            modifier = Modifier
                .size(
                    height = 6.dp,
                    width = (badDay * 9).dp
                )
        )

        Spacer(modifier = Modifier.width(1.dp))

        LinearProgressIndicator(
            progress = 1f,
            color = Color.Red,
            modifier = Modifier
                .size(
                    height = 6.dp,
                    width = (veryBadDay * 9).dp
                )
        )
    }
}

@Composable
fun CardItemForWellBeingReportScreen(
    cardList: List<CardListItemData>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier.fillMaxWidth()) {
        items(cardList.size) {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = modifier
                    .padding(horizontal = 6.dp)
            ) {
                Column(
                    modifier = modifier
                        .background(color = cardList[it].colorBackground)
                        .padding(12.dp)
                ) {
                    cardList[it].dayNumber?.let { it1 ->
                        Text(
                            text = it1,
                            style = MaterialTheme.typography.h5,
                            fontSize = 13.sp,
                            color = cardList[it].colorText
                        )
                    }

                    Spacer(modifier = Modifier.height(1.dp))

                    Text(
                        text = cardList[it].text,
                        style = MaterialTheme.typography.h5,
                        fontSize = 13.sp,
                    )
                }
            }
        }
    }
}


object Emoji {

    val grinningFace = getEmojiByUnicode(0x1F600)
    val smilingFace = getEmojiByUnicode(0x1F60A)
    val slightlySmilingFace = getEmojiByUnicode(0x1F642)
    val worriedFace = getEmojiByUnicode(0x1F61F)
    val headBandageFace = getEmojiByUnicode(0x1F915)

    fun getEmojiByUnicode(unicode: Int): String {
        return String(Character.toChars(unicode))
    }
}