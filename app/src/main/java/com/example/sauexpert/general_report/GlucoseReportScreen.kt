package com.example.sauexpert.general_report

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
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
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.indicator_with_chart.BarChartForGlucose
import com.example.sauexpert.indicator_with_chart.BottomSheetContentForGlucose
import com.example.sauexpert.indicator_with_chart.MeasurementChangeForGlucose
import com.example.sauexpert.model.GlucoseData
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.ui.theme.Blue4289
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Pink4294
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun GlucoseReportScreen() {
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
                dimensions = dimensions,
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
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                ActionToolBarWithSubtitle(
                    titleText = stringResource(R.string.blood_glucose),
                    subtitleText = "Декабрь 2021",
                    iconBackClick = Icons.Default.ArrowBack,
                    sizeText = dimensions.fontSizeSubtitle_2,
                    sizeSubtitleText = dimensions.fontSizeBody_2,
                    sizeIcon = dimensions.iconSize_2,
                    onBackClick = {},
                    onRightClick = {}
                )

                Spacer(modifier = Modifier.height(dimensions.grid_2))

                GlucoseReportWithBarChart(
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

                Spacer(modifier = Modifier.height(16.dp))

                ReferenceIndicatorSection(
                    textValue = "3,5 — 4,1",
                    dimensions = dimensions
                )

                Spacer(modifier = Modifier.height(16.dp))

                IndicatorForMonthSection(
                    glucoseValueAfterFood = 18,
                    glucoseValueBeforeFood = 18,
                    dimensions = dimensions
                )

                Spacer(modifier = Modifier.height(16.dp))

                DeviationsFromGeneralSection(dimensions = dimensions)
            }
        }
    }
}


@Composable
fun GlucoseReportWithBarChart(
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
        Text(
            text = stringResource(id = R.string.blood_glucose),
            style = MaterialTheme.typography.caption,
            fontSize = dimensions.fontSizeCaption
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
            visible = visible,
            dimensions = dimensions
        )

        Spacer(modifier = Modifier.height(16.dp))

        MeasurementChangeForGlucose(
            onClick = onClick,
            state = state,
            dimensions
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
fun ReferenceIndicatorSection(
    textValue: String,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ).padding(14.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_reference_indicator),
            tint = Color.Green,
            contentDescription = "",
        )

        AnalysisField(
            title = stringResource(R.string.references_value),
            value = textValue,
            dimensions = dimensions
        )
    }
}


@Composable
fun IndicatorForMonthSection(
    glucoseValueAfterFood: Int? = null,
    glucoseValueBeforeFood: Int? = null,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Показатели за Декабрь 2021",
            style = MaterialTheme.typography.subtitle1,
            fontSize = dimensions.fontSizeCustom_1,
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
            AnalysisField(
                title = stringResource(R.string.total_measurements),
                value = "18",
                dimensions = dimensions
            )
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            glucoseValueAfterFood?.let {
                AnalysisField(
                    title = stringResource(R.string.case_before_food),
                    value = it.toString(),
                    dimensions = dimensions
                )
                Divider(
                    color = Gray30.copy(alpha = 0.19f),
                    thickness = 1.dp,
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                )
            }

            glucoseValueBeforeFood?.let {
                AnalysisField(
                    title = stringResource(R.string.case_after_food),
                    value = it.toString(),
                    dimensions = dimensions
                )
                Divider(
                    color = Gray30.copy(alpha = 0.19f),
                    thickness = 1.dp,
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                )
            }

            AnalysisField(
                title = stringResource(R.string.missed_measurements),
                value = "18",
                color = Color.Red,
                dimensions = dimensions
            )

        }

    }
}


@Composable
fun DeviationsFromGeneralSection(
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.deviations_from_general),
            style = MaterialTheme.typography.subtitle1,
            fontSize = dimensions.fontSizeCustom_1,
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
            AnalysisField(
                title = stringResource(R.string.total_case),
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
                title = stringResource(R.string.significantly_above_general),
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
                title = stringResource(R.string.above_general),
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
                title = stringResource(R.string.below_general),
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
                title = stringResource(R.string.significantly_below_general),
                value = "18",
                dimensions = dimensions
            )

        }

    }
}