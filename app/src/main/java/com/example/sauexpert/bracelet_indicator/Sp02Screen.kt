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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.Sp02Data
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun Sp02Screen() {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp),
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {

            BottomSheetContentForSoas(
                onClick = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
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
                .padding(horizontal =  16.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 24.dp, bottom = 10.dp)
            ) {
                SP02withLineGraph()
                Spacer(modifier = Modifier.height(24.dp))


                AnalysisSp02Section()
                Spacer(modifier = Modifier.height(24.dp))

                AnalysisSOASTitle(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))
                AnalysisSOASSection()
                Spacer(modifier = Modifier.height(16.dp))
                RangeCustomizeSection()
            }

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
                shape = RoundedCornerShape(10.dp)
            ).padding(16.dp)
    ) {
        SP02Title()
        Spacer(modifier = Modifier.height(40.dp))
        LineChartForSp02(
            Sp02Data = listOf(
                Sp02Data(positionOnX = 0f, positionOnY = 0f, dateName = "16"),
                Sp02Data(
                    positionOnX = 110f,
                    positionOnY = 100f,
                    dateName = "17",
                    sleepApnea = true
                ),
                Sp02Data(positionOnX = 210f, positionOnY = 30f, dateName = "18"),
                Sp02Data(
                    positionOnX = 310f,
                    positionOnY = 200f,
                    dateName = "19",
                    sleepApnea = true
                ),
                Sp02Data(positionOnX = 410f, positionOnY = 120f, dateName = "20"),
                Sp02Data(positionOnX = 510f, positionOnY = 30f, dateName = "21"),
                Sp02Data(positionOnX = 610f, positionOnY = 280f, dateName = "22"),
            ),
            ListNumberData = listOf(
                ListNumberOfYForTableData("100"),
                ListNumberOfYForTableData("90"),
                ListNumberOfYForTableData("80"),
                ListNumberOfYForTableData("70"),
                ListNumberOfYForTableData("60")
            )

        )
        Spacer(modifier = Modifier.height(20.dp))

        TextWithIconForGraph(
            color = Color.Green.copy(alpha = 0.25f),
            text = stringResource(id = R.string.oxygen_level)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextWithIconForGraph(
            color = Color.Red,
            text = stringResource(id = R.string.sleep_apnea)
        )
//        SP02Indicator()

    }
}

@Composable
fun SP02Title(
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    var textDate = "18-20 ноября 2021"

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.sp02),
                style = MaterialTheme.typography.caption
            )


            CustomTextRadioGroup() {
                selectedTabIndex = it
            }
            when (selectedTabIndex) {
                0 -> textDate = "18-20 ноября 2021"
                1 -> textDate = "Ноября 2021"

            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = textDate,
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
            color = Gray30
        )
    }
}


@Composable
fun LineChartForSp02(
    Sp02Data: List<Sp02Data>,
    ListNumberData: List<ListNumberOfYForTableData>
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
                //            path.relativeLineTo(30f, -30F)

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
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {
            drawLine(
                start = Offset(0f, height.dp.toPx()),
                end = Offset(780f, height.dp.toPx()),
                color = Gray30,
                strokeWidth = 2f
            )

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

//            drawPath(
//                path = path,
//                color = Color.Green,
//                style = Stroke(width = 6f)
//            )

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
                strokeWidth = 5f
            )

            if (Sp02Data[i].sleepApnea) {
                drawCircle(
                    color = Color.White,
                    radius = 13f,
                    center = Offset(Sp02Data[i].positionOnX, Sp02Data[i].positionOnY - 1f)
                )

                drawCircle(
                    color = Color.Red,
                    radius = 10f,
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
fun AnalysisSOASSection(modifier: Modifier = Modifier) {
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
                imageVector = Icons.Filled.Circle
            )
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(title = stringResource(R.string.sleep_apnea_case), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(title = stringResource(R.string.hypopnea_case), value = "18")

        }

    }
}


@ExperimentalMaterialApi
@Composable
fun BottomSheetContentForSoas(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(screenHeight / 2)
            .background(
                color = Color.White
            )
    ) {
        Column(
            modifier = modifier.fillMaxSize().padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.soas_analysis),
                style = MaterialTheme.typography.caption
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.soas_description),
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(24.dp))

            MainButton(
                text = stringResource(id = R.string.understand),
                onClick = onClick,
                enableState = true
            )
        }
    }
}

@Composable
fun AnalysisSOASTitle(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.soas_analysis),
            style = MaterialTheme.typography.subtitle2,
        )

        ClickableText(
            text = AnnotatedString(stringResource(R.string.more_detail)),
            style = MaterialTheme.typography.body2.copy(Color.Red),
            onClick = onClick,
        )
    }
}


@Composable
fun AnalysisSp02Section(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.sp02),
            style = MaterialTheme.typography.subtitle2,
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
            AnalysisField(title = stringResource(R.string.sp02_average), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(title = stringResource(R.string.breathing_rate), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(title = stringResource(R.string.hypoxia_case), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(title = stringResource(R.string.cardiac_pressure), value = "18")

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
