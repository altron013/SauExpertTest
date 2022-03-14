package com.example.sauexpert.bracelet_indicator

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.*
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Gray4292
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBar
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun BraceletIndicatorScreen() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions
    val navigator = LocalNavigator.currentOrThrow

    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                color = Gray30.copy(alpha = 0.19f)
            )
//            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(5.dp))

        ActionToolBar(
            titleText = stringResource(id = R.string.bracelet),
            iconBackClick = Icons.Default.ArrowBack,
            iconRightClick = painterResource(R.drawable.ic_calendar),
            colorRightClick = Color.Red,
            sizeText = dimensions.fontSizeSubtitle_2,
            sizeIcon = dimensions.iconSize_2,
            onBackClick = { navigator.pop() },
            onRightClick = {}
        )

        Spacer(modifier = Modifier.height(dimensions.grid_3_5))

        TabViewWithRoundBorder(
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
            ),
            dimensions = dimensions
        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                SleepScreen()
            }
            1 -> Sp02Screen()
            2 -> Box(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                HRVScreen()
            }
            3 -> Box(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 16.dp)

            ) {
                PressureScreen()
            }
            4 -> Box(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                PulseScreen()
            }
        }
    }
}

@Composable
fun TabViewWithRoundBorder(
    modifier: Modifier = Modifier,
    TextOfTab: List<TextOfTabData>,
    dimensions: Dimensions,
    onTabSelected: (selectedIndex: Int) -> Unit,

    ) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val shape = RoundedCornerShape(10.dp)
    val backgroundColor = Gray4292

    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = backgroundColor,
        contentColor = Color.Transparent,
        divider = {},
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(shape = shape)
            .border(width = 2.dp, color = backgroundColor, shape = shape)

    ) {
        TextOfTab.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)

                },
                modifier = modifier
                    .background(
                        if (selectedTabIndex == index) Color.White else backgroundColor,
                        shape = shape
                    )
                    .clip(shape = shape)
            ) {
                Text(
                    text = item.text,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensions.fontSizeH5,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }

        }

    }
}


@Composable
fun TextWithIconForGraph(
    color: Color,
    text: String,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.Circle,
            contentDescription = "",
            tint = color,
            modifier = modifier.size(dimensions.iconSize_7)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            fontSize = dimensions.fontSizeCustom_3,
            color = Gray30,
        )

    }
}


@Composable
fun TextWithBigValueAndDateForGraph(
    textValue: Int,
    text: String,
    textDate: String,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = dimensions.fontSizeH4
                    )
                ) {
                    append("$textValue ")
                }

                append(text)
            },
            style = MaterialTheme.typography.subtitle1,
            fontSize = dimensions.fontSizeSubtitle_1,
            fontWeight = FontWeight.Bold,
            color = Gray30
        )

        Text(
            text = textDate,
            style = MaterialTheme.typography.h6,
            fontSize = dimensions.fontSizeCustom_1,
            color = Gray30
        )
    }

}


@Composable
fun CustomTextRadioGroup(
    TextOfTab: List<TextOfTabData>,
    backgroundColor: Color = Gray4292,
    textColor: Color = Color.Black,
    activity: AppCompatActivity? = null,
    dateText: MutableState<String>? = null,
    dimensions: Dimensions,
    onTabSelected: (selectedIndex: Int) -> Unit,
) {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }


    val listSize = TextOfTab.size - 1

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextOfTab.forEachIndexed { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(width = 1.dp, color = Gray4292, shape = RoundedCornerShape(10.dp))
                    .clip(
                        shape = RoundedCornerShape(
                            size = 10.dp,
                        ),
                    )
                    .clickable {
                        selectedTabIndex = index
                        onTabSelected(index)
                        if (index == 2) {
                            activity?.let {
                                dateText?.let { it1 -> showDatePicker(it, it1) }
                            }
                        }
                    }
                    .background(
                        if (selectedTabIndex == index) backgroundColor else Color.White
                    )
                    .padding(
                        vertical = 4.dp,
                        horizontal = 12.dp,
                    )
            ) {

                item.painter?.let {
                    Icon(
                        painter = it,
                        contentDescription = null,
                        tint = Gray30,
                        modifier = Modifier.size(dimensions.iconSize_6)
                    )

                    Spacer(modifier = Modifier.width(6.dp))
                }

                Text(
                    text = item.text,
                    style = MaterialTheme.typography.h5,
                    fontSize = dimensions.fontSizeH5,
                    color = if (selectedTabIndex == index) textColor else Color.Black,

                    )
            }

            if (index < listSize) {
                Spacer(modifier = Modifier.width(8.dp))
            }

        }
    }
}

fun showDatePicker(
    activity: AppCompatActivity,
    dateText: MutableState<String>
) {
    val picker = MaterialDatePicker.Builder.dateRangePicker().build()
    activity?.let {
        picker.show(it.supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener { dateSelected ->
            val startDate = dateSelected.first
            val endDate = dateSelected.second

            if (startDate != null && endDate != null) {
                dateText.value = "${convertLongToTime(startDate)} - ${convertLongToTime(endDate)}"
            }
        }
    }
}

private fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat(
        "dd.MM.yyyy",
        Locale.getDefault()
    )
    return format.format(date)
}

@Composable
fun TitleForGraph(
    textTitle: String,
    TextOfTab: List<TextOfTabData>,
    weight: Float = 0.5f,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember {
        mutableStateOf(1)
    }

    val date = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(weight)
            ) {
                Text(
                    text = textTitle,
                    style = MaterialTheme.typography.caption,
                    fontSize = dimensions.fontSizeCaption
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f - weight)
            ) {

                CustomTextRadioGroup(
                    TextOfTab = TextOfTab,
                    dimensions = dimensions
                ) {
                    selectedTabIndex = it
                }
                when (selectedTabIndex) {
                    0 -> date.value = "18-20 ноября 2021"
                    1 -> date.value = "Ноября 2021"

                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = date.value,
            style = MaterialTheme.typography.h6,
            fontSize = dimensions.fontSizeCustom_1,
            color = Gray30
        )
    }
}


@Composable
fun AnalysisField(
    title: String,
    value: String,
    dateData: String? = null,
    color: Color = Color.Black,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 11.dp, horizontal = 16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.body1,
                color = color,
                fontSize = dimensions.fontSizeBody_1
            )

            Text(
                text = value,
                style = MaterialTheme.typography.body1,
                color = color,
                fontSize = dimensions.fontSizeBody_1
            )

        }

        dateData?.let {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = it,
                style = MaterialTheme.typography.h5,
                fontSize = dimensions.fontSizeH5,
                color = Gray30
            )
        }
    }
}

@Composable
fun AnalysisFieldWithIconAtBeg(
    title: String,
    value: String,
    dateData: String? = null,
    imageVector: ImageVector,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 11.dp, horizontal = 16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = "",
                tint = Color.Red,
                modifier = modifier.size(dimensions.iconSize_1)
            )

            Spacer(modifier = Modifier.width(3.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.body1,
                fontSize = dimensions.fontSizeBody_1
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = value,
                style = MaterialTheme.typography.body1,
                fontSize = dimensions.fontSizeBody_1
            )

        }
        dateData?.let {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = it,
                style = MaterialTheme.typography.h5,
                fontSize = dimensions.fontSizeH5,
                color = Gray30
            )
        }
    }
}

@Composable
fun AnalysisFieldWithIconAtEnd(
    title: String,
    value: String,
    dateData: String? = null,
    imageVector: ImageVector,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 11.dp, horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.body1,
                fontSize = dimensions.fontSizeBody_1,
            )

            Spacer(modifier = Modifier.width(2.dp))

            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = Color.Yellow,
                modifier = modifier.size(dimensions.iconSize_3)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = value,
                style = MaterialTheme.typography.body1,
                fontSize = dimensions.fontSizeBody_1
            )

        }

        dateData?.let {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = it,
                style = MaterialTheme.typography.h5,
                fontSize = dimensions.fontSizeH5,
                color = Gray30
            )
        }

    }
}


@Composable
fun RangeCustomizeSection(
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
            .clip(
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(19.dp)

        ) {
            Icon(
                painter = painterResource(R.drawable.ic_gear),
                contentDescription = "",
                tint = Color.Black,
                modifier = modifier.size(dimensions.iconSize_3)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = stringResource(R.string.range_customize),
                style = MaterialTheme.typography.body1,
                fontSize = dimensions.fontSizeBody_1
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "",
                tint = Color.Black,
                modifier = modifier.size(dimensions.iconSize_3)
            )

        }
    }
}


@Composable
fun dpToPxValue(number: Dp) = LocalDensity.current.run {
    number.toPx()
}

@Composable
fun identifyHeightForYPoint(
    dataList: List<ListNumberOfYForTableData>,
    number: Int,
): Float {
    for ((index, dataList) in dataList.withIndex()) {
        when {
            number == dataList.number -> {
                return dpToPxValue((index * 35).dp)
            }
            number > dataList.number -> {
                return dpToPxValue((index * 30).dp)
            }
        }
    }
    return dpToPxValue(((dataList.size - 1) * 35).dp)
}


@Composable
fun identifyHeightForYPointForString(
    dataList: List<ListStringOfYForTableData>,
    text: String,
): Float {
    for ((index, dataList) in dataList.withIndex()) {
        if (text == dataList.text) {
            return dpToPxValue((index * 35).dp)
        }
    }
    return dpToPxValue(((dataList.size - 1) * 35).dp)
}


fun identifyClickItem(dataList: List<Any>, x: Float, y: Float, size: Float): Int {
    dataList.forEachIndexed { index, any ->
        when (any) {
            is SleepData -> {
                if (positionCorrectForBarChart(
                        size = size,
                        positionOnX = any.positionOnX,
                        positionOnY = any.hourOfSleep,
                        x = x,
                        y = y
                    )
                ) return index
            }
            is HRVData -> {
                if (positionCorrectForBarChart(
                        size = size,
                        positionOnX = any.positionOnX,
                        positionOnY = any.positionOnY,
                        x = x,
                        y = y
                    )
                ) return index
            }
            is StepsData -> {
                if (positionCorrectForBarChart(
                        size = size,
                        positionOnX = any.positionOnX,
                        positionOnY = any.positionOnY,
                        x = x,
                        y = y
                    )
                ) return index
            }
            is PressureData -> {
                if (positionCorrectWithStartPoint(
                        size = size,
                        positionOnX = any.positionOnX,
                        startPoint = any.startPoint,
                        endPoint = any.endPoint,
                        x = x,
                        y = y
                    )
                ) return index
            }
            is PulseData -> {
                if (positionCorrectForLineChart(
                        size = size,
                        positionOnX = any.positionOnX,
                        positionOnY = any.positionOnY,
                        x = x,
                        y = y
                    )
                ) return index
            }
        }
    }
    return -1
}

fun positionCorrectForBarChart(
    size: Float,
    positionOnX: Float,
    positionOnY: Float,
    x: Float,
    y: Float
) = x > positionOnX && x < positionOnX + size && y > positionOnY

fun positionCorrectWithStartPoint(
    size: Float,
    positionOnX: Float,
    startPoint: Float,
    endPoint: Float,
    x: Float,
    y: Float
) = x > positionOnX && x < positionOnX + size && y > startPoint - size && y < endPoint + size

fun positionCorrectForLineChart(
    size: Float,
    positionOnX: Float,
    positionOnY: Float,
    x: Float,
    y: Float
) = x > positionOnX && x < positionOnX + size && y > positionOnY - size && y < positionOnY + size