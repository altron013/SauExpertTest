package com.example.sauexpert.diagnosis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.*
import com.example.sauexpert.widgets.compose.SpacingVertical

@Composable
fun EmptyDiagnosis() {
    SauExpertTheme() {
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
}

@Composable
fun DiagnosisContent() {
    SauExpertTheme() {
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
                    .clickable {  },
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

@Preview(showBackground = true)
@Composable
fun DiagnosisPreview() {
    DiagnosisContent()
}

