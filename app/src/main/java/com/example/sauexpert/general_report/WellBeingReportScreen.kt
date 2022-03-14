package com.example.sauexpert.general_report

import android.graphics.Paint
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.dpToPxValue
import com.example.sauexpert.bracelet_indicator.identifyHeightForYPointForString
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.CardListItemData
import com.example.sauexpert.model.ListStringOfYForTableData
import com.example.sauexpert.model.WellBeingData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Green15B83D
import com.example.sauexpert.ui.theme.Orange4294
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle
import com.example.sauexpert.model.objects.Emoji


@Composable
fun WellBeingReportScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions
    val navigator = LocalNavigator.currentOrThrow

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
                sizeText = dimensions.fontSizeSubtitle_2,
                sizeSubtitleText = dimensions.fontSizeBody_2,
                sizeIcon = dimensions.iconSize_2,
                onBackClick = {navigator.pop()},
                onRightClick = {}
            )

            Spacer(modifier = Modifier.height(dimensions.grid_2))

            WellBeingReportWithBarChart(dimensions = dimensions)

            Spacer(modifier = Modifier.height(dimensions.grid_2))

            WellBeingStatisticsReportSection(dimensions = dimensions)

        }
    }
}


@Composable
fun WellBeingReportWithBarChart(
    dimensions: Dimensions,
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
            style = MaterialTheme.typography.caption,
            fontSize = dimensions.fontSizeCaption
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
            ListNumberData = listNumberData,
            dimensions = dimensions
        )
    }
}


@Composable
fun BarChartForWellBeing(
    WellBeingData: List<WellBeingData>,
    ListNumberData: List<ListStringOfYForTableData>,
    dimensions: Dimensions
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
            textSize = dimensions.fontSizeCustom_3.toPx()
            color = Gray30.toArgb()
        }

        val paint2 = Paint().apply {
            textAlign = Paint.Align.RIGHT
            textSize = dimensions.fontSizeCustom_3.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {
            drawContext.canvas.nativeCanvas.drawText(
                i.text,
                WellBeingData[listSize].positionOnX + dimensions.grid_12_5.toPx(),
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
    dimensions: Dimensions,
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
            ),
            sizeText = dimensions.fontSizeCustom_3
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
                fontSize = dimensions.fontSizeBody_1,
                color = Color.Red
            )

            Text(
                text = "4 ${stringResource(R.string.day)}",
                style = MaterialTheme.typography.body1,
                fontSize = dimensions.fontSizeBody_1,
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

    val widthForProgressBar = (screenWidth - 70.dp) / 28.dp

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
                    width = (veryGoodDay * widthForProgressBar).dp
                )
        )

        Spacer(modifier = Modifier.width(1.dp))

        LinearProgressIndicator(
            progress = 1f,
            color = Color.Green,
            modifier = Modifier
                .size(
                    height = 6.dp,
                    width = (goodDay * widthForProgressBar).dp
                )
        )

        Spacer(modifier = Modifier.width(1.dp))

        LinearProgressIndicator(
            progress = 1f,
            color = Gray30,
            modifier = Modifier
                .size(
                    height = 6.dp,
                    width = (fineDay * widthForProgressBar).dp
                )
        )

        Spacer(modifier = Modifier.width(1.dp))

        LinearProgressIndicator(
            progress = 1f,
            color = Orange4294,
            modifier = Modifier
                .size(
                    height = 6.dp,
                    width = (badDay * widthForProgressBar).dp
                )
        )

        Spacer(modifier = Modifier.width(1.dp))

        LinearProgressIndicator(
            progress = 1f,
            color = Color.Red,
            modifier = Modifier
                .size(
                    height = 6.dp,
                    width = (veryBadDay * widthForProgressBar).dp
                )
        )
    }
}

@Composable
fun CardItemForWellBeingReportScreen(
    cardList: List<CardListItemData>,
    sizeText: TextUnit = 13.sp,
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
                            fontSize = sizeText,
                            color = cardList[it].colorText
                        )
                    }

                    Spacer(modifier = Modifier.height(1.dp))

                    Text(
                        text = cardList[it].text,
                        style = MaterialTheme.typography.h5,
                        fontSize = sizeText,
                    )
                }
            }
        }
    }
}
