package com.example.sauexpert.general_report

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.AnalysisField
import com.example.sauexpert.bracelet_indicator.TextWithIconForGraph
import com.example.sauexpert.bracelet_indicator.dpToPxValue
import com.example.sauexpert.bracelet_indicator.identifyHeightForYPoint
import com.example.sauexpert.indicator_with_chart.BarChartForGlucose
import com.example.sauexpert.indicator_with_chart.BottomSheetContentForGlucose
import com.example.sauexpert.indicator_with_chart.MeasurementChangeForGlucose
import com.example.sauexpert.model.GlucoseData
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.ui.theme.Blue4289
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Pink4294
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun GlucoseReportScreen() {

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
                GlucoseReportWithBarChart(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                        visible.value = false
                    },
                    state = state,
                    visible = visible
                )

                Spacer(modifier = Modifier.height(16.dp))

                ReferenceIndicatorSection()

                Spacer(modifier = Modifier.height(16.dp))

                IndicatorForMonthSection()

                Spacer(modifier = Modifier.height(16.dp))

                DeviationsFromGeneralSection()
            }
        }
    }
}


@Composable
fun GlucoseReportWithBarChart(
    onClick: (Int) -> Unit,
    visible: MutableState<Boolean>,
    state: String,
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
        Text(
            text = stringResource(id = R.string.blood_glucose),
            style = MaterialTheme.typography.caption
        )
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
            text = stringResource(id = R.string.level_of_glucose_before_food).toUpperCase(Locale.current)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextWithIconForGraph(
            color = Blue4289,
            text = stringResource(id = R.string.level_of_glucose_after_food).toUpperCase(Locale.current)
        )
    }
}

@Composable
fun ReferenceIndicatorSection(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Canvas(
            modifier = Modifier
                .width(27.dp)
                .height(20.dp)
                .padding(16.dp)
        ) {

            var width = 0

            drawRect(
                color = Color.Green.copy(alpha = 0.05f),
                topLeft = Offset(
                    x = 0.dp.toPx(),
                    y = 0.dp.toPx()
                ),
                size = Size(
                    width = 27.dp.toPx(),
                    height = 20.dp.toPx()
                )
            )

            for (i in 0 until 4) {
                drawLine(
                    Gray30.copy(alpha = 0.5f),
                    Offset(
                        x = (width + 6).dp.toPx(),
                        y = 0.dp.toPx()
                    ),
                    Offset(
                        x = width.dp.toPx(),
                        y = 20.dp.toPx()
                    )
                )
                width += 6
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        AnalysisField(title = stringResource(R.string.references_value), value = "3,5 — 4,1")
    }
}


@Composable
fun IndicatorForMonthSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Показатели за Декабрь 2021",
            style = MaterialTheme.typography.subtitle1,
            fontSize = 15.sp,
            color = Gray30
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
            AnalysisField(title = stringResource(R.string.total_measurements), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(title = stringResource(R.string.case_before_food), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(title = stringResource(R.string.case_after_food), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(
                title = stringResource(R.string.missed_measurements),
                value = "18",
                color = Color.Red
            )

        }

    }
}


@Composable
fun DeviationsFromGeneralSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.deviations_from_general),
            style = MaterialTheme.typography.subtitle1,
            fontSize = 15.sp,
            color = Gray30
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
            AnalysisField(title = stringResource(R.string.total_case), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(
                title = stringResource(R.string.significantly_above_general),
                value = "18"
            )
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(title = stringResource(R.string.above_general), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(title = stringResource(R.string.below_general), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisField(
                title = stringResource(R.string.significantly_below_general),
                value = "18"
            )

        }

    }
}