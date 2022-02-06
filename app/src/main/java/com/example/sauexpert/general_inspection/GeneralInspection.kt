package com.example.sauexpert.general_inspection

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.diagnosis.SubHeaderText
import com.example.sauexpert.my_patients.RoundedCheckView
import com.example.sauexpert.ui.theme.*
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.Toolbars.MainToolbar

@Composable
fun GeneralInspection() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "",
                        maxLines = 1,
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 65.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {  }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_back_blue),
                            contentDescription = null,
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.onPrimary,
                elevation = 0.dp
            )
        }
    ) {
        val progress = remember { mutableStateOf(0) }
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
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
                        painter = painterResource(id = R.drawable.avatar),
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
                    progress = progress.value.div(10).toFloat(),
                    color = Green15B83D,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(24.dp)
                )
            }
            InspectionChange(progress = progress)
            Spacer(modifier = Modifier.height(24.dp))

            if(progress.value < 8) {
                Text(
                    text = "Далее",
                    style = SauExpertTypography.body1,
                    color = Red435B,
                    modifier = Modifier
                        .clickable { progress.value += 1
                        }
                        .align(alignment = Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun InspectionChange(progress: MutableState<Int>) {
    when (progress.value) {
        0 -> InspectionFirst()
        
        1 -> InspectionSecond()

        2 -> InspectionThird()

        3 -> InspectionFourth()

        4 -> InspectionFifth()

        5 -> InspectionFiveOne()
        
        6 -> InspectionSixth()

        7 -> InspectionSeventh()

        8 -> InspectionEighth()
    }
}

@Composable
fun InspectionFirst() {
//    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Заполните данные пациента",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(27.dp))
        Text(
            text = "Жалобы пациента",
            style = SauExpertTypography.body1,
            color = SystemGray,
            modifier = Modifier.padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(38.dp))
        Text(
            text = "Анамнез заболевания",
            style = SauExpertTypography.body1,
            color = SystemGray,
            modifier = Modifier.padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(38.dp))
        Text(
            text = "Анамнез жизни",
            style = SauExpertTypography.body1,
            color = SystemGray,
            modifier = Modifier.padding(start = 30.dp)
        )

//    }
}

@Composable
fun InspectionSecond() {
    val scrollState = rememberScrollState()
//    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Заполните данные пациента",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.W600
        )
        InspectionTextField(
            text = "Холестерин, ммоль/л.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InspectionTextField(
            text = "Глюкоза, ммоль/л.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            InspectionTextField(
                text = "Рост, см",
                modifier = Modifier.weight(1f)
            )

            InspectionTextField(
                text = "Вес, кг",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        InspectionTextField(
            text = "Объем талии, см",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InspectionTextField(
            text = "Объем бедер, см",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InspectionTextField(
            text = "Объем груди, см",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Индекс массы тела",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(68.dp))
        Text(
            text = "Данные внесены системой",
            style = SauExpertTypography.body2,
            color = SystemGray,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Артериальное давление",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.W600
        )
        InspectionTextField(
            text = "С.А.Д , мм.рт.ст.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InspectionTextField(
            text = "С.А.Д , мм.рт.ст.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Курение",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Пациент курит?",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalRadioGroup()
        Spacer(modifier = Modifier.height(20.dp))
        InspectionTextField(
            text = "Сколько сигарет в день?",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InspectionTextField(
            text = "Индекс курящего человека",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Оценка риска в баллах",
            style = SauExpertTypography.body2,
            color = SystemGray,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InspectionTextField(
            text = "Возраст",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Данные внесены системой",
            style = SauExpertTypography.body2,
            color = SystemGray,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InspectionTextField(
            text = "Расчёт SCORE",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Оценка риска в %",
            style = SauExpertTypography.body2,
            color = SystemGray,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "COVID-19",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Пациент ранее болел COVID-19?",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalRadioGroup()
        Spacer(modifier = Modifier.height(16.dp))
        InspectionTextField(
            text = "Описание",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Пациент вакцинирован от COVID-19?",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalRadioGroup()
        Spacer(modifier = Modifier.height(16.dp))
        InspectionTextField(
            text = "Описание",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        )
//    }
}

@Composable
fun InspectionThird() {
    val scrollState = rememberScrollState()
//    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Состояние здоровья пациента на момент осмотра",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp, end = 30.dp),
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ОБЩЕЕ СОСТОЯНИЕ")
        Spacer(modifier = Modifier.height(8.dp))
        val isVisibleCondition = remember { mutableStateOf(false) }
        DropDownSelection(
            list = listOf("Удовлетворительное", "Средней тяжести", "Тяжелое"),
            condition = listOf("Тяжелое"),
            isVisible = isVisibleCondition
        )
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionVisibility(isVisible = isVisibleCondition.value)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "СОЗНАНИЕ")
        val isVisibleСonsciousness = remember { mutableStateOf(false) }
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(
            list = listOf("Ясное", "Другое"),
            condition = listOf("Другое"),
            isVisible = isVisibleСonsciousness
        )
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionVisibility(isVisible = isVisibleСonsciousness.value)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "КОНСТИТУЦИЯ")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(
            list = listOf("Норма", "Астеническая", "Гипостеническая")
        )
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ЭМОЦИОНАЛЬНЫЙ СТАТУС")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(
            list = listOf("Нормальный", "Лабильный")
        )
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "СОН")
        Spacer(modifier = Modifier.height(8.dp))
        val isVisibleSleep = remember { mutableStateOf(false) }
        DropDownSelection(
            list = listOf("Не нарушен", "Нарушен"),
            condition = listOf("Нарушен"),
            isVisible = isVisibleSleep
        )
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionVisibility(isVisible = isVisibleSleep.value)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "АППЕТИТ")
        Spacer(modifier = Modifier.height(8.dp))
        val isVisibleAppetite = remember { mutableStateOf(false) }
        DropDownSelection(
            list = listOf("Нормальный", "Повышен", "Снижен"),
            condition = listOf("Повышен", "Снижен"),
            isVisible = isVisibleAppetite
        )
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionVisibility(isVisible = isVisibleAppetite.value)
//    }
}

@Composable
fun InspectionFourth() {
    val scrollState = rememberScrollState()
//    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Кожные покровы",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp, end = 30.dp),
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(20.dp))
        InspectionTextField(text = "Температура, °С", modifier = Modifier.padding(start = 30.dp))
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ВИДИМЫЕ СЛИЗИСТЫЕ")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(list = listOf("Сухие, чистые", "Другие"))
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ЦВЕТ КОЖИ")
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ТУГОР")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(list = listOf("В норме", "Снижен"))
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ВЛАЖНОСТЬ")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(list = listOf("В норме", "Гипергидроз", "Сухая"))
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ЧУВСТВИТЕЛЬНОСТЬ")
        Spacer(modifier = Modifier.height(8.dp))
        val isVisibleSensitivity = remember { mutableStateOf(false) }
        DropDownSelection(
            list = listOf("В норме", "Снижена"),
            condition = listOf("Снижена"),
            isVisible = isVisibleSensitivity
        )
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionVisibility(isVisible = isVisibleSensitivity.value)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ЖИРОВАЯ КЛЕТЧАТКА")
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ЛИМФАТИЧЕСКИЕ УЗЛЫ")
        val isVisibleLimfo = remember { mutableStateOf(false) }
        DropDownSelection(
            list = listOf("Не увеличено", "Увеличено"),
            condition = listOf("Увеличено"),
            isVisible = isVisibleLimfo
        )
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionVisibility(isVisible = isVisibleLimfo.value)

//    }
}

@Composable
fun InspectionFifth() {
    val scrollState = rememberScrollState()
//    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Мышечная и костно-суставная система ",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp, end = 30.dp),
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "АКТИВНОСТЬ")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(list = listOf("Сохранена", "Ограничена", "Резко ограничена"))
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "КОСТНО-СУСТАВНАЯ СИСТЕМА")
        Spacer(modifier = Modifier.height(8.dp))
        val isVisibleSystem = remember { mutableStateOf(false) }
        DropDownSelection(
            list = listOf("Без деформации", "Другое"),
            condition = listOf("Другое"),
            isVisible = isVisibleSystem
        )
        DescriptionVisibility(isVisible = isVisibleSystem.value)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "МЫШЕЧНАЯ СИСТЕМА")
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ПОЛОВЫЕ ПРИЗНАКИ")
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ЩИТОВИДНАЯ ЖЕЛЕЗА")
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "МОЛОЧНЫЕ/ГРУДНЫЕ ЖЕЛЕЗЫ")
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionVisibility(isVisible = true)
//    }
}

@Composable
fun InspectionFiveOne() {
    val scrollState = rememberScrollState()
//    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Дыхательная система",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp, end = 30.dp),
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Дыхание",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        VerticalRadioGroup(radioOptions = listOf("Грудное", "Брюшное", "Смешанное"))
        Spacer(modifier = Modifier.height(24.dp))
        SubHeaderText(text = "ЧАСТОТА ДЫХАНИЯ")
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Хрипы",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp, end = 30.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        VerticalRadioGroup(radioOptions = listOf("Нет", "Есть"))
        Spacer(modifier = Modifier.height(16.dp))
        SubHeaderText(text = "ХАРАКТЕР, ЛОКАЛИЗАЦИЯ")
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Участие вспомогательной мускулатуры",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp, end = 30.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        VerticalRadioGroup(radioOptions = listOf("Да", "Нет"))
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Кашель",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp, end = 30.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        VerticalRadioGroup(
            radioOptions = listOf(
                "Нет",
                "Есть, непродуктивный",
                "Есть, продуктивный"
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        SubHeaderText(text = "МОКРОТА")
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Аускультативно",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp, end = 30.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        VerticalRadioGroup(radioOptions = listOf("Везикулярное", "Жёсткое", "Ослабление хрипа"))
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(16.dp))
//    }
}

@Composable
fun InspectionSixth() {
    val scrollState = rememberScrollState()
//    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Сердечно-сосудистая система",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp, end = 30.dp),
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "РИТМ")
        DropDownSelection(list = listOf("Правильный", "Неправильный"))
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ОТЕКИ")
        Spacer(modifier = Modifier.height(8.dp))
        val isVisibleEdema = remember { mutableStateOf(false) }
        DropDownSelection(
            list = listOf("Нет", "Отеки нижних конечностей", "Есть"),
            condition = listOf("Отеки нижних конечностей", "Есть"),
            isVisible = isVisibleEdema
        )
        DescriptionVisibility(isVisible = isVisibleEdema.value)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ТОНЫ СЕРДЦА")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(list = listOf("Приглушенные", "Ясные", "Глухие"))
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ШУМЫ СЕРДЦА")
        Spacer(modifier = Modifier.height(8.dp))
        val isVisibleHeart = remember { mutableStateOf(false) }
        DropDownSelection(
            list = listOf("Нет", "Есть"),
            condition = listOf("Есть"),
            isVisible = isVisibleHeart
        )
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionVisibility(isVisible = isVisibleHeart.value)
        Spacer(modifier = Modifier.height(16.dp))
//    }
}

@Composable
fun InspectionSeventh() {
    val scrollState = rememberScrollState()
//    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Желудочно-кишечный тракт",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp, end = 30.dp),
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ЯЗЫК")
        Spacer(modifier = Modifier.height(12.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "влажный")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "чистый")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "сухой")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "язвенные элементы")
        Spacer(modifier = Modifier.height(12.dp))
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ЖИВОТ")
        Spacer(modifier = Modifier.height(8.dp))
        val isVisibleStomach = remember { mutableStateOf(false) }
        DropDownSelection(
            list = listOf("Болезненный", "Безболезненный"),
            condition = listOf("Болезненный", "Безболезненный"),
            isVisible = isVisibleStomach
        )
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionVisibility(isVisible = isVisibleStomach.value)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ПЕЧЕНЬ")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(
            list = listOf("Увеличена", "Не увеличена", "Уменьшена")
        )
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "СТУЛ")
        Spacer(modifier = Modifier.height(12.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "не нарушен")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "запоры")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "диарея")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "дегтеобразный стул")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "другое")
        Spacer(modifier = Modifier.height(12.dp))
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ЦВЕТ")
        Spacer(modifier = Modifier.height(8.dp))
        val isVisibleColor = remember { mutableStateOf(false) }
        DropDownSelection(
            list = listOf("Норма", "Другое"),
            condition = listOf("Другое"),
            isVisible = isVisibleColor
        )
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionVisibility(isVisible = isVisibleColor.value)
//    }
}

@Composable
fun InspectionEighth() {
    val scrollState = rememberScrollState()
//    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Мочевыделительная система",
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp, end = 30.dp),
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "МОЧЕИСПУСКАНИЕ")
        Spacer(modifier = Modifier.height(12.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "свободное")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "болезненное")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "затрудненное")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "безболезненное")
        Spacer(modifier = Modifier.height(12.dp))
        DescriptionVisibility(isVisible = true)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "НЕДЕРЖАНИЕ МОЧИ")
        Spacer(modifier = Modifier.height(12.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "нет")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "есть")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "позывы императивные")
        Spacer(modifier = Modifier.height(20.dp))
        RoundedCheckViewWithText(id = R.drawable.ic_checked_pink, text = "позывы стрессовые")
        Spacer(modifier = Modifier.height(24.dp))
        SubHeaderText(text = "СИМПТОМ ПОКОЛАЧИВАНИЯ")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(list = listOf("Положительный", "Отрицательный"))
        Spacer(modifier = Modifier.height(34.dp))
        MainButton(
            onClick = { /*TODO*/ },
            enableState = true,
            text = "Продолжить",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Вы завершите осмотр и перейдёте к добавлению анализов.",
            modifier = Modifier.padding(start = 16.dp, end = 40.dp),
            fontSize = 13.sp,
            color = Gray50,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(24.dp))
//    }
}

@Composable
fun RoundedCheckViewWithText(id: Int = R.drawable.ic_checkbox_checked, text: String) {
    val isChecked = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Transparent)
                .toggleable(value = isChecked.value, role = Role.Checkbox) {
                    isChecked.value = it
                },
            contentAlignment = Alignment.Center
        ) {
            if (isChecked.value) {
                Image(
                    painter = painterResource(id = id),
                    modifier = Modifier.size(24.dp),
                    contentDescription = ""
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_checkbox_unchecked),
                    modifier = Modifier.size(24.dp),
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, style = SauExpertTypography.body1, color = BlackAccent)
    }

}

@Composable
fun DescriptionVisibility(isVisible: Boolean) {
    if (isVisible) {
        InspectionTextField(
            text = "Описание",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 30.dp)
        )
    }
}

@Composable
fun DropDownSelection(
    list: List<String>,
    condition: List<String> = listOf(),
    isVisible: MutableState<Boolean> = mutableStateOf(false)
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        var chosen by remember { mutableStateOf(list[0]) }

        Text(
            text = chosen,
            style = SauExpertTypography.body1,
            color = BlackAccent,
            modifier = Modifier.padding(start = 16.dp)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.padding(16.dp)
        ) {
            list.forEachIndexed { index, str ->

                DropdownMenuItem(onClick = {
                    isVisible.value = condition.contains(str)
                    expanded = false
                    chosen = str
                }) {
                    Text(text = list[index])
                }
            }
        }
        IconButton(onClick = { expanded = true }) {
            Icon(
                painterResource(R.drawable.ic_chevron_down),
                contentDescription = "Localized description"
            )
        }
    }
}


@Composable
fun VerticalRadioGroup(radioOptions: List<String> = listOf("Да", "Нет")) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    Column(Modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null, // null recommended for accessibility with screenreaders
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioDisabled
                    ),
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}

@Composable
fun HorizontalRadioGroup(radioOptions: List<String> = listOf("Да", "Нет")) {

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    Row(Modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null, // null recommended for accessibility with screenreaders
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioDisabled
                    ),
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun InspectionTextField(text: String, modifier: Modifier = Modifier) {

    var textState by remember {
        mutableStateOf("")
    }

    TextField(
        value = textState,
        onValueChange = { value ->
            textState = value
        },
        textStyle = TextStyle(color = BlackAccent, fontSize = 17.sp),
        singleLine = false,
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
                text = text,
                fontSize = 17.sp,
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .wrapContentSize(align = Alignment.CenterStart)
                    .padding(0.dp)

            )
        },
        modifier = modifier
            .wrapContentHeight()
            .defaultMinSize(minHeight = 55.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    SauExpertTheme {
        GeneralInspection()
    }
}