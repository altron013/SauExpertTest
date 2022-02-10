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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.TextOfTabData
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
            onBackClick = {},
            onRightClick = {}
        )

        Spacer(modifier = Modifier.height(28.dp))
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
            )


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
    onTabSelected: (selectedIndex: Int) -> Unit
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
                    .clip(
                        shape = shape,
                    )
            ) {
                Text(
                    text = item.text,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(4.dp)

                )
            }

        }

    }
}


@Composable
fun TextWithIconForGraph(
    color: Color,
    text: String,
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
            modifier = modifier.size(9.dp)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            fontSize = 13.sp,
            color = Gray30,
        )

    }
}


@Composable
fun TextWithBigValueAndDateForGraph(
    textValue: Int,
    text: String,
    textDate: String,
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
                        fontSize = 34.sp
                    )
                ) {
                    append("$textValue ")
                }

                append(text)
            },
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Gray30
        )

        Text(
            text = textDate,
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
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
    onTabSelected: (selectedIndex: Int) -> Unit
) {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextOfTab.forEachIndexed { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .border(width = 1.dp, color = Gray4292, shape = RoundedCornerShape(10.dp))
                    .clip(
                        shape = RoundedCornerShape(
                            size = 10.dp,
                        ),
                    )
                    .clickable {
                        selectedTabIndex = index
                        onTabSelected(index)
                        if(index == 2) {
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
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(6.dp))
                }

                Text(
                    text = item.text,
                    style = MaterialTheme.typography.h6,
                    color = if (selectedTabIndex == index) textColor else Color.Black,
                    fontSize = 13.sp,
                )
            }
        }
    }
}

fun showDatePicker(
    activity: AppCompatActivity,
    dateText: MutableState<String>
){
    val picker = MaterialDatePicker.Builder.dateRangePicker().build()
    activity?.let {
        picker.show(it.supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener { dateSelected ->
            val startDate  = dateSelected.first
            val endDate  = dateSelected.second

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
        Locale.getDefault())
    return format.format(date)
}


@Composable
fun AnalysisField(
    title: String,
    value: String,
    dateData: String? = null,
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
            )

            Text(
                text = value,
                style = MaterialTheme.typography.body1,
            )

        }

        dateData?.let {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = it,
                style = MaterialTheme.typography.h5,
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
                modifier = modifier.size(12.dp)
            )

            Spacer(modifier = Modifier.width(3.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.body1,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = value,
                style = MaterialTheme.typography.body1,
            )

        }
        dateData?.let {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = it,
                style = MaterialTheme.typography.h5,
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
    modifier: Modifier = Modifier
) {
    val myId = "inlineContent"
    val text = buildAnnotatedString {
        append(title)
        appendInlineContent(myId, "[icon]")
    }

    val inlineContent = mapOf(
        Pair(
            myId,
            InlineTextContent(
                Placeholder(
                    width = 20.sp,
                    height = 20.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                )
            ) {

                Icon(imageVector, "", tint = Color.Yellow)
            }
        )
    )

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
                text = text,
                style = MaterialTheme.typography.body1,
                inlineContent = inlineContent
            )

            Text(
                text = value,
                style = MaterialTheme.typography.body1,
            )

        }

        dateData?.let {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = it,
                style = MaterialTheme.typography.h5,
                color = Gray30
            )
        }

    }
}


@Composable
fun RangeCustomizeSection(modifier: Modifier = Modifier) {
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
                modifier = modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = stringResource(R.string.range_customize),
                style = MaterialTheme.typography.body1,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "",
                tint = Color.Black,
                modifier = modifier.size(20.dp)
            )

        }
    }
}






