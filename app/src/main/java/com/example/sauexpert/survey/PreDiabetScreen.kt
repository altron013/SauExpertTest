package com.example.sauexpert.survey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.profile.ProfileForInspection
import com.example.sauexpert.signup_patient.AuthTextFiled
import com.example.sauexpert.ui.theme.Gray50
import com.example.sauexpert.widgets.compose.MainButton


data class Question(
    val question: String,
    val answers: List<String>
)

@Composable
fun PreDiabetScreen() {
    val field = remember { mutableStateOf(TextFieldValue()) }
    val fieldState = remember { mutableStateOf(false) }
    val prediabetSurveyQuestions = listOf(
        Question("Окружность талии", listOf("< 80 см", "80-88 см", "> 88 см")),
        Question(
            "Как часто ест овощи, фрукты или ягоды?",
            listOf("Каждый день", "Не каждый день")
        ),
        Question(
            "Был ли у родственников сахарный диабет 1 или 2 типа?",
            listOf("Нет", "Да: дедушка/бабушка, тетя/дядя/двоюродные братья/сестры ")
        ),
        Question(
            "Делает ли пациент физические упражнения по 30 минут каждый день или 3 часа в течение недели? ",
            listOf("Каждый день", "Не каждый день")
        ),
        Question(
            "Принимал когда-либо регулярно лекарства для снижения артериального давления? ",
            listOf("Да", "Нет")
        ),
        Question(
            "Обнаруживали уровень глюкозы в крови выше нормы (во время диспансеризации, проф. осмотра, во время болезни или беременности)? ",
            listOf("Да", "Нет")
        )
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .verticalScroll(rememberScrollState())
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = null,
            modifier = Modifier.padding(top = 26.dp, start = 16.dp)
        )
        Text(
            text = stringResource(id = R.string.prediabet_questionnaire),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 26.dp, start = 16.dp, bottom = 26.dp)
        )
        ProfileForInspection(content = "Zhanna Ahmetova", text = 0.3f)
        Spacer(modifier = Modifier.padding(10.dp))
        Column(
            Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.find_out_patients_diabet_risk),
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.padding(12.dp))
            AuthTextFiled(
                name = field, ErrorState = fieldState, placeholder = stringResource(
                    id = R.string.your_age
                )
            )
            Spacer(modifier = Modifier.padding(12.dp))
            AuthTextFiled(
                name = field, ErrorState = fieldState, placeholder = stringResource(
                    id = R.string.gender
                )
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                stringResource(
                    id = R.string.body_mass_index
                ),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Row() {
                AuthTextFiled(
                    name = field,
                    ErrorState = fieldState,
                    placeholder = stringResource(
                        id = R.string.height_cm
                    ),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                AuthTextFiled(
                    name = field,
                    ErrorState = fieldState,
                    placeholder = stringResource(
                        id = R.string.weight_kg
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.padding(12.dp))
            AuthTextFiled(
                name = field, ErrorState = fieldState, placeholder = stringResource(
                    id = R.string.body_mass_index
                )
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = stringResource(
                    id = R.string.data_entered_by_system
                ),
                fontSize = 12.sp,
                color = Gray50
            )
            Spacer(modifier = Modifier.padding(7.dp))
            prediabetSurveyQuestions.forEach { text ->
                SurveyGroup(title = text.question, radioOptions = text.answers)
            }
            Spacer(modifier = Modifier.padding(9.dp))
            MainButton(
                onClick = { /*TODO*/ },
                enableState = true,
                text = stringResource(id = R.string.proceed)
            )
            Spacer(modifier = Modifier.padding(9.dp))
            Text(
                text = stringResource(
                    id = R.string.you_complete_the_prediabetes_questionnaire
                ),
                fontSize = 13.sp,
                color = Gray50
            )
        }
    }
}
//
//@Composable
//fun SimpleRadioButtonComponent(surveyQuestions: List<Question>) {
//    val (selectedOption, onOptionSelected) = remember { mutableStateOf("к") }
//    surveyQuestions.forEach { text ->
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 6.dp, bottom = 6.dp),
//            backgroundColor = SurfaceF9,
//            shape = RoundedCornerShape(12.dp)
//        ) {
//            Column {
//                Column(
//                    Modifier
//                        .fillMaxWidth()
//                        .selectable(
//                            selected = (text.question == selectedOption),
//                            onClick = { onOptionSelected(text.question) }
//                        )
//                        .padding(16.dp)
//                ) {
//                    Text(text = text.question, fontSize = 17.sp, fontWeight = FontWeight.Bold)
//                    Spacer(modifier = Modifier.padding(4.dp))
//                    text.answers.forEach {
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            RadioButton(
//                                selected = (it == selectedOption),
//                                modifier = Modifier.padding(all = Dp(value = 6F)),
//                                onClick = {
//                                    onOptionSelected(it)
//                                }
//                            )
//                            Text(
//                                text = it,
//                                modifier = Modifier.padding(start = 16.dp)
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
