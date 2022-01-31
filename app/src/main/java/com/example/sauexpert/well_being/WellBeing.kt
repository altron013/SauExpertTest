package com.example.sauexpert.well_being

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray75
import com.example.sauexpert.ui.theme.Green57C3A7
import com.example.sauexpert.widgets.compose.Toolbars.MainToolbar
import com.example.sauexpert.widgets.compose.buttons.OutlinedMainButton
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

enum class WellBeing {
    VERY_GOOD,
    NORMAL,
    BAD,
    GOOD,
    VERY_BAD
}

data class WellBeingCardFields(
    val painter: Painter,
    val content: String,
    val type:String
)

@ExperimentalFoundationApi
@Composable
fun WellBeingScreen() {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
    ) {
        MainToolbar(onBackClick = {}, text = stringResource(id = R.string.well_being))
        Column(Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
            WellBeingGeneral(
                heading = stringResource(id = R.string.glucose_level),
                date = "8 июня 2020 | 10:30",
                patient = "Анатолий Смирнов"
            )
        }
        WellBeingCards()
        Column(Modifier.padding(start = 20.dp, end = 20.dp)) {
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
            painter = painterResource(id = R.drawable.ic_report_samochuvstvie),
            type = WellBeing.VERY_BAD.toString()
        ),
        WellBeingCardFields(
            content = stringResource(id = R.string.bad),
            painter = painterResource(id = R.drawable.ic_report_samochuvstvie),
            type = WellBeing.BAD.toString()
        ),
        WellBeingCardFields(
            content = stringResource(id = R.string.normal),
            painter = painterResource(id = R.drawable.ic_report_samochuvstvie),
            type = WellBeing.NORMAL.toString()
        ),
        WellBeingCardFields(
            content = stringResource(id = R.string.good),
            painter = painterResource(id = R.drawable.ic_report_samochuvstvie),
            type = WellBeing.GOOD.toString()
        ),
        WellBeingCardFields(
            content = stringResource(id = R.string.very_good),
            painter = painterResource(id = R.drawable.ic_report_samochuvstvie),
            type = WellBeing.VERY_GOOD.toString()
        )
    )
    WellBeingCards(wellBeingCardList)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WellBeingCards(items: List<WellBeingCardFields>) {
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp / 2
    var selectedOption by remember {
        mutableStateOf("")
    }
    var onSelectionChange = { text: WellBeingCardFields ->
        selectedOption = text.type
    }
    FlowRow(
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
    ) {
        for (i in items) {
            EmojiCard(
                content = i.content, painter = i.painter, modifier = Modifier
                    .size(itemSize)
                    .padding(start = 20.dp, end = 20.dp, bottom = 40.dp)
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color =
                            if (i.type == selectedOption) {
                                Color.Red
                            } else {
                                Color.Gray
                            },
                        ),
                        shape = RoundedCornerShape(
                            size = 8.dp,
                        )
                    )
            )
        }
    }
}

@Composable
fun EmojiCard(painter: Painter, content: String, modifier: Modifier) {
    var selectedOption by remember {
        mutableStateOf("")
    }
    var onSelectionChange = { text: String ->
        selectedOption = text
    }
    Card(modifier = modifier
        .border(
            BorderStroke(
                width = 1.dp,
                color = Gray75
            ),
            shape = RoundedCornerShape(10.dp)
        )
        .clickable {
            onSelectionChange = onSelectionChange
            selectedOption = selectedOption
        },

    ) {
        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painter,
                contentDescription = "",
                tint = Gray75,
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