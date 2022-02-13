package com.example.sauexpert.analysis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.diagnosis.DiagnosisDateWithPadding
import com.example.sauexpert.diagnosis.SubHeaderText
import com.example.sauexpert.ui.theme.*
import com.example.sauexpert.widgets.compose.MainButton

@Composable
fun AnalysisCard() {
    Card(
        backgroundColor = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
        elevation = 0.dp
    ) {
        Column() {
            Spacer(modifier = Modifier.height(24.dp))
            HeaderChevronRight(
                text = "Общий анализ крови",
                modifier = Modifier.padding(start = 24.dp, end = 24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "с 13 Октября 2016",
                fontSize = 13.sp,
                color = SystemGray2,
                modifier = Modifier.padding(start = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .background(
                        Color.Transparent, shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        1.dp, BorderGray, RoundedCornerShape(8.dp)
                    )
                    .wrapContentSize(align = Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.document),
                    contentDescription = "",
                    modifier = Modifier
                        .size(width = 48.dp, height = 56.dp)
                        .padding(all = 8.dp)

                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}

@Composable
fun AnalysisContent() {
    Column {
        AnalysisCard()
        AnalysisCard()
        AnalysisCard()

        Row(
            modifier = Modifier
                .padding(top = 24.dp, bottom = 10.dp)
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .background(color = Color.Transparent)
                .clickable { /* TODO(Open bottom sheet to create new analysis) */ },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_plus_circle_conflict),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))
            Text(
                text = "Новый анализ",
                color = Red435B,
                style = SauExpertTypography.body1
            )
        }
    }
}

@Composable
fun NewAnalysis() {
    Column {
        BottomSheetHeader(title = "Назначить анализ", onClick = {})
        Spacer(modifier = Modifier.height(40.dp))
        Divider(thickness = 0.5.dp, color = SystemGray)
        Spacer(modifier = Modifier.height(16.dp))
        NewAnalysisItem()
        ButtonWithIcon(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(start = 16.dp),
            backgroundColor = Pink20p,
            text = "Новый диагноз",
            contentColor = Red435B
        )
        Spacer(modifier = Modifier.height(40.dp))
        MainButton(
            text = "Назначить анализ",
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Пациент получит уведомление, что ему нужно прикрепить анализы.",
            modifier = Modifier.padding(start = 16.dp, end = 60.dp),
            fontSize = 13.sp,
            color = DarkGray,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(10.dp))

    }

}

@Composable
fun Analysis() {
    Column {
        BottomSheetHeader(title = "Микрореакция", onClick = {})
        AnalysisChosenItem()
        Text(
            text = "Фото или скан анализа",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Divider(thickness = 0.5.dp, color = DarkGray)
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = R.drawable.document),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(20.dp)
                )
                .size(width = 100.dp, height = 66.dp)
                .padding(start = 35.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        MainButton(
            text = "Ок, понятно",
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Composable
fun BottomSheetHeader(title: String, closeText: String = "Закрыть", onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp, start = 8.dp)
                .clickable { onClick() }
                .align(alignment = Alignment.CenterStart)
        ) {

            Text(
                text = closeText,
                style = SauExpertTypography.body1,
                modifier = Modifier.padding(start = 5.dp),
                color = Red435B
            )
        }

        Text(
            text = title,
            style = SauExpertTypography.subtitle2,
            color = BlackAccent,
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(top = 16.dp)
        )
    }
}

@Composable
fun AnalysisChosenItem() {
    Column {
        SubHeaderText(text = "АНАЛИЗ")
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Микрореакция",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        DatePeriod(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
    }
}


@Composable
fun NewAnalysisItem() {

    Column {
        SubHeaderText(text = "АНАЛИЗ")
        Spacer(modifier = Modifier.height(20.dp))
        DropDownSelection(
            text = "Выберите анализ",
            modifier = Modifier.padding(start = 30.dp, end = 30.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        DatePeriod(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
    }
}

@Composable
fun DropDownSelection(text: String, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()

    ) {
        Text(
            text = text,
            style = SauExpertTypography.body1,
            color = SystemGray
        )

        Image(
            painter = painterResource(id = R.drawable.ic_chevron_down),
            contentDescription = "",
        )
    }
}

@Composable
fun DatePeriod(modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier) {
        Column(modifier = Modifier.weight(1f)) {
            SubHeaderText(text = "ДАТА НАЧАЛА")
            DiagnosisDateWithPadding("25.11.2020")
        }
        Column(modifier = Modifier.weight(1f)) {
            SubHeaderText(text = "ДЕЙСТВУЕТ ДО:")
            DiagnosisDateWithPadding("25.11.2020")
        }
    }
}

@Composable()
fun ButtonWithIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    text: String,
    contentColor: Color,
    iconId: Int = R.drawable.ic_plus_circle_conflict
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        modifier = modifier,
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.width(9.dp))

        Text(text = text, style = SauExpertTypography.body1, fontWeight = FontWeight.W600)

    }
}


@Composable
fun HeaderChevronRight(text: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            style = SauExpertTypography.body1,
            color = BlackAccent,
            fontWeight = FontWeight.W600
        )

        Image(
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    SauExpertTheme {
        AnalysisContent()
    }
}
