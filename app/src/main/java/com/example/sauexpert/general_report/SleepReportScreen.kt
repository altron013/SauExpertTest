package com.example.sauexpert.general_report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.*
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.SleepData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle

@Composable
fun SleepReportScreen() {
    val visible = remember { mutableStateOf(false) }

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
                titleText = stringResource(R.string.sleep),
                subtitleText = "Декабрь 2021",
                iconBackClick = Icons.Default.ArrowBack,
                onBackClick = {},
                onRightClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            SleepReportWithBarChart(
                visible = visible
            )

            Spacer(modifier = Modifier.height(16.dp))

            SleepStatisticsReportSection(
                visible = visible
            )

        }
    }
}


@Composable
fun SleepReportWithBarChart(
    visible: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = dpToPxValue((configuration.screenWidthDp.dp - 70.dp) / 7)

    val listNumberData = listOf(
        ListNumberOfYForTableData(10),
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
            )
            .padding(16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.sleep),
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(12.dp))
        BarChartForSleep(
            ListNumberData = listNumberData,

            SleepData = listOf(
                SleepData(
                    positionOnX = (screenWidth * 0),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 7),
                    dateName = "16"
                ),
                SleepData(
                    positionOnX = (screenWidth * 1),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 4),
                    dateName = "17"
                ),
                SleepData(
                    positionOnX = (screenWidth * 2),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 5),
                    dateName = "18"
                ),
                SleepData(
                    positionOnX = (screenWidth * 3),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 6),
                    dateName = "19"
                ),
                SleepData(
                    positionOnX = (screenWidth * 4),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 2),
                    dateName = "20"
                ),
                SleepData(
                    positionOnX = (screenWidth * 5),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 3),
                    dateName = "21"
                ),
                SleepData(
                    positionOnX = (screenWidth * 6),
                    hourOfSleep = identifyHeightForYPoint(dataList = listNumberData, number = 1),
                    dateName = "22"
                )
            ),

            visible = visible

        )
    }
}


@Composable
fun SleepStatisticsReportSection(
    visible: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {

    if (visible.value) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {

            ProgressBarForSleep(
                deepSleepPercent = 40,
                lightSleepPercent = 35,
                remSleepPercent = 25,
            )

        }
    }

}