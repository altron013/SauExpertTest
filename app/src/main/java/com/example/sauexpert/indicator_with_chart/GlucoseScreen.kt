package com.example.sauexpert.indicator_with_chart

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.chargemap.compose.numberpicker.ListItemPicker
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.CustomTextRadioGroup
import com.example.sauexpert.bracelet_indicator.TextWithIconForGraph
import com.example.sauexpert.bracelet_indicator.dpToPxValue
import com.example.sauexpert.bracelet_indicator.identifyHeightForYPoint
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.GlucoseData
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Blue4289
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Pink4294
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun GlucoseScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    val visible = remember { mutableStateOf(false) }

    val list = listOf(
        stringResource(R.string.before_after_food),
        stringResource(R.string.before_food),
        stringResource(R.string.after_food),
    )

    var state by rememberSaveable { mutableStateOf(list[0]) }

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp),
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {

            BottomSheetContentForGlucose(
                onClick = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                },
                possibleValues = list,
                state = state,
                onNameChange = { state = it }
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
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 24.dp, bottom = 10.dp)
            ) {
                GlucosewithBarChart(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                        visible.value = false
                    },
                    state = state,
                    visible = visible,
                    dimensions = dimensions
                )
            }
        }
    }
}

@Composable
fun GlucosewithBarChart(
    onClick: (Int) -> Unit,
    visible: MutableState<Boolean>,
    state: String,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListNumberOfYForTableData(8),
        ListNumberOfYForTableData(6),
        ListNumberOfYForTableData(4),
        ListNumberOfYForTableData(2),
        ListNumberOfYForTableData(0),
    )


    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ).padding(16.dp)
    ) {
        GlucoseTitle(dimensions = dimensions)
        Spacer(modifier = Modifier.height(12.dp))
        BarChartForGlucose(
            glucoseData = listOf(
                GlucoseData(
                    positionOnX = 0f,
                    glucoseBeforeFood = 8,
                    positionOnYBeforeFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 8
                    ),
                    glucoseAfterFood = 6,
                    positionOnYAfterFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 6
                    ),
                    dateName = "16"
                ),
                GlucoseData(
                    positionOnX = screenWidth,
                    glucoseBeforeFood = 5,
                    positionOnYBeforeFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 5
                    ),
                    glucoseAfterFood = 6,
                    positionOnYAfterFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 6
                    ),
                    dateName = "17"
                ),
                GlucoseData(
                    positionOnX = (screenWidth * 2),
                    glucoseBeforeFood = 4,
                    positionOnYBeforeFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 4
                    ),
                    glucoseAfterFood = 7,
                    positionOnYAfterFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 7
                    ),
                    dateName = "18"
                ),
                GlucoseData(
                    positionOnX = (screenWidth * 3),
                    glucoseBeforeFood = 9,
                    positionOnYBeforeFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 9
                    ),
                    glucoseAfterFood = 4,
                    positionOnYAfterFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 4
                    ),
                    dateName = "19",
                ),
                GlucoseData(
                    positionOnX = (screenWidth * 4),
                    glucoseBeforeFood = 8,
                    positionOnYBeforeFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 8
                    ),
                    glucoseAfterFood = 8,
                    positionOnYAfterFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 8
                    ),
                    dateName = "20",
                ),
                GlucoseData(
                    positionOnX = (screenWidth * 5),
                    glucoseBeforeFood = 8,
                    positionOnYBeforeFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 8
                    ),
                    glucoseAfterFood = 8,
                    positionOnYAfterFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 8
                    ),
                    dateName = "21"
                ),
                GlucoseData(
                    positionOnX = (screenWidth * 6),
                    glucoseBeforeFood = 5,
                    positionOnYBeforeFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 5
                    ),
                    glucoseAfterFood = 5,
                    positionOnYAfterFood = identifyHeightForYPoint(
                        dataList = listNumberData,
                        number = 5
                    ),
                    dateName = "22"
                )
            ),
            ListNumberData = listNumberData,
            state = state,
            visible = visible
        )

        Spacer(modifier = Modifier.height(16.dp))

        MeasurementChangeForGlucose(
            onClick = onClick,
            state = state
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextWithIconForGraph(
            color = Pink4294,
            text = stringResource(id = R.string.level_of_glucose_before_food).toUpperCase(Locale.current),
            dimensions = dimensions
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextWithIconForGraph(
            color = Blue4289,
            text = stringResource(id = R.string.level_of_glucose_after_food).toUpperCase(Locale.current),
            dimensions = dimensions
        )
    }
}

@Composable
fun GlucoseTitle(
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val date = remember { mutableStateOf("") }
    val activity = LocalContext.current as AppCompatActivity

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.blood_glucose),
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(12.dp))

        CustomTextRadioGroup(
            TextOfTab = listOf(
                TextOfTabData(stringResource(R.string.week_short).toUpperCase(Locale.current)),
                TextOfTabData(stringResource(R.string.month_short).toUpperCase(Locale.current)),
                TextOfTabData(
                    stringResource(R.string.choose).toUpperCase(Locale.current),
                    painter = painterResource(R.drawable.ic_calendar)
                )
            ),
            activity = activity,
            dateText = date,
            dimensions = dimensions
        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> date.value = "18-20 ноября 2021"
            1 -> date.value = "Ноября 2021"

        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = date.value,
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
            color = Gray30
        )


    }
}


@Composable
fun BarChartForGlucose(
    glucoseData: List<GlucoseData>,
    state: String,
    visible: MutableState<Boolean>,
    ListNumberData: List<ListNumberOfYForTableData>
) {
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    val listSize = glucoseData.size - 1
    val heightForGraph = (ListNumberData.size * 35).dp

    val itemID = remember { mutableStateOf(-1) }
    val positionOfX = remember { mutableStateOf(1) }
    val positionOfY = remember { mutableStateOf(1) }
    val hideBarBeforeFood: Boolean
    val hideBarAfterFood: Boolean

    when (state) {
        stringResource(R.string.before_food) -> {
            hideBarBeforeFood = false
            hideBarAfterFood = true
        }
        stringResource(R.string.after_food) -> {
            hideBarBeforeFood = true
            hideBarAfterFood = false
        }
        else -> {
            hideBarBeforeFood = false
            hideBarAfterFood = false
        }
    }

    InfoDialogForBarChartOfGlucose(
        visible = visible,
        itemID = itemID,
        xPosition = positionOfX,
        yPosition = positionOfY,
        GlucoseData = glucoseData
    )

    setRedColorInsideDataClassForGlucose(
        GlucoseData = glucoseData,
        itemID = itemID,
        visible = visible
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightForGraph)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        itemID.value = identifyClickItemForGlucose(
                            dataList = glucoseData,
                            x = it.x,
                            y = it.y,
                            size = 20.dp.toPx()
                        )
                        ResetColorInsideDataClassForGlucose(GlucoseData = glucoseData)
                        positionOfX.value = it.x.toInt()
                        positionOfY.value = it.y.toInt()
                        if (itemID.value != -1) {
                            visible.value = true
                            setRedColorInsideDataClassForGlucose(
                                GlucoseData = glucoseData,
                                itemID = itemID,
                                visible = visible
                            )
                        }
                    }
                )
            }
    ) {
        var height = 0
        var width = 0
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 13.sp.toPx()
            color = Gray30.toArgb()
        }

        for (i in ListNumberData) {
            drawContext.canvas.nativeCanvas.drawText(
                i.number.toString(),
                glucoseData[listSize].positionOnX + 40.dp.toPx(),
                (height + 4).dp.toPx(),
                paint
            )

            height += 35
        }

        drawRect(
            color = Color.Green.copy(alpha = 0.05f),
            topLeft = Offset(
                x = 0f,
                y = 35.dp.toPx()
            ),
            size = Size(
                width = glucoseData[listSize].positionOnX + 20.dp.toPx(),
                height = 35.dp.toPx()
            )
        )

        for (i in 0 until listSize * 7) {
            drawLine(
                Gray30.copy(alpha = 0.5f),
                Offset(
                    x = (width + 6).dp.toPx(),
                    y = 35.dp.toPx()
                ),
                Offset(
                    x = width.dp.toPx(),
                    y = 70.dp.toPx()
                )
            )

            width += 6
        }

        start = true
        for (p in glucoseData) {
            if (!hideBarBeforeFood) {
                drawRect(
                    color = p.colorFocusBeforeFood,
                    topLeft = Offset(
                        x = p.positionOnX,
                        y = (height - 35).dp.toPx() - ((height - 35).dp.toPx() - p.positionOnYBeforeFood) * heightPre
                    ),
                    size = Size(
                        width = 8.dp.toPx(),
                        height = ((height - 35).dp.toPx() - p.positionOnYBeforeFood) * heightPre
                    )
                )
            }

            if (!hideBarAfterFood) {
                drawRect(
                    color = p.colorFocusAfterFood,
                    topLeft = Offset(
                        x = p.positionOnX + 12.dp.toPx(),
                        y = (height - 35).dp.toPx() - ((height - 35).dp.toPx() - p.positionOnYAfterFood) * heightPre
                    ),
                    size = Size(
                        width = 8.dp.toPx(),
                        height = ((height - 35).dp.toPx() - p.positionOnYAfterFood) * heightPre
                    )
                )
            }


            drawContext.canvas.nativeCanvas.drawText(
                "${p.dateName}",
                p.positionOnX + 10.dp.toPx(),
                (height - 15).dp.toPx(),
                paint
            )
        }
    }
}

private fun identifyClickItemForGlucose(
    dataList: List<GlucoseData>,
    x: Float,
    y: Float,
    size: Float
): Int {
    var itemY: Float
    for ((index, dataList) in dataList.withIndex()) {
        itemY = if (dataList.positionOnYBeforeFood > dataList.positionOnYAfterFood) {
            dataList.positionOnYAfterFood
        } else {
            dataList.positionOnYBeforeFood
        }


        if (x > dataList.positionOnX && x < dataList.positionOnX + size && y > itemY) {
            return index
        }
    }
    return -1
}

private fun ResetColorInsideDataClassForGlucose(GlucoseData: List<GlucoseData>) {
    for (p in GlucoseData) {
        p.colorFocusBeforeFood = Pink4294
        p.colorFocusAfterFood = Blue4289
    }
}

private fun setRedColorInsideDataClassForGlucose(
    GlucoseData: List<GlucoseData>,
    itemID: MutableState<Int>,
    visible: MutableState<Boolean>
) {
    if (itemID.value != -1 && visible.value) {
        ResetColorInsideDataClassForGlucose(GlucoseData = GlucoseData)
        GlucoseData[itemID.value].colorFocusBeforeFood = Color.Red
        GlucoseData[itemID.value].colorFocusAfterFood = Color.Red
    }
}

@Composable
fun InfoDialogForBarChartOfGlucose(
    visible: MutableState<Boolean>,
    itemID: MutableState<Int>,
    xPosition: MutableState<Int>,
    yPosition: MutableState<Int>,
    GlucoseData: List<GlucoseData>,
    modifier: Modifier = Modifier
) {
    if (visible.value) {
        Box {
            Popup(
                alignment = Alignment.Center,
                IntOffset(xPosition.value, yPosition.value - 70)
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 140.dp, height = 40.dp)
                        .background(Color.White, RoundedCornerShape(10.dp))
                ) {
                    if (itemID.value == -1) {
                        visible.value = false
                    } else {
                        Text(
                            text = "${itemID.value} | " +
                                    "${GlucoseData[itemID.value].glucoseBeforeFood} | " +
                                    "${GlucoseData[itemID.value].glucoseAfterFood} | " +
                                    "${GlucoseData[itemID.value].dateName}",
                            style = MaterialTheme.typography.h5,
                            modifier = modifier
                                .align(alignment = Alignment.Center)
                                .clickable {
                                    visible.value = false
                                }
                        )
                    }
                }
            }
        }
    } else {
        ResetColorInsideDataClassForGlucose(GlucoseData = GlucoseData)
    }
}

@Composable
fun MeasurementChangeForGlucose(
    onClick: (Int) -> Unit,
    state: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Gray30.copy(alpha = 0.19f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.measurements),
            style = MaterialTheme.typography.button,
            fontSize = 15.sp,
        )

        ClickableText(
            text = AnnotatedString(state),
            style = MaterialTheme.typography.subtitle2.copy(color = Color.Black, fontSize = 15.sp),
            onClick = onClick
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun BottomSheetContentForGlucose(
    modifier: Modifier = Modifier,
    possibleValues: List<String>,
    state: String,
    onNameChange: (String) -> Unit,
    onClick: (Int) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(screenHeight / 2)
            .background(
                color = Gray30.copy(alpha = 0.25f)
            )
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.White)
        ) {
            ClickableText(
                text = AnnotatedString(stringResource(R.string.done)),
                style = MaterialTheme.typography.body2.copy(Color.Red),
                onClick = onClick,
                modifier = modifier
                    .align(alignment = Alignment.TopEnd)
                    .padding(vertical = 14.dp, horizontal = 24.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .height(34.dp)
                .padding(horizontal = 25.dp)
                .background(
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .align(alignment = Alignment.Center)
        ) {

        }

        ListItemPicker(
            label = { it },
            value = state,
            onValueChange = onNameChange,
            list = possibleValues,
            dividersColor = Color.Transparent,
            modifier = modifier
                .align(alignment = Alignment.Center)
        )
    }
}
