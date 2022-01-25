package com.example.sauexpert.diagnosis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.*
import com.example.sauexpert.widgets.compose.SpacingVertical

@Composable
fun EmptyDiagnosis() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.inspection),
            contentDescription = "",
            alignment = Alignment.TopCenter,
            modifier = Modifier.size(width = 145.dp, height = 185.dp)
        )

        Text(
            text = "Начните первый осмотр",
            style = SauExpertTypography.h6,
            color = BlackAccent
        )

        Text(
            text = "Осмотрите пациента,\n" +
                    "чтобы поставить ему диагноз",
            fontSize = 15.sp,
            modifier = Modifier.padding(
                start = 32.dp,
                top = 12.dp,
                end = 32.dp,
                bottom = 24.dp
            ),
            textAlign = TextAlign.Center,
            color = SystemGray
        )

        Button(
            modifier = Modifier
                .height(height = 50.dp),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(horizontal = 40.dp, vertical = 14.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_play_fill),
                contentDescription = ""
            )

            Text(
                text = "Начать осмотр",
                fontWeight = FontWeight.W500,
                letterSpacing = 0.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 14.dp)
            )
        }
    }
}

@Composable
fun DiagnosisContent() {
    Column() {
        Text(
            text = "ОСНОВНОЙ",
            style = SauExpertTypography.body2,
            modifier = Modifier
                .padding(start = 16.dp, top = 24.dp)
                .align(Alignment.Start),
            color = SystemDark
        )
        DiagnosisCard()
        Text(
            text = "СОПУТСТВУЮЩИЙ",
            style = SauExpertTypography.body2,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.Start),
            color = SystemGray
        )
        DiagnosisCard()
        Row(
            modifier = Modifier
                .padding(top = 40.dp, bottom = 10.dp)
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .background(color = Color.Transparent)
                .clickable { /* TODO(Open bottom sheet to create new diagnosis) */ },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_plus_circle),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))
            Text(text = "Новый диагноз", color = Red435B, style = SauExpertTypography.body1)
        }
    }

}

@Composable
fun DiagnosisCard() {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
        content = {
            Column(modifier = Modifier.padding(all = 24.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "E11.9 Сахарный диабет",
                        style = SauExpertTypography.body1,
                        color = BlackAccent
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = ""
                    )
                }

                Text(text = "с 13 Октября 2016", fontSize = 13.sp, color = SystemGray2)
            }
        })
}

@Composable
fun DiagnosisFill() {

    Column(
        horizontalAlignment = Alignment.Start
    ) {

        HeaderText(text = "Основной диагноз")

        SubHeaderText(text = "ДИАГНОЗ")

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 30.dp)
        ) {
            Text(
                text = "Выберите диагноз из МКБ-10",
                style = SauExpertTypography.body1,
                color = SystemGray
            )

            Image(
                painter = painterResource(id = R.drawable.ic_chevron_down),
                contentDescription = "",
            )
        }

        SubHeaderText(text = "ДАТА НАЧАЛА")

        DiagnosisDateWithPadding()

        SubHeaderText(text = "КОММЕНТАРИЙ")
        DescriptionTextField(placeHolderText = "Описание")
    }
}

@Composable
fun DiagnosisCriticalCase() {
    Column {
        HeaderText(text = "Критический случай")

        SwitchWithText(text = "Критический случай")

        DescriptionTextField(placeHolderText = "Опишите случай")

        SubHeaderText(text = "КОГДА ПРОИЗОШЛО")

        DiagnosisDateWithPadding()
    }
}

@Composable
fun DiagnosisMore() {

    Column(horizontalAlignment = Alignment.Start) {

        HeaderText(text = "Ещё")

        SwitchWithText(text = "Базовая терапия")

        DescriptionTextField(placeHolderText = "Описание")

        SwitchWithText(text = "Использование инсулина")

        DescriptionTextField(placeHolderText = "Описание")
    }

}

@Composable
fun SwitchWithText(text: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 10.dp)
    ) {
        Text(
            text = text,
            style = SauExpertTypography.body1,
            color = Color.Black
        )
        Switch(checked = true, onCheckedChange = { /* TODO(Add onCheckedChange behaviour)*/ })
    }
}


@Composable
fun DescriptionTextField(placeHolderText: String) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = textState.value, onValueChange = { value ->
            textState.value = value
        },
        placeholder = {
            Text(
                text = placeHolderText,
                style = SauExpertTypography.body1,
                fontSize = 17.sp,
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .wrapContentSize(align = Alignment.CenterStart)
                    .padding(0.dp)

            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.DarkGray,
            cursorColor = DarkGray,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    )
}

@Composable
fun DiagnosisDateWithPadding() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 30.dp, top = 20.dp, bottom = 30.dp)
    ) {
        Text(text = "25.11.2020", style = SauExpertTypography.body1, color = BlackAccent)

        Image(
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = "",
            modifier = Modifier.padding(start = 35.dp)
        )
    }
}

@Composable
fun HeaderText(text: String) {
    Text(
        text = text,
        color = BlackAccent,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        modifier = Modifier.padding(all = 16.dp)
    )
}

@Composable
fun SubHeaderText(text: String) {
    Text(
        text = text,
        style = SauExpertTypography.body2,
        modifier = Modifier
            .padding(start = 16.dp),
        color = DarkGray
    )
}

@Preview(showBackground = true)
@Composable
fun DiagnosisPreview() {
    SauExpertTheme {
        DiagnosisMore()
    }
}

