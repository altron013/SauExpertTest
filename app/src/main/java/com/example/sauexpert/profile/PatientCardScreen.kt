package com.example.sauexpert.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.buttons.MainButtonsInRow

@Composable
fun PatientCardScreen() {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
    ) {
        TopBarForPatientCardScreen()
        Spacer(modifier = Modifier.height(24.dp))
        ProfileForPatientCard(
            userName = "Жанна Ахметова",
            diagnosisString = "E11.9 Сахарный диабет"
        )
        Spacer(modifier = Modifier.height(16.dp))
        AnamnesisCell(lastAnamnesisDiagnos = "Перенесла операцию на сердце в 2017")
        Spacer(modifier = Modifier.height(28.dp))

        TabViewForPatientCardScreen(
            TextOfTab = listOf(
                TextOfTabData(
                    text = stringResource(id = R.string.indicators)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.diagnosis)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.analysis)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.appointments)
                )
            )


        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(bottom = 10.dp)

            ) {
                IndicatorsScreen()

                MainButtonsInRow(
                    textForOutlineBtn = stringResource(id = R.string.skip),
                    textForMainBtn = stringResource(id = R.string.next),
                    onClickForOutlineBtn = { /*TODO*/ },
                    onClickForMainBtn = { /*TODO*/ },
                    enableStateForOutlineBtn = true,
                    enableStateForMainBtn = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .align(Alignment.BottomCenter)
                )


            }
        }
    }
}

@Composable
fun TopBarForPatientCardScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = modifier
                .clickable {
                }
        )
    }
}

@Composable
fun ProfileForPatientCard(
    userName: String,
    diagnosisString: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        RoundImage(
            image = painterResource(id = R.drawable.avatar),
            modifier = Modifier
                .size(48.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column() {
            Text(
                text = userName,
                style = MaterialTheme.typography.subtitle2,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = diagnosisString,
                style = MaterialTheme.typography.body2,
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.Black,
            modifier = modifier.size(20.dp)
        )


    }

}

@Composable
fun AnamnesisCell(
    lastAnamnesisDiagnos: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = Gray30.copy(alpha = 0.35f),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 11.dp
                )
        ) {

            Text(
                text = stringResource(R.string.anamnesis),
                style = MaterialTheme.typography.body2,
            )

            Spacer(modifier = Modifier.height(4.dp))


            Text(
                text = lastAnamnesisDiagnos,
                style = MaterialTheme.typography.button,
                fontSize = 15.sp
            )
        }
    }
}


@Composable
fun TabViewForPatientCardScreen(
    modifier: Modifier = Modifier,
    TextOfTab: List<TextOfTabData>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inactiveColor = Gray30
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Red,
        modifier = modifier.fillMaxWidth()
    ) {
        TextOfTab.forEachIndexed { index, item ->
            Tab(selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)

                }
            ) {
                Text(
                    text = item.text,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }

        }

    }
}

@Composable
fun IndicatorsScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 70.dp, top = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        BraceletIndicatorCell()
        Spacer(modifier = Modifier.height(24.dp))
        IndicatorInfromationStat()
        Spacer(modifier = Modifier.height(24.dp))
        DailyReportInfromation()


    }
}


@Composable
fun BraceletIndicatorCell(
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
            .padding(horizontal = 24.dp, vertical = 27.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_applewatch),
            contentDescription = "",
            tint = Color.Black,
            modifier = modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))


        Text(
            text = stringResource(R.string.bracelet_indicator),
            style = MaterialTheme.typography.subtitle2
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

@Composable
fun IndicatorInfromationStat(
    modifier: Modifier = Modifier
) {

    val configuration = LocalConfiguration.current
    val screenWidth = (configuration.screenWidthDp.dp / 2) - 25.dp


    Column(modifier = modifier.fillMaxWidth()) {

        Text(
            text = stringResource(R.string.patient_indicator),
            style = MaterialTheme.typography.subtitle2,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            CardItemForPatientCard(
                title = stringResource(R.string.glucose_before_meal),
                subtitle = stringResource(R.string.millimoles_per_liter),
                textValue = "7.5",
                dateText = "15 Октября 15:00",
                modifier = modifier.width(screenWidth)
            )

            CardItemForPatientCard(
                title = stringResource(R.string.glucose_after_meal),
                subtitle = stringResource(R.string.millimoles_per_liter),
                textValue = "8.0",
                dateText = "15 Октября 15:00",
                modifier = modifier.width(screenWidth)
            )
        }

        Spacer(modifier = Modifier.height(13.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            CardItemForPatientCard(
                title = stringResource(R.string.pulse),
                subtitle = stringResource(R.string.pulse_in_minute),
                textValue = "7.5",
                dateText = "15 Октября 15:00",
                modifier = modifier.width(screenWidth)
            )

            CardItemForPatientCard(
                title = stringResource(R.string.arterial_pressure),
                subtitle = stringResource(R.string.mmhg),
                textValue = "8.0",
                dateText = "15 Октября 15:00",
                modifier = modifier.width(screenWidth)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ProgressBarForSteps(stepPercent = 0.5f)

    }
}


@Composable
fun CardItemForPatientCard(
    title: String,
    subtitle: String,
    textValue: String,
    showDate: Boolean = true,
    dateText: String = "15 Октября 15:00",
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .height(160.dp)
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 13.sp
                        )
                    ) {
                        append(title)
                    }

                    append(" $subtitle")
                },
                style = MaterialTheme.typography.button,
                fontSize = 13.sp,
                color = Gray30
            )

            Text(
                text = textValue,
                style = MaterialTheme.typography.caption,
            )

            if (showDate) {
                Text(
                    text = dateText,
                    style = MaterialTheme.typography.button,
                    fontSize = 13.sp,
                    color = Gray30
                )
            }
        }
    }
}

@Composable
fun ProgressBarForSteps(
    modifier: Modifier = Modifier,
    stepValue: Int = 0,
    stepPercent: Float = 0f,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.steps),
                style = MaterialTheme.typography.h5,
                fontSize = 13.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "$stepValue из 10 000",
                style = MaterialTheme.typography.subtitle2,
                fontSize = 22.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))


            LinearProgressIndicator(
                progress = stepPercent,
                color = Color.Black,
                modifier = modifier
                    .fillMaxWidth()
                    .height(height = 6.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.goal_for_today),
                style = MaterialTheme.typography.h5,
                fontSize = 13.sp,
                color = Gray30
            )
        }
    }
}


@Composable
fun DailyReportInfromation(
    modifier: Modifier = Modifier
) {

    val configuration = LocalConfiguration.current
    val screenWidth = (configuration.screenWidthDp.dp / 2) - 25.dp


    Column(modifier = modifier.fillMaxWidth()) {

        Text(
            text = stringResource(R.string.daily_reports),
            style = MaterialTheme.typography.subtitle2,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            CardItemWithIconForPatientCard(
                title = stringResource(R.string.well_being),
                icon = Icons.Filled.ThumbUp,
                textValue = "Отлично",
                dateText = "15 Октября 15:00",
                modifier = modifier.width(screenWidth).height(240.dp)
            )


            Column(

            ) {
                CardItemForPatientCard(
                    title = stringResource(R.string.fulfillment_prescription),
                    subtitle = "",
                    textValue = "70%",
                    showDate = false,
                    modifier = modifier.width(screenWidth).height(112.dp)
                )

                Spacer(modifier = Modifier.height(13.dp))

                CardItemForPatientCard(
                    title = stringResource(R.string.weight),
                    subtitle = stringResource(R.string.kg),
                    textValue = "75",
                    dateText = "15 Октября 15:00",
                    modifier = modifier.width(screenWidth).height(112.dp)

                )

            }


        }

        Spacer(modifier = Modifier.height(13.dp))

        CriticalCaseCell(month = "сентябрь")

    }

}


@Composable
fun CardItemWithIconForPatientCard(
    title: String,
    icon: ImageVector,
    textValue: String,
    showDate: Boolean = true,
    dateText: String = "15 Октября 15:00",
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .height(160.dp)
                .padding(16.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.button,
                fontSize = 13.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = icon,
                contentDescription = "Back",
                tint = Color.Black,
            )

            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = textValue,
                style = MaterialTheme.typography.caption,
            )

            Spacer(modifier = Modifier.weight(1f))

            if (showDate) {
                Text(
                    text = dateText,
                    style = MaterialTheme.typography.button,
                    fontSize = 13.sp,
                    color = Gray30
                )
            }
        }
    }
}

@Composable
fun CriticalCaseCell(
    month: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ) {

            Text(
                text = stringResource(R.string.critical_case),
                style = MaterialTheme.typography.subtitle2,
            )

            Spacer(modifier = Modifier.height(16.dp))

            CriticalCaseStat(
                text = stringResource(R.string.hypoglycemia),
                textValue = 5
            )

            Spacer(modifier = Modifier.height(8.dp))

            CriticalCaseStat(
                text = stringResource(R.string.hyperglycemia),
                textValue = 4
            )

            Spacer(modifier = Modifier.height(8.dp))

            CriticalCaseStat(
                text = stringResource(R.string.hypertension),
                textValue = 8
            )

            Spacer(modifier = Modifier.height(8.dp))

            CriticalCaseStat(
                text = stringResource(R.string.hypotension),
                textValue = 3
            )

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = Gray30.copy(alpha = 0.19f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 15.dp, vertical = 11.dp)
            ) {
                Text(
                    text = stringResource(R.string.data_for),
                    style = MaterialTheme.typography.body1
                )


                Text(
                    text = month,
                    style = MaterialTheme.typography.body1,
                    color = Gray30
                )
            }

        }
    }
}


@Composable
fun CriticalCaseStat(
    text: String,
    textValue: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            fontSize = 11.sp,
            modifier = Modifier.drawPinkBar((24 * textValue)),
        )

        Text(
            text = "$textValue",
            style = MaterialTheme.typography.h6,
            fontSize = 13.sp,
            color = Gray30
        )
    }

}


fun Modifier.drawPinkBar(width: Int) = this.drawBehind {
    val colorOfLine = Color(250, 218, 221)

    drawRect(
        color = colorOfLine,
        size = Size(
            width = width.dp.toPx(),
            height = 24.dp.toPx()
        )
    )
}