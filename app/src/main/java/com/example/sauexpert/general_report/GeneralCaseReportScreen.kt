package com.example.sauexpert.general_report

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.bracelet_indicator.ProgressBarForSleep
import com.example.sauexpert.patient_card_screen.BraceletIndicatorCell
import com.example.sauexpert.patient_card_screen.ProgressBarForSteps
import com.example.sauexpert.ui.theme.Gray30

@Composable
fun GeneralCaseReportScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        BraceletIndicatorCell(
            text = stringResource(R.string.risk_factor),
            backgroundColor = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))
        IndicatorInfromationForMonthSection()

    }

}


@Composable
fun IndicatorInfromationForMonthSection(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = (configuration.screenWidthDp.dp / 2) - 25.dp


    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Text(
            text = stringResource(R.string.patient_indicator_for_month),
            style = MaterialTheme.typography.subtitle2,
        )

        Spacer(modifier = Modifier.height(12.dp))

        CardItemForGeneralCase(
            title = stringResource(R.string.blood_glucose),
            measurementValue = 33,
            caseValue = 15

        )

        Spacer(modifier = Modifier.height(13.dp))

        CardItemForGeneralCase(
            title = stringResource(R.string.blood_pressure_pulse),
            measurementValue = 40,
            caseValue = 12

        )

        Spacer(modifier = Modifier.height(13.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            CardItemForGeneralCase(
                title = stringResource(R.string.fulfillment_prescription),
                text = "70%",
                modifier = modifier.width(screenWidth),
            )

            CardItemForGeneralCase(
                title = stringResource(R.string.well_being),
                icon = Icons.Filled.ThumbUp,
                text = "Отлично",
                modifier = modifier.width(screenWidth),
            )
        }

        Spacer(modifier = Modifier.height(13.dp))

        CardItemWithValueForGeneralCase(
            title = stringResource(R.string.weight),
            subtitle = stringResource(R.string.kg),
            text = "75",
            textValue = "+2.3",
        )

        Spacer(modifier = Modifier.height(13.dp))

        CardProgressBarForSleep(
            deepSleepPercent = 45,
            lightSleepPercent = 30,
            remSleepPercent = 25
        )


        Spacer(modifier = Modifier.height(13.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            CardItemForGeneralCase(
                title = stringResource(R.string.hrv),
                subtitle = stringResource(R.string.milliseconds),
                text = "80",
                modifier = modifier.width(screenWidth),
            )

            CardItemForGeneralCase(
                title = stringResource(R.string.sp02),
                subtitle = stringResource(R.string.oxygen),
                text = "96",
                modifier = modifier.width(screenWidth),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        ProgressBarForSteps(stepPercent = 0.5f, stepValue = 2000, goalStepValue = 4000)

    }
}


@Composable
fun CardItemForGeneralCase(
    title: String,
    measurementValue: Int,
    caseValue: Int,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {

            }
    ) {
        Column(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.button,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 13.sp
                        )
                    ) {
                        append("$measurementValue ")
                    }

                    append(stringResource(R.string.measurement).toLowerCase(Locale.current))
                },
                style = MaterialTheme.typography.button,
                fontSize = 13.sp,
                color = Gray30
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 13.sp
                        )
                    ) {
                        append("$caseValue ${stringResource(R.string.cases).toLowerCase(Locale.current)} ")
                    }

                    append(stringResource(R.string.deviations_from_general).toLowerCase(Locale.current))
                },
                style = MaterialTheme.typography.button,
                fontSize = 13.sp,
                color = Gray30
            )

        }
    }
}


@Composable
fun CardItemForGeneralCase(
    title: String,
    subtitle: String? = null,
    icon: ImageVector? = null,
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {

            }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .height(120.dp)
                .padding(16.dp)
        ) {

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.button,
                    fontSize = 13.sp
                )

                subtitle?.let {
                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = it,
                        style = MaterialTheme.typography.button,
                        fontSize = 13.sp,
                        color = Gray30
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = Color.Black,
                    )

                    Spacer(modifier = Modifier.width(13.dp))
                }


                Text(
                    text = text,
                    style = MaterialTheme.typography.caption,
                    fontSize = if (icon == null) 22.sp else 13.sp
                )

            }
        }
    }
}


@Composable
fun CardItemWithValueForGeneralCase(
    title: String,
    subtitle: String,
    text: String,
    textValue: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {

            }
    ) {
        Column(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth()
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
                        append("$title ")
                    }

                    append(subtitle)
                },
                style = MaterialTheme.typography.button,
                fontSize = 13.sp,
                color = Gray30
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {

                Text(
                    text = text,
                    style = MaterialTheme.typography.caption,
                    fontSize = 22.sp
                )

                Text(
                    text = textValue,
                    style = MaterialTheme.typography.button,
                    fontSize = 13.sp,
                    color = Gray30
                )

            }

        }
    }
}


@Composable
fun CardProgressBarForSleep(
    deepSleepPercent: Int,
    lightSleepPercent: Int,
    remSleepPercent: Int,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {

            }
    ) {
        Column(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth()
        ) {

            Text(
                text = stringResource(R.string.sleep),
                style = MaterialTheme.typography.button,
                fontSize = 13.sp,
                modifier = modifier.padding(16.dp)
            )

            ProgressBarForSleep(
                deepSleepPercent = deepSleepPercent,
                lightSleepPercent = lightSleepPercent,
                remSleepPercent = remSleepPercent,
            )

        }
    }
}