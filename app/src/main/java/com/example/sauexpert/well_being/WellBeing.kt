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
    val type: String,
    val color: Color,
    val painter2: Painter
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
            Spacer(Modifier.padding(14.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WellBeingCards() {
    val wellBeingCardList = listOf(
        WellBeingCardFields(
            content = stringResource(id = R.string.very_bad),
            painter = painterResource(id = R.drawable.ic_report_samochuvstvie),
            type = "Sad",
            color = Color.Gray,
            painter2 = painterResource(id = R.drawable.ic_home),
            ),
        WellBeingCardFields(
            content = stringResource(id = R.string.bad),
            painter = painterResource(id = R.drawable.ic_report_samochuvstvie),
            type = WellBeing.BAD.toString(),
            color = Color.Gray,
            painter2 = painterResource(id = R.drawable.ic_home)
        ),
        WellBeingCardFields(
            content = stringResource(id = R.string.normal),
            painter = painterResource(id = R.drawable.ic_report_samochuvstvie),
            type = WellBeing.NORMAL.toString(),
            color = Color.Gray,
            painter2 = painterResource(id = R.drawable.ic_home)
        ),
        WellBeingCardFields(
            content = stringResource(id = R.string.good),
            painter = painterResource(id = R.drawable.ic_report_samochuvstvie),
            type = WellBeing.GOOD.toString(),
            color = Color.Gray,
            painter2 = painterResource(id = R.drawable.ic_home)
        ),
        WellBeingCardFields(
            content = stringResource(id = R.string.very_good),
            painter = painterResource(id = R.drawable.ic_report_samochuvstvie),
            type = WellBeing.VERY_GOOD.toString(),
            color = Color.Gray,
            painter2 = painterResource(id = R.drawable.ic_home)
        )
    )
    val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp / 2
    var selectedOption by remember {
        mutableStateOf("")
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }
    FlowRow(
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
    ) {
        for (i in wellBeingCardList) {
            EmojiCard(
                content = i.content,
                painter = if (i.content == selectedOption) {
                    i.painter2
                } else {
                    i.painter
                },
                modifier = Modifier
                    .size(itemSize)
                    .padding(start = 20.dp, end = 20.dp, bottom = 40.dp)
                    .clickable { onSelectionChange(i.content) }
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color =
                            if (i.content == selectedOption) {
                                Color.Green
                            } else {
                                Color.Black
                            },
                        ),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
            )
            when{
           
            }
        }

    }
}

@Composable
fun EmojiCard(
    painter: Painter,
    content: String,
    modifier: Modifier,
    tint: Color = Color.Black
) {
    Card(
        modifier = modifier
//        .border(
//            BorderStroke(
//                width = 1.dp,
//                color = Color.Black
//            ),
//            shape = RoundedCornerShape(10.dp)
//        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painter,
                contentDescription = "",
                tint = tint,
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