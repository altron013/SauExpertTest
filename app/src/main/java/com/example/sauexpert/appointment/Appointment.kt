package com.example.sauexpert.appointment

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.analysis.BottomSheetHeader
import com.example.sauexpert.analysis.ButtonWithIcon
import com.example.sauexpert.analysis.DatePeriod
import com.example.sauexpert.analysis.HeaderChevronRight
import com.example.sauexpert.diagnosis.SubHeaderText
import com.example.sauexpert.diagnosis.SwitchWithText
import com.example.sauexpert.general_inspection.DropDownSelection
import com.example.sauexpert.general_inspection.InspectionTextField
import com.example.sauexpert.model.AppointmentCardData
import com.example.sauexpert.ui.theme.*
import com.example.sauexpert.widgets.compose.MainButton
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun AppointmentCard(appointmentCardData: AppointmentCardData) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(24.dp))
        HeaderChevronRight(
            text = appointmentCardData.name,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = appointmentCardData.lastSampleDate,
            fontSize = 13.sp,
            color = SystemGray2,
            style = SauExpertTypography.body2,
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = appointmentCardData.period,
            fontSize = 13.sp,
            color = SystemGray2,
            style = SauExpertTypography.body2,
            fontWeight = FontWeight.W600,
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = appointmentCardData.frequency,
            fontSize = 13.sp,
            color = SystemGray2,
            style = SauExpertTypography.body2,
            modifier = Modifier.padding(start = 24.dp)
        )
        LinearProgressIndicator(
            progress = 0.4f,
            color = BlackAccent,
            backgroundColor = Color.Transparent,
            modifier = Modifier
                .padding(vertical = 24.dp)
                .height(8.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun PlusButton(title: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(top = 24.dp, bottom = 10.dp)
            .wrapContentWidth()
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
            text = title,
            color = Red435B,
            style = SauExpertTypography.body1
        )
    }
}

@Composable
fun NewSample() {
    Column(modifier = Modifier.fillMaxWidth()) {
        BottomSheetHeader(title = "Новый замер", closeText = "Отмена", onClick = {})
        Spacer(modifier = Modifier.height(18.dp))
        Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth(), color = Separator)
        Spacer(modifier = Modifier.height(24.dp))
        SubHeaderText(text = "ТИП ЗАМЕРА")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(list = listOf("Option1", "Option2", "Option3"))
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "РЕГУЛЯРНОСТЬ ЗАМЕРА")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(list = listOf("Option1", "Option2", "Option3"))
        Spacer(modifier = Modifier.height(16.dp))
        SubHeaderText(text = "ВРЕМЯ ЗАМЕРА")
        Spacer(modifier = Modifier.height(8.dp))
        SampleTimeItem(time = "9:00", modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(12.dp))
        ButtonWithIcon(
            onClick = { /*TODO*/ },
            backgroundColor = Pink20p,
            text = "Добавить время",
            contentColor = Red435B,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        DatePeriod()
        Spacer(modifier = Modifier.height(24.dp))
        MainButton(
            onClick = { /*TODO*/ },
            enableState = true,
            text = "Готово",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun SampleWithSlider() {

    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxWidth().verticalScroll(scrollState)) {
        BottomSheetHeader(title = "Замеры глюкозы", closeText = "Отмена", onClick = {})
        Spacer(modifier = Modifier.height(18.dp))
        Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth(), color = Separator)
        Spacer(modifier = Modifier.height(24.dp))
        SubHeaderText(text = "ТИП ЗАМЕРА")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(list = listOf("Option1", "Option2", "Option3"))
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "РЕГУЛЯРНОСТЬ ЗАМЕРА")
        Spacer(modifier = Modifier.height(8.dp))
        DropDownSelection(list = listOf("Option1", "Option2", "Option3"))
        Spacer(modifier = Modifier.height(16.dp))
        SubHeaderText(text = "ВРЕМЯ ЗАМЕРА")
        Spacer(modifier = Modifier.height(8.dp))
        SampleTimeItem(time = "9:00", modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(12.dp))
        ButtonWithIcon(
            onClick = { /*TODO*/ },
            backgroundColor = Pink20p,
            text = "Добавить время",
            contentColor = Red435B,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        DatePeriod()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Настройте норму глюкозы",
            color = BlackAccent,
            style = SauExpertTypography.body1,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Будем присылать уведомления, когда давление будет падать или превышать заданную норму",
            style = SauExpertTypography.body2,
            modifier = Modifier.padding(start = 16.dp),
            color = Gray50,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        val sliderPosition = remember { mutableStateOf(0f..8f) }
        RangeSliderWithLabels(values = sliderPosition.value, valueRange = 0f..8f, finiteEnd = true, showFractions = true )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "3,5 — 4,1",
            style = SauExpertTypography.caption,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "ммоль/литр",
            style = SauExpertTypography.body1,
            color = Gray30,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(48.dp))
        MainButton(
            onClick = { /*TODO*/ },
            enableState = true,
            text = "Готово",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun SampleTimeItem(time: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = time,
            color = BlackAccent,
            style = SauExpertTypography.body1,
            modifier = Modifier.padding(start = 15.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_triple_dot),
            contentDescription = "",
            modifier = Modifier.padding(end = 14.dp)
        )
    }
}

@Composable
fun AssignMedicine() {
    Column {
        BottomSheetHeader(title = "Назначить медикамент", closeText = "Отмена", onClick = {})
        Spacer(modifier = Modifier.height(18.dp))
        Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth(), color = Separator)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "МЕДИКАМЕНТ")
        InspectionTextField(text = "Введите название медикамента")
        val list = listOf(
            "ГЛЮКОФАЖ",
            "АСПИРИН",
            "КАРДИОМАГНИЛ",
            "МЕДИКАМЕНТ1",
            "МЕДИКАМЕНТ2",
            "МЕДИКАМЕНТ3"
        )
        val selectedMedicine: MutableState<String> = mutableStateOf("ГЛЮКОФАЖ")
        ChipGroup(medicine = list,
            selectedMedicine = selectedMedicine.value,
            onSelectedChanged = {
                selectedMedicine.value = it
            })
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ДНИ ПРИЁМА")
        Spacer(modifier = Modifier.height(8.dp))
        val listWeek = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
        val selectedDays = listOf("")
        WeekGroup(weekList = listWeek)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Дозировка",
            color = BlackAccent,
            style = SauExpertTypography.body1,
            fontWeight = FontWeight.W600,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InspectionTextField(text = "Например, 1 таблетка в день")
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ВРЕМЯ ПРИЁМА")
        Spacer(modifier = Modifier.height(8.dp))
        SampleTimeItem(time = "9:00", modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        ButtonWithIcon(
            onClick = { /*TODO*/ },
            backgroundColor = Pink20p,
            text = "Добавить время",
            contentColor = Red435B,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        DatePeriod()
        Spacer(modifier = Modifier.height(24.dp))
        SwitchWithText(
            text = "Отправить рецепт на почту",
            modifier = Modifier.padding(start = 32.dp, end = 30.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        MainButton(
            onClick = { /*TODO*/ },
            enableState = true,
            text = "Готово",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun AssignMedicineWithPressure() {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        BottomSheetHeader(title = "Замеры давления", closeText = "Отмена", onClick = {})
        Spacer(modifier = Modifier.height(18.dp))
        Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth(), color = Separator)
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "МЕДИКАМЕНТ")
        InspectionTextField(text = "Введите название медикамента")
        val list = listOf(
            "ГЛЮКОФАЖ",
            "АСПИРИН",
            "КАРДИОМАГНИЛ",
            "МЕДИКАМЕНТ1",
            "МЕДИКАМЕНТ2",
            "МЕДИКАМЕНТ3"
        )
        val selectedMedicine: MutableState<String> = mutableStateOf("ГЛЮКОФАЖ")
        ChipGroup(medicine = list,
            selectedMedicine = selectedMedicine.value,
            onSelectedChanged = {
                selectedMedicine.value = it
            })
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ДНИ ПРИЁМА")
        Spacer(modifier = Modifier.height(8.dp))
        val listWeek = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
        val selectedDays = listOf("")
        WeekGroup(weekList = listWeek)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Дозировка",
            color = BlackAccent,
            style = SauExpertTypography.body1,
            fontWeight = FontWeight.W600,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InspectionTextField(text = "Например, 1 таблетка в день")
        Spacer(modifier = Modifier.height(20.dp))
        SubHeaderText(text = "ВРЕМЯ ПРИЁМА")
        Spacer(modifier = Modifier.height(8.dp))
        SampleTimeItem(time = "9:00", modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        ButtonWithIcon(
            onClick = { /*TODO*/ },
            backgroundColor = Pink20p,
            text = "Добавить время",
            contentColor = Red435B,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        DatePeriod()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Настройте норму давления",
            color = BlackAccent,
            style = SauExpertTypography.body1,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Будем присылать уведомления, когда давление будет падать или превышать заданную норму",
            style = SauExpertTypography.body2,
            modifier = Modifier.padding(start = 16.dp),
            color = Gray50,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Систолическое давление",
            style = SauExpertTypography.body2,
            modifier = Modifier.padding(start = 16.dp, end = 24.dp),
            color = DarkGray,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        val sliderPosition = remember { mutableStateOf(0f..240f) }
        RangeSliderWithLabels(
            values = sliderPosition.value, valueRange = 0f..240f, finiteEnd = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Диастолическое давление",
            style = SauExpertTypography.body2,
            modifier = Modifier.padding(start = 16.dp, end = 24.dp),
            color = DarkGray,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        val sliderPositionSecond = remember { mutableStateOf(0f..120f) }
        RangeSliderWithLabels(
            values = sliderPositionSecond.value, valueRange = 0f..120f, finiteEnd = true
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "90 — 140 / 60 — 80",
            style = SauExpertTypography.caption,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "мм. рт. ст.",
            style = SauExpertTypography.body1,
            color = Gray30,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(48.dp))
        MainButton(
            onClick = { /*TODO*/ },
            enableState = true,
            text = "Готово",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun NewRecommendation() {
    Column {
        Spacer(modifier = Modifier.height(9.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_line),
            contentDescription = "",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "Новая рекомендация",
            color = BlackAccent,
            style = SauExpertTypography.caption,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Выберите рекомендацию",
            style = SauExpertTypography.body1,
            color = Gray50,
            modifier = Modifier.padding(start = 17.dp, end = 23.dp)
        )
        Spacer(modifier = Modifier.height(28.dp))
        HeaderChevronRight(
            text = "Цель по шагам",
            modifier = Modifier.padding(start = 32.dp, end = 40.dp)
        )
        Spacer(modifier = Modifier.height(46.dp))
        HeaderChevronRight(
            text = "Упражнения",
            modifier = Modifier.padding(start = 32.dp, end = 40.dp)
        )
        Spacer(modifier = Modifier.height(46.dp))
        HeaderChevronRight(
            text = "Питание",
            modifier = Modifier.padding(start = 32.dp, end = 40.dp)
        )
        Spacer(modifier = Modifier.height(46.dp))
        HeaderChevronRight(
            text = "Курение",
            modifier = Modifier.padding(start = 32.dp, end = 40.dp)
        )
        Spacer(modifier = Modifier.height(46.dp))
        HeaderChevronRight(
            text = "Алкоголь",
            modifier = Modifier.padding(start = 32.dp, end = 40.dp)
        )
        Spacer(modifier = Modifier.height(46.dp))
        HeaderChevronRight(
            text = "Дополнительно",
            modifier = Modifier.padding(start = 32.dp, end = 40.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))

    }
}

@Composable
fun NewAppointmentBottomSheet() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Новое назначение",
            color = BlackAccent,
            style = SauExpertTypography.caption,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.W400
        )
        Text(
            text = "Что Вы хотите назначить пациенту?",
            color = Gray50,
            style = SauExpertTypography.body2,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Замеры",
            color = BlackAccent,
            style = SauExpertTypography.body1,
            modifier = Modifier.padding(start = 28.dp, end = 28.dp),

            )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Медикаменты",
            color = BlackAccent,
            style = SauExpertTypography.body1,
            modifier = Modifier.padding(start = 28.dp, end = 28.dp),

            )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Рецепты",
            color = BlackAccent,
            style = SauExpertTypography.body1,
            modifier = Modifier.padding(start = 28.dp, end = 28.dp),

            )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Рекомендации",
            color = BlackAccent,
            style = SauExpertTypography.body1,
            modifier = Modifier.padding(start = 28.dp, end = 28.dp),

            )
    }
}

@Composable
fun GoalSteps() {
    Column(modifier = Modifier.fillMaxWidth()) {
        BottomSheetHeader(title = "Цель по шагам", closeText = "Отмена", onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
        Divider(thickness = 1.dp, color = Separator)
        Spacer(modifier = Modifier.height(24.dp))
        val sliderPosition = remember { mutableStateOf(0f) }
        SliderWithLabel(value = sliderPosition.value, valueRange = 0f..20000f, finiteEnd = true)
        Spacer(modifier = Modifier.height(32.dp))
        MainButton(
            onClick = { /*TODO*/ },
            enableState = true,
            text = "Готово",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun RecommendationBottomSheet(title: String, hint: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            BottomSheetHeader(title = title, closeText = "Отмена", onClick = {})
            Spacer(modifier = Modifier.height(10.dp))
            Divider(thickness = 1.dp, color = Separator)
            Spacer(modifier = Modifier.height(12.dp))
            InspectionTextField(text = hint)
        }
        MainButton(
            onClick = { /*TODO*/ },
            enableState = true,
            text = "Готово",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
        )
    }
}


@Composable
fun WeekGroup(
    weekList: List<String>,
    selectedDays: List<String> = listOf(),
    onSelectedChanged: (String) -> Unit = {},
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .padding(start = 7.dp)
            .horizontalScroll(scrollState)
    ) {
        weekList.forEach { day ->
            WeekDayItem(
                text = day,
                isSelected = selectedDays.contains(day),
                onSelectionChanged = { onSelectedChanged(it) })
        }
    }
}


@Composable
fun WeekDayItem(
    text: String,
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},
) {
    Surface(
        color = if (isSelected) Red435B else Color.White,
        border = if (isSelected) null else BorderStroke(1.dp, Gray30),
        shape = RoundedCornerShape(10.dp),
        elevation = 0.dp,
        modifier = Modifier.padding(start = 9.dp)
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectionChanged(text)
                    })
                .size(width = 47.dp, height = 44.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = if (isSelected) Color.White else BlackAccent,
                style = SauExpertTypography.body1
            )
        }
    }

}

@Composable
fun ChipGroup(
    medicine: List<String>,
    selectedMedicine: String = "",
    onSelectedChanged: (String) -> Unit = {},
) {
    Column(modifier = Modifier.padding(8.dp)) {
        FlowRow(crossAxisSpacing = 8.dp) {
            medicine.forEach { name ->
                Chip(
                    name = name,
                    isSelected = name == selectedMedicine,
                    onSelectionChanged = {
                        onSelectedChanged(it)
                    },
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    name: String = "Chip",
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},

    ) {
    Surface(
        modifier = modifier,
        elevation = 0.dp,
        shape = RoundedCornerShape(17.dp),
        color = if (isSelected) Red435B else Quarternary
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(name)
                }
            )
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.body2,
                color = if (isSelected) Color.White else Red435B,
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 13.dp),
                fontSize = 13.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RangeSliderWithLabels(
    values: ClosedFloatingPointRange<Float>,
    valueRange: ClosedFloatingPointRange<Float>,
    finiteEnd: Boolean,
    labelMinWidth: Dp = 10.dp,
    showFractions: Boolean = false
) {
    val sliderPositions = remember { mutableStateOf(values) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val offsetStart = getSliderOffset(
                value = sliderPositions.value.start,
                valueRange = valueRange,
                boxWidth = maxWidth,
                labelWidth = labelMinWidth
            )

            val offsetEnd = getSliderOffset(
                value = sliderPositions.value.endInclusive,
                valueRange = valueRange,
                boxWidth = maxWidth,
                labelWidth = labelMinWidth
            )

            val startValueText =
                if (!finiteEnd && sliderPositions.value.start >= valueRange.endInclusive)
                    "${if (showFractions) "%.1f".format(sliderPositions.value.start) else sliderPositions.value.start.toInt()} +"
                else (if (showFractions) "%.1f".format(sliderPositions.value.start) else sliderPositions.value.start.toInt())
                    .toString()

            val endValueText =
                if (!finiteEnd && sliderPositions.value.endInclusive >= valueRange.endInclusive)
                    "${if (showFractions) "%.1f".format(sliderPositions.value.endInclusive) else sliderPositions.value.endInclusive.toInt()} +"
                else (if (showFractions) "%.1f".format(sliderPositions.value.endInclusive) else sliderPositions.value.endInclusive.toInt())
                    .toString()
            SliderLabel(
                label = valueRange.start.toInt().toString(),
                minWidth = labelMinWidth,
                modifier = Modifier.alpha(0f)
            )

            if (sliderPositions.value.start > valueRange.start) {
                SliderLabel(
                    label = startValueText,
                    minWidth = labelMinWidth,
                    modifier = Modifier
                        .padding(start = offsetStart / 1.05f),
                    textColor = BlackAccent
                )
            }

            if (sliderPositions.value.endInclusive < valueRange.endInclusive) {
                SliderLabel(
                    label = endValueText,
                    minWidth = labelMinWidth,
                    modifier = Modifier
                        .padding(start = offsetEnd / 1.05f),
                    textColor = BlackAccent
                )
            }

        }

        RangeSlider(
            values = sliderPositions.value,
            onValueChange = {
                sliderPositions.value = it
            },
            valueRange = valueRange,
            colors = SliderDefaults.colors(
                thumbColor = Red435B,
                activeTrackColor = Color.Green,
                inactiveTrackColor = Red435B,
                disabledThumbColor = Red435B
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "0",
                style = SauExpertTypography.body1,
                color = Gray30,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )
            Text(
                text = (valueRange.endInclusive / 2).toInt().toString(),
                style = SauExpertTypography.body1,
                color = Gray30,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = valueRange.endInclusive.toInt().toString(),
                style = SauExpertTypography.body1,
                color = Gray30,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )
        }
    }
}


@Composable
fun SliderWithLabel(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    finiteEnd: Boolean,
    labelMinWidth: Dp = 10.dp
) {
    val sliderPosition = remember { mutableStateOf(value) }

    Column(modifier = Modifier.fillMaxWidth()) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val offset = getSliderOffset(
                value = sliderPosition.value,
                valueRange = valueRange,
                boxWidth = maxWidth,
                labelWidth = labelMinWidth
            )

            val endValueText = if (!finiteEnd && sliderPosition.value >= valueRange.endInclusive)
                "${sliderPosition.value.toInt()} +" else sliderPosition.value.toInt().toString()

            SliderLabel(
                label = valueRange.start.toInt().toString(),
                minWidth = labelMinWidth,
                modifier = Modifier.alpha(0f)
            )

            if (sliderPosition.value > valueRange.start) {
                SliderLabel(
                    label = endValueText,
                    minWidth = labelMinWidth,
                    modifier = Modifier
                        .padding(start = offset / 1.1f)
                )
            }
        }

        Slider(
            value = sliderPosition.value,
            onValueChange = {
                sliderPosition.value = it
            },
            valueRange = valueRange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            colors = SliderDefaults.colors(
                thumbColor = Red435B,
                activeTrackColor = Red435B,
                inactiveTrackColor = Color.White
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 17.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "0",
                style = SauExpertTypography.body1,
                color = Gray30,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )
            Text(
                text = (valueRange.endInclusive / 2).toInt().toString(),
                style = SauExpertTypography.body1,
                color = Gray30,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = valueRange.endInclusive.toInt().toString(),
                style = SauExpertTypography.body1,
                color = Gray30,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun SliderLabel(
    label: String,
    minWidth: Dp,
    modifier: Modifier = Modifier,
    textColor: Color = Red435B
) {
    Text(
        text = label,
        textAlign = TextAlign.Start,
        color = textColor,
        style = SauExpertTypography.body1,
        modifier = modifier
            .background(
                color = Color.Transparent
            )
            .defaultMinSize(minWidth = minWidth),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

private fun getSliderOffset(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    boxWidth: Dp,
    labelWidth: Dp
): Dp {
    val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
    val positionFraction = calcFraction(valueRange.start, valueRange.endInclusive, coerced)

    return (boxWidth - labelWidth) * positionFraction
}

private fun calcFraction(a: Float, b: Float, pos: Float) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(0f, 1f)

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewAppointment() {
    SauExpertTheme {
        AssignMedicine()
    }
}
