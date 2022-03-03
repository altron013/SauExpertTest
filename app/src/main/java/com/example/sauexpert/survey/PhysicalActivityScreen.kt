package com.example.sauexpert.survey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.signup_patient.AuthTextFiled
import com.example.sauexpert.ui.theme.Gray50
import com.example.sauexpert.ui.theme.GrayF0F
import com.example.sauexpert.widgets.compose.MainButton

@Composable
fun PhysicalActivityScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions
    val field = remember { mutableStateOf(TextFieldValue()) }
    val fieldState = remember { mutableStateOf(false) }
    val intensivePhysicalActivity = listOf(
        Question(
            "Сколько обычно длится интенсивная физическая нагрузка у пациента? ",
            listOf(
                "до 10 минут",
                "10-20 минут",
                "20-40 минут",
                "40-60 минут",
                "1 час и более"
            )
        ),
    )
    val nonintensivePhysicalActivity = listOf(
        Question(
            "Какова обычная продолжительность неинтенсивной физической нагрузки в течение дня? ",
            listOf(
                "до 20 минут",
                "20-40 минут",
                "40-60 минут",
                "1,5 час и более"
            )
        ),
    )
    val activity = listOf(
        Question(
            "Сколько времени ходит пешком в течение дня? ",
            listOf(
                "до 20 минут",
                "20-40 минут",
                "40-60 минут",
                "1,5 час и более"
            )
        ),
        Question(
            "Сколько времени проводит в сидячем положении?",
            listOf(
                "8 ч и более ",
                "7-8 ч ",
                "6-7 ч ",
                "4-5 ч",
                "3-4 ч",
                "3-1 ч",
                "менее одного часа"
            )
        )
    )
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .verticalScroll(rememberScrollState())
    ) {
        SurveyHeader(
            stringResource(id = R.string.physical_activity),
            dimensions = dimensions
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Column(
            Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.complete_physical_activity_survey),
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.padding(12.dp))
            ActivityOptions(
                optionText = stringResource(id = R.string.intensive_exercise_stress),
                optionQuestion = "Сколько раз в неделю занимается интенсивной физической нагрузкой? ",
                activityList = intensivePhysicalActivity,
                field = field,
                fieldState = fieldState
            )
            ActivityOptions(
                optionText = stringResource(id = R.string.nonintensive_exercise_stress),
                optionQuestion = "Сколько раз в неделю занимается интенсивной физической нагрузкой? ",
                activityList = nonintensivePhysicalActivity,
                field = field,
                fieldState = fieldState
            )
            ActivityOptions(
                optionText = stringResource(id = R.string.activity),
                optionQuestion = "Сколько дней в неделю ходит пешком?? ",
                activityList = activity,
                field = field,
                fieldState = fieldState
            )
            MainButton(
                onClick = { /*TODO*/ },
                enableState = true,
                text = stringResource(id = R.string.proceed)
            )
            Spacer(modifier = Modifier.padding(9.dp))
            Text(
                text = stringResource(
                    id = R.string.you_complete_physical_activity_start_hads
                ),
                fontSize = 13.sp,
                color = Gray50
            )

        }
    }
}

@Composable
fun ActivityOptions(
    optionText: String,
    optionQuestion: String,
    field: MutableState<TextFieldValue>,
    fieldState: MutableState<Boolean>,
    activityList: List<Question>
) {
    Text(
        text = optionText,
        fontSize = 20.sp,
        color = Gray50
    )
    Spacer(modifier = Modifier.padding(6.dp))
    Text(
        text = optionQuestion,
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.padding(6.dp))
    AuthTextFiled(
        name = field, ErrorState = fieldState, placeholder = "Введите значение"
    )
    Spacer(modifier = Modifier.padding(6.dp))
    activityList.forEach { text ->
        SurveyGroup(title = text.question, radioOptions = text.answers)
    }
    Spacer(modifier = Modifier.padding(6.dp))
}


@Composable
fun SurveyGroup(
    radioOptions: List<String> = listOf(),
    title: String = "",
): String {
    if (radioOptions.isNotEmpty()) {
        val (selectedOption, onOptionSelected) = remember {
            mutableStateOf(radioOptions[0])
        }
        Card(
            backgroundColor = GrayF0F,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
        ) {
            Column(
                Modifier.padding(10.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.padding(6.dp))
                radioOptions.forEach { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = (item == selectedOption),
                            modifier = Modifier.padding(all = Dp(value = 6F)),
                            onClick = { onOptionSelected(item) }
                        )
                        val annotatedString = buildAnnotatedString {
                            append("  $item  ")
                        }
                        ClickableText(
                            text = annotatedString,
                            onClick = {
                                onOptionSelected(item)
                            }
                        )
                    }
                }
            }
        }
        return selectedOption
    } else {
        return ""
    }
}
