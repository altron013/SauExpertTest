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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
        Divider(thickness = 1.dp, color = Separator)
        Spacer(modifier = Modifier.height(24.dp))
        val sliderPosition = remember { mutableStateOf(0f) }
        Slider(value = sliderPosition.value,
            onValueChange = { sliderPosition.value = it },
            valueRange = 0f..20000f,
            steps = 1,
            colors = SliderDefaults.colors(
                thumbColor = Gray50,
                activeTrackColor = Red435B,
                inactiveTrackColor = DarkGray,
                activeTickColor = Red435B,
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
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


@Preview(showBackground = true)
@Composable
fun PreviewAppointment() {
    SauExpertTheme {
        GoalSteps()
    }
}


