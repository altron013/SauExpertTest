package com.example.sauexpert.profile

import androidx.activity.compose.LocalActivityResultRegistryOwner.current
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.signup_doctor.SubmitApplicationToolbar
import com.example.sauexpert.ui.theme.BorderGray
import com.example.sauexpert.ui.theme.Gray50
import com.example.sauexpert.ui.theme.Green34C759
import com.example.sauexpert.ui.theme.Orange1
import com.example.sauexpert.widgets.compose.MainButton
import java.util.*
import java.util.concurrent.ThreadLocalRandom.current

data class Inspection(
    val name: String,
    val percentage: Int
)

@Composable
fun SupplementData() {
    var selectedOption by remember {
        mutableStateOf("")
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }
    val generalList = listOf(
        Inspection("Общая информация", 100),
        Inspection("Расчёт рисков", 100),
        Inspection(
            "Состояние пациента на момент осмотра", 100
        ),
        Inspection("Кожные покровы", 100),
        Inspection("Дыхательная система", 100),
        Inspection(
            "Мышечная и костно-суставная система", 100
        ),
        Inspection("Сердечно-сосудистая система", 100),
        Inspection("Желудочно-кишечный тракт", 5),
        Inspection("Мочевыделительная система", 100)
    )
    val analysisList = listOf(
        Inspection("Анализы/Диагностика", 100)
    )
    val diariesList = listOf(
        Inspection("Предиабет", 0),
        Inspection("Физическая активность", 0),
        Inspection("Шкала тревоги и депрессии (HADS) ", 0)
    )
    val routineList = listOf(
        Inspection("Режим дня", 0),
    )

    Column(Modifier.verticalScroll(rememberScrollState())) {
        SubmitApplicationToolbar(
            stringResource(id = R.string.close),
            stringResource(id = R.string.submit_application)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Выберите раздел, в котором Вы хотите дополнить данные.",
                color = Gray50,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(id = R.string.general_inspection).toUpperCase(), fontSize = 12.sp)
                Text(stringResource(id = R.string.filled).toUpperCase(), fontSize = 12.sp)
            }
            InspectonCard(generalList)
            InspectonCard(analysisList)
            Text("Анкеты")
            InspectonCard(diariesList)
            InspectonCard(routineList)
            Spacer(modifier = Modifier.padding(10.dp))
            MainButton(onClick = { /*TODO*/ }, enableState = true,text = "Перейти")
        }
    }
}

@Composable
fun InspectonCard(list: List<Inspection>) {
    var selectedOption by remember {
        mutableStateOf("")
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }
    Card(
        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            for (i in list) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectionChange(i.name) }
                        .padding(10.dp),
                ) {
                    Text(text = i.name, modifier = Modifier.weight(6f))
                    if (i.percentage > 30) {
                        Text(
                            text = i.percentage.toString() + "%",
                            color = Green34C759,
                            modifier = Modifier.weight(1.4f)
                        )
                    }
                    else if (i.percentage == 0) {
                        Text(
                            text = i.percentage.toString() + "%",
                            color = Color.Red,
                            modifier = Modifier.weight(1.4f)
                        )
                    }
                    else {
                        Text(
                            text = i.percentage.toString() + "%",
                            color = Orange1,
                            modifier = Modifier.weight(1.4f)
                        )
                    }
                    if (i.name == selectedOption) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "dd",
                            tint = Color.Red,
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                if (i != list.last()) {
                    Divider(color = BorderGray, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

}