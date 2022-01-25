package com.example.sauexpert.well_being

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.widgets.compose.Toolbars.MainToolbar
import com.example.sauexpert.widgets.compose.buttons.OutlinedMainButton


data class WellBeingCardFields(
    val painter: Painter,
    val content: String,
)

@ExperimentalFoundationApi
@Composable
fun WellBeingScreen() {
    Column() {
        var well=remember { mutableStateOf("") }
        MainToolbar(onBackClick = {}, text = stringResource(id = R.string.well_being))
        Column(
            Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            WellBeingGeneral(
                heading = stringResource(id = R.string.glucose_level),
                date = "8 июня 2020 | 10:30",
                patient = "Анатолий Смирнов"
            )
            WellBeingCards()
            Spacer(Modifier.padding(14.dp))
            WellBeingCheck()
            Spacer(Modifier.padding(14.dp))
            OutlinedMainButton(
                text = stringResource(id = R.string.confirm),
                onClick = { /*TODO*/ },
                enableState = false
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun WellBeingCards() {
    val wellBeingCardList = listOf(
        WellBeingCardFields(
            content = stringResource(id = R.string.very_bad),
            painter = painterResource(id = R.drawable.ic_profile_squared)
        ),
        WellBeingCardFields(
            content = stringResource(id = R.string.bad),
            painter = painterResource(id = R.drawable.ic_profile_squared)
        ),
        WellBeingCardFields(
            content = stringResource(id = R.string.normal),
            painter = painterResource(id = R.drawable.ic_profile_squared)
        ),
        WellBeingCardFields(
            content = stringResource(id = R.string.good),
            painter = painterResource(id = R.drawable.ic_profile_squared)
        ),
        WellBeingCardFields(
            content = stringResource(id = R.string.very_good),
            painter = painterResource(id = R.drawable.ic_profile_squared)

        )
    )

       Row() {
     Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.SpaceBetween) {
           EmojiCard(
               content = stringResource(id = R.string.very_bad),
              painter = painterResource(id = R.drawable.ic_profile_squared)
            )
            Spacer(Modifier.padding(14.dp))
            EmojiCard(
                content = stringResource(id = R.string.bad),
                painter = painterResource(id = R.drawable.ic_profile_squared)
            )
            Spacer(Modifier.padding(14.dp))
            EmojiCard(
                content = stringResource(id = R.string.normal),
                painter = painterResource(id = R.drawable.ic_profile_squared)
            )
        }
        Spacer(Modifier.padding(15.dp))
        Column(modifier = Modifier.weight(1f)) {
            EmojiCard(
                content = stringResource(id = R.string.good),
                painter = painterResource(id = R.drawable.ic_profile_squared)
            )
            Spacer(Modifier.padding(14.dp))
            EmojiCard(
                content = stringResource(id = R.string.very_good),
                painter = painterResource(id = R.drawable.ic_profile_squared)
            )
        }
    }
}

@Composable
fun EmojiCard(painter: Painter, content: String) {
    Card(modifier = Modifier
        .clickable { }
        .fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(top = 23.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painter,
                tint = Color.Gray,
                contentDescription = "",
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = content,
                fontSize = 18.sp,
                color = Color.Gray
            )
        }
    }
}