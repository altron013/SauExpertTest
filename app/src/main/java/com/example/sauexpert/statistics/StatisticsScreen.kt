package com.example.sauexpert.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray15
import com.example.sauexpert.ui.theme.Gray50
import com.example.sauexpert.ui.theme.Green34C759
import com.example.sauexpert.ui.theme.SurfaceBlue


data class StatisticsData(
    val category: String? = null,
    val icon: Painter? = null,
    val scales: Map<String, Int>? = null,
    val sum: Int? = null
)

@Composable
fun Statistics() {
    val statisticsList = listOf(
        StatisticsData(
            category = "Пациенты",
            scales = mapOf("Активные" to 16, "Неактивные" to 8, "Новые" to 3),
            sum = 27,
            icon = painterResource(id = R.drawable.ic_person_2_crop_square_stack)
        ),
        StatisticsData(
            category = "Критические случаи",
            scales = mapOf(
                "Гипертонический криз" to 11,
                "Гипергликемия" to 4,
                "Гипогликемия " to 56
            ),
            sum = 71,
            icon = painterResource(id = R.drawable.ic_waveform_path_ecg)
        )
    )
    Column(
        modifier = Modifier
            .background(SurfaceBlue)
            .fillMaxSize()
            .padding(16.dp,top=63.dp,end=16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Статистика, ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 34.sp,
                            color = Color.LightGray,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Декабрь 2021")
                    }
                },
                modifier = Modifier.weight(5f)
            )
            Icon(
                painter= painterResource(id = R.drawable.ic_calendar),
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Rating()
        Spacer(modifier = Modifier.size(12.dp))
        for (it in statisticsList) {
            StatisticsCard(it)
        }
    }
}

@Composable
fun Rating() {
    Card(shape = RoundedCornerShape(12.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start=12.dp,end=12.dp,bottom=12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_leadinghalf_filled),
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Рейтинг",
                fontSize = 17.sp,
                modifier = Modifier.weight(4f),
                color = Color.Black
            )
            Column(
                modifier = Modifier
                    .weight(3f)
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.End,
            ) {
                Text("4,9", color = Green34C759, fontSize = 17.sp)
                Text(text = "23 оценки", color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun StatisticsCard(content: StatisticsData) {
    Card(
        shape = RoundedCornerShape(12.dp)
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                content.icon?.let {
                    Icon(
                        painter = it,
                        contentDescription = "",
                        modifier = Modifier.weight(2f),
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = content.category ?: "",
                    fontSize = 17.sp,
                    modifier = Modifier.weight(10f),
                    color = Color.Black,

                    )
                Text(
                    text = content.sum.toString(),
                    fontSize = 17.sp,
                    modifier = Modifier
                        .weight(1.2f)
                        .padding(top = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Divider(
                color = Gray15,
                modifier = Modifier.padding(top=3.dp,start = 16.dp, end = 16.dp)
            )
            content.scales?.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = it.key,
                        fontSize = 17.sp,
                        color = Gray50
                    )
                    Text(
                        text = "${it.value}",
                        fontSize = 17.sp,
                        modifier = Modifier
                            .padding(top = 6.dp),
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
    Spacer(modifier = Modifier.padding(6.dp))
}
