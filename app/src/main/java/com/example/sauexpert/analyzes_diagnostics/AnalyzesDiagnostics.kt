package com.example.sauexpert.analyzes_diagnostics

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.analysis.ButtonWithIcon
import com.example.sauexpert.ui.theme.*
import com.example.sauexpert.widgets.compose.MainButton

@Composable
fun EmptyAnalysis() {


    Column(modifier = Modifier.fillMaxWidth()) {

        val textState = remember { mutableStateOf(TextFieldValue("")) }

        AnalysisNameInput(textState)

        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = "",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Фото или скан анализа",
            style = SauExpertTypography.body1,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            color = SystemGray
        )

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun FilledAnalysis() {

    val textState = remember { mutableStateOf(TextFieldValue("Общий анализ крови")) }

    Column(modifier = Modifier.fillMaxWidth()) {
        AnalysisNameInput(state = textState)

        Spacer(modifier = Modifier.height(25.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Фото или скан анализа",
                        color = SystemGray,
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .align(alignment = Alignment.CenterVertically)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = "",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = Separator
                )

                Box(contentAlignment = Alignment.Center) {
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
                            .padding(start = 35.dp, bottom = 6.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "",
                        modifier = Modifier
                            .align(alignment = Alignment.TopEnd)
                            .clickable { /*TODO()*/ }
                    )
                }
            }

        }
    }
}

@Composable
fun AnalyzesDiagnosticsContent() {

    var tabIndex by remember { mutableStateOf(0) }
    val titles = listOf("Загрузить анализ", "Назначить новый")

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Анализы/Диагностика",
            style = SauExpertTypography.h3,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo_light_1),
                    contentDescription = "",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Zhanna Akhmetova",
                    style = SauExpertTypography.body1,
                    color = BlackAccent
                )
            }

            CircularProgressIndicator(
                progress = 0.4f,
                color = Green15B83D,
                strokeWidth = 2.dp,
                modifier = Modifier.size(24.dp)
            )
        }

        TabRow(selectedTabIndex = tabIndex, backgroundColor = Color.Transparent,
            indicator = {
                TabRowDefaults.Indicator(
                    color = Color.Transparent,
                    height = 0.dp,
                    modifier = Modifier.tabIndicatorOffset(it[tabIndex])
                )
            }
        ) {
            titles.forEachIndexed { index, title ->
                if (index == tabIndex) {
                    Surface(elevation = 8.dp) {
                        Tab(
                            selected = tabIndex == index,
                            onClick = { tabIndex = index },
                            text = { Text(text = title, color = Color.Black) },
                            modifier = Modifier.border(
                                color = TabBorder,
                                width = 0.5.dp,
                                shape = RoundedCornerShape(8.dp)
                            )
                        )
                    }
                } else {
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = title, color = Color.Black) },
                    )
                }
            }
        }

        FilledAnalysis()

        Spacer(modifier = Modifier.height(35.dp))

        ButtonWithIcon(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(start = 16.dp),
            backgroundColor = Color.Transparent,
            text = "Добавить анализ",
            contentColor = Red435B
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonWithIcon(
            onClick = { /*TODO*/ },
            backgroundColor = Color.Transparent,
            modifier = Modifier.padding(start = 16.dp),
            text = "Сохранить как шаблон",
            contentColor = BlackAccent,
            iconId = R.drawable.ic_save
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Вам не придётся заполнять одни и те же названия анализов при осмотре других пациентов",
            modifier = Modifier.padding(start = 16.dp, end = 35.dp),
            fontSize = 13.sp,
            color = Gray50,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(48.dp))

        MainButton(
            text = "Продолжить",
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Вы завершаете добавление анализов и перейдёте к анкете предиабета. ",
            modifier = Modifier.padding(start = 16.dp, end = 60.dp),
            fontSize = 13.sp,
            color = Gray50,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun AnalysisNameInput(state: MutableState<TextFieldValue>) {

    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value

        },
        textStyle = TextStyle(color = BlackAccent, fontSize = 17.sp),
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = BlackAccent,
            cursorColor = Color.DarkGray,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = "Название анализа",
                fontSize = 17.sp,
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .wrapContentSize(align = Alignment.CenterStart)
                    .padding(0.dp)

            )
        },
        modifier = Modifier
            .height(55.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun ModalBottomSheet() {
    Column {
        Spacer(modifier = Modifier.height(9.dp))
        Image(painter = painterResource(id = R.drawable.ic_line), contentDescription = "", modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "Сохранить набор анализов",
            style = SauExpertTypography.caption,
            modifier = Modifier.padding(start = 16.dp),
            color = BlackAccent
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Сохраните набор анализов и Вам не придётся каждый раз заполнять одни и те же названия анализов для других пациентов",
            style = SauExpertTypography.body1,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 17.dp),
        )
        Spacer(modifier = Modifier.height(24.dp))
        MainButton(
            onClick = { /*TODO*/ },
            enableState = true,
            text = "Сохранить",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Закрыть",
            color = Red435B,
            style = SauExpertTypography.body1,
            fontWeight = FontWeight.W600,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun Pre() {
    SauExpertTheme {
        ModalBottomSheet()
    }
}