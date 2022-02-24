package com.example.sauexpert.bracelet_indicator

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.Sp02Data
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.bottomsheet.BottomSheetContentForSoas
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun Sp02Screen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            BottomSheetContentForSoas(
                onClick = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                },
                dimensions = dimensions
            )
        },
        sheetPeekHeight = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Gray30.copy(alpha = 0.19f)
                )
                .padding(horizontal = 16.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 24.dp, bottom = 10.dp)
            ) {
                SP02withLineGraph(dimensions = dimensions)
                Spacer(modifier = Modifier.height(24.dp))

                AnalysisSp02Section(dimensions = dimensions)
                Spacer(modifier = Modifier.height(24.dp))

                AnalysisSOASSection(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                    },
                    dimensions = dimensions
                )
                Spacer(modifier = Modifier.height(16.dp))
                RangeCustomizeSection(dimensions = dimensions)
            }

        }


    }
}


@Composable
fun SP02withLineGraph(
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListNumberOfYForTableData(100),
        ListNumberOfYForTableData(98),
        ListNumberOfYForTableData(96),
        ListNumberOfYForTableData(94),
        ListNumberOfYForTableData(92),
        ListNumberOfYForTableData(90),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ).padding(16.dp)
    ) {
        TitleForGraph(
            textTitle = stringResource(id = R.string.sp02),
            TextOfTab = listOf(
                TextOfTabData(stringResource(R.string.week_short).toUpperCase(Locale.current)),
                TextOfTabData(stringResource(R.string.month_short).toUpperCase(Locale.current)),
                TextOfTabData(
                    stringResource(R.string.choose).toUpperCase(Locale.current),
                    painter = painterResource(R.drawable.ic_calendar)
                )
            ),
            weight = 0.3f,
            dimensions = dimensions
        )

        Spacer(modifier = Modifier.height(40.dp))
        LineChartForSp02(
            Sp02Data = listOf(
                Sp02Data(
                    positionOnX = (screenWidth * 0),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 90),
                    dateName = "16"
                ),
                Sp02Data(
                    positionOnX = (screenWidth * 1),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 100),
                    dateName = "17",
                    sleepApnea = true
                ),
                Sp02Data(
                    positionOnX = (screenWidth * 2),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 99),
                    dateName = "18"
                ),
                Sp02Data(
                    positionOnX = (screenWidth * 3),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 98),
                    dateName = "19",
                    sleepApnea = true
                ),
                Sp02Data(
                    positionOnX = (screenWidth * 4),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 97),
                    dateName = "20"
                ),
                Sp02Data(
                    positionOnX = (screenWidth * 5),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 94),
                    dateName = "21"
                ),
                Sp02Data(
                    positionOnX = (screenWidth * 6),
                    positionOnY = identifyHeightForYPoint(dataList = listNumberData, number = 89),
                    dateName = "22"
                ),
            ),
            dimensions = dimensions,
            ListNumberData = listNumberData

        )

        Spacer(modifier = Modifier.height(20.dp))

        TextWithIconForGraph(
            color = Color.Green.copy(alpha = 0.25f),
            text = stringResource(id = R.string.oxygen_level).toUpperCase(Locale.current),
            dimensions = dimensions
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextWithIconForGraph(
            color = Color.Red,
            text = stringResource(id = R.string.sleep_apnea).toUpperCase(Locale.current),
            dimensions = dimensions
        )
    }
}

@Composable
fun LineChartForSp02(
    Sp02Data: List<Sp02Data>,
    ListNumberData: List<ListNumberOfYForTableData>,
    dimensions: Dimensions
) {
    val scale by remember { mutableStateOf(1f) }
    val listSize = Sp02Data.size - 1
    val heightForGraph = (ListNumberData.size * 35).dp

    val path = Path()

    for ((index, item) in Sp02Data.withIndex()) {
        when (index) {
            0 -> {
                path.moveTo(0f * scale, 0f)
                path.lineTo(item.positionOnX * scale, item.positionOnY)
            }
            listSize -> {
                path.lineTo(item.positionOnX * scale, item.positionOnY)
                path.lineTo((item.positionOnX + 25f) * scale, 0f)
            }
            else -> {
                path.lineTo(item.positionOnX * scale, item.positionOnY)
            }
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightForGraph)
            .background(Color.White)
    ) {
        var height = 0
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = dimensions.fontSizeCustom_3.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {
            drawContext.canvas.nativeCanvas.drawText(
                "${i.number}",
                Sp02Data[listSize].positionOnX + 38.dp.toPx(),
                height.dp.toPx(),
                paint
            )

            height += 35
        }


        clipPath(
            path = path,
            clipOp = ClipOp.Difference
        ) {
            drawRect(
                color = Color.Green.copy(alpha = 0.1f),
                size = Size(
                    width = Sp02Data[listSize].positionOnX,
                    height = (height - 35).dp.toPx()
                )
            )
        }


        for (i in 0 until listSize) {
            drawLine(
                start = Offset(Sp02Data[i].positionOnX, Sp02Data[i].positionOnY),
                end = Offset(Sp02Data[i + 1].positionOnX, Sp02Data[i + 1].positionOnY),
                color = Color.Green,
                strokeWidth = 2.dp.toPx()
            )

            if (Sp02Data[i].sleepApnea) {
                drawCircle(
                    color = Color.White,
                    radius = 4.dp.toPx(),
                    center = Offset(Sp02Data[i].positionOnX, Sp02Data[i].positionOnY - 1f)
                )

                drawCircle(
                    color = Color.Red,
                    radius = 3.dp.toPx(),
                    center = Offset(Sp02Data[i].positionOnX, Sp02Data[i].positionOnY - 1f)
                )

            }

            drawContext.canvas.nativeCanvas.drawText(
                "${Sp02Data[i].dateName}",
                Sp02Data[i].positionOnX,
                (height - 15).dp.toPx(),
                paint
            )
        }

        drawContext.canvas.nativeCanvas.drawText(
            "${Sp02Data[listSize].dateName}",
            Sp02Data[listSize].positionOnX,
            (height - 15).dp.toPx(),
            paint
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun AnalysisSOASSection(
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    dimensions: Dimensions
) {
//    val visible: MutableState<Boolean> = remember { mutableStateOf(false) }
//
//    InfoDialogForSOAS(visible = visible)

    Column(modifier = modifier.fillMaxWidth()) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = modifier.fillMaxWidth()
//        ) {
//            Text(
//                text = stringResource(R.string.soas_analysis),
//                style = MaterialTheme.typography.subtitle2,
//            )
//
//            Text(
//                text = stringResource(R.string.more_detail),
//                style = MaterialTheme.typography.body2,
//                color = Color.Red,
//                modifier = modifier.clickable {
//                    visible.value = true
//                }
//            )
//        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.soas_analysis),
                style = MaterialTheme.typography.subtitle2,
                fontSize = dimensions.fontSizeSubtitle_2
            )

            ClickableText(
                text = AnnotatedString(stringResource(R.string.more_detail)),
                style = MaterialTheme.typography.body2.copy(
                    Color.Red,
                    fontSize = dimensions.fontSizeBody_2
                ),
                onClick = onClick
            )
        }

        Spacer(modifier = Modifier.height(12.dp))


        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {

            AnalysisFieldWithIconAtBeg(
                title = stringResource(R.string.severe_degree),
                value = "18",
                imageVector = Icons.Filled.Circle,
                dimensions = dimensions
            )
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(
                title = stringResource(R.string.sleep_apnea_case),
                value = "18",
                dimensions = dimensions
            )
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(
                title = stringResource(R.string.hypopnea_case),
                value = "18",
                dimensions = dimensions
            )

        }

    }
}


@Composable
fun AnalysisSp02Section(
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.sp02),
            style = MaterialTheme.typography.subtitle2,
            fontSize = dimensions.fontSizeSubtitle_2
        )

        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            AnalysisField(
                title = stringResource(R.string.sp02_average),
                value = "18",
                dimensions = dimensions
            )
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(
                title = stringResource(R.string.breathing_rate),
                value = "18",
                dimensions = dimensions
            )
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(
                title = stringResource(R.string.hypoxia_case),
                value = "18",
                dimensions = dimensions
            )
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(
                title = stringResource(R.string.cardiac_pressure),
                value = "18",
                dimensions = dimensions
            )

        }

    }
}


//fun Modifier.customDialogModifier(pos: String) = layout { measurable, constraints ->
//
//    val placeable = measurable.measure(constraints)
//    layout(constraints.maxWidth, constraints.maxHeight) {
//        when (pos) {
//            "BOTTOM" -> {
//                placeable.place(0, constraints.maxHeight - placeable.height, 10f)
//            }
//            "TOP" -> {
//                placeable.place(0, 0, 10f)
//            }
//        }
//    }
//}
//
//
//@ExperimentalComposeUiApi
//@Composable
//fun InfoDialogForSOAS(
//    visible: MutableState<Boolean>,
//    modifier: Modifier = Modifier
//) {
//    if (visible.value) {
//        AlertDialog(
//            onDismissRequest = { visible.value = false },
//            title = {
//                Text(
//                    text = stringResource(R.string.soas_analysis),
//                    style = MaterialTheme.typography.caption
//                )
//            },
//            text = {
//                Text(
//                    text = stringResource(R.string.soas_description),
//                    style = MaterialTheme.typography.body1
//                )
//            },
//            confirmButton = {
//                MainButton(
//                    text = stringResource(id = R.string.understand),
//                    onClick = { visible.value = false },
//                    enableState = true,
//                    modifier = modifier.fillMaxWidth().padding(16.dp)
//                )
//            },
//            shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
//            modifier = modifier.fillMaxWidth().customDialogModifier("BOTTOM"),
//            properties = DialogProperties(usePlatformDefaultWidth = false),
//        )
//    }
//
//}
