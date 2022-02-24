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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.LineChartForSp02
import com.example.sauexpert.bracelet_indicator.TextWithIconForGraph
import com.example.sauexpert.bracelet_indicator.dpToPxValue
import com.example.sauexpert.bracelet_indicator.identifyHeightForYPoint
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.ListNumberOfYForTableData
import com.example.sauexpert.model.Sp02Data
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle

@Composable
fun Sp02ReportScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

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
                titleText = stringResource(R.string.sp02),
                subtitleText = "Декабрь 2021",
                iconBackClick = Icons.Default.ArrowBack,
                sizeText = dimensions.fontSizeSubtitle_2,
                sizeSubtitleText = dimensions.fontSizeBody_2,
                sizeIcon = dimensions.iconSize_2,
                onBackClick = {},
                onRightClick = {}
            )

            Spacer(modifier = Modifier.height(dimensions.grid_2))

            Sp02ReportWithLineGraph(dimensions = dimensions)

        }
    }
}


@Composable
fun Sp02ReportWithLineGraph(
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

        Text(
            text = stringResource(id = R.string.sp02),
            style = MaterialTheme.typography.caption,
            fontSize = dimensions.fontSizeCaption
        )

        Spacer(modifier = Modifier.height(dimensions.grid_1_5))

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = dimensions.fontSizeBody_1
                    )
                ) {
                    append("80 ")
                }

                append(stringResource(R.string.oxygen))
            },
            style = MaterialTheme.typography.body1,
            fontSize = dimensions.fontSizeBody_1,
            color = Gray30
        )

        Spacer(modifier = Modifier.height(dimensions.grid_2))

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

        Spacer(modifier = Modifier.height(dimensions.grid_2_5))

        TextWithIconForGraph(
            color = Color.Green.copy(alpha = 0.25f),
            text = stringResource(id = R.string.oxygen_level).toUpperCase(Locale.current),
            dimensions = dimensions
        )

        Spacer(modifier = Modifier.height(dimensions.grid_1))

        TextWithIconForGraph(
            color = Color.Red,
            text = stringResource(id = R.string.sleep_apnea).toUpperCase(Locale.current),
            dimensions = dimensions
        )
    }
}