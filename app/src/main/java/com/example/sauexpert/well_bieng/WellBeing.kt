package com.example.sauexpert.well_bieng

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.signup_doctor.SubmitApplicationToolbar

@Composable
fun WellBeingScreen() {
    Column() {
        SubmitApplicationToolbar(
            leftText = "Назад",
            centerText = stringResource(id = R.string.glucose_level)
        )
        Column(
            Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.glucose_level),
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.padding(15.dp))
            Text(
                text = "Назначено: 8 июня 2020 | 10:30",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.padding(4.dp))
            Text(text = "Анатолий Смирнов", fontSize = 17.sp)
            Spacer(Modifier.padding(14.dp))
            WellBeingCards()
        }
    }
}

@Composable
fun WellBeingCards() {
    Row() {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.SpaceBetween) {
            Card() {
                Icon(
                    painterResource(id = R.drawable.ic_very_bad_emoji),
                    contentDescription = "",
                    modifier = Modifier.padding(56.dp)
                )
                Text(
                    text = "Назначено: 8 июня 2020 | 10:30",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.padding(14.dp))
            Card() {
                Icon(
                    painter = painterResource(id = R.drawable.ic_very_bad_emoji),
                    contentDescription = "",
                    modifier = Modifier.padding(56.dp)
                )
                Text(
                    text = "Назначено: 8 июня 2020 | 10:30",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.padding(14.dp))
            Card() {
                Icon(
                    painter = painterResource(id = R.drawable.ic_very_bad_emoji),
                    contentDescription = "",
                    modifier = Modifier.padding(56.dp)
                )
                Text(
                    text = "Назначено: 8 июня 2020 | 10:30",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Spacer(Modifier.padding(15.dp))
        Column(modifier = Modifier.weight(1f)) {
            Card() {
                Icon(
                    painter = painterResource(id = R.drawable.ic_very_bad_emoji),
                    contentDescription = "",
                    modifier = Modifier.padding(56.dp)
                )
                Text(
                    text = "Назначено: 8 июня 2020 | 10:30",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.padding(14.dp))
            Card() {
                Icon(
                    painter = painterResource(id = R.drawable.ic_very_bad_emoji),
                    contentDescription = "",
                    modifier = Modifier.padding(56.dp)
                )
                Text(
                    text = "Назначено: 8 июня 2020 | 10:30",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun EmojiCard() {

}