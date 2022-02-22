package com.example.sauexpert.general_report

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Green57C3A7

@Composable
fun PrescriptionReportScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {

        CardItemForPrescription(
            titleOfPrescription = "Кардиомагнил, 50 мг, табл.",
            timeMiss = "3 пропуска",
            colorForMiss = Color.Red,
            date = "15 Октября 2021 — 20 декабря 2021",
            prescriptionText = "3 раза в день после еды",
            progressValue = 0.5f
        )

        Spacer(modifier = Modifier.height(16.dp))


        CardItemForPrescription(
            titleOfPrescription = "Новопассит, 40 мг, табл.",
            timeMiss = "3 пропуска",
            colorForMiss = Color.Red,
            date = "15 Октября 2021 — 20 декабря 2021",
            prescriptionText = "Каждый день по 3 раза после еды",
            progressValue = 0.5f
        )

        Spacer(modifier = Modifier.height(16.dp))


        CardItemForPrescription(
            titleOfPrescription = "Медикамент Глюкофаж",
            timeMiss = "нет пропусков",
            date = "15 Октября 2021 — 20 декабря 2021",
            prescriptionText = "Каждый день",
            progressValue = 0.5f
        )

    }
}


@Composable
fun CardItemForPrescription(
    titleOfPrescription: String,
    timeMiss: String,
    colorForMiss: Color = Green57C3A7,
    date: String,
    prescriptionText: String,
    progressValue: Float = 0f,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {

            }
    ) {
        Column(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = Gray30.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = titleOfPrescription,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.weight(0.8f)
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(0.2f),
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))


            Text(
                text = timeMiss,
                style = MaterialTheme.typography.button,
                fontSize = 13.sp,
                color = colorForMiss

            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = date,
                style = MaterialTheme.typography.button,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = prescriptionText,
                style = MaterialTheme.typography.button,
                fontSize = 13.sp,
                color = Gray30
            )

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = progressValue,
                color = Color.Black,
                modifier = modifier
                    .fillMaxWidth()
                    .height(height = 6.dp)
            )


        }
    }
}