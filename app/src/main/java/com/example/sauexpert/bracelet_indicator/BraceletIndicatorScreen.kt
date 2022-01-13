package com.example.sauexpert.bracelet_indicator

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton


@Composable
fun BraceletIndicatorScreen() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                color = Gray30.copy(alpha = 0.19f),
                shape = RoundedCornerShape(7.dp)
            )
            .padding(16.dp)
    ) {
        TopBarForBracelet()
        Spacer(modifier = Modifier.height(28.dp))
        TabView(
            TextOfTab = listOf(
                TextOfTabData(
                    text = stringResource(id = R.string.sleep)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.sp02)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.hrv)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.pressure)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.pulse)
                )
            )


        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
            ) {
                SleepScreen()
                MainButton(
                    text = stringResource(id = R.string.range_customize),
                    onClick = { /*TODO*/ },
                    enableState = true,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
            1 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
            ) {
                Sp02Screen()
                MainButton(
                    text = stringResource(id = R.string.range_customize),
                    onClick = { /*TODO*/ },
                    enableState = true,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )

            }

        }
    }
}


@Composable
fun TopBarForBracelet(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = modifier.align(Alignment.CenterStart)
                .clickable {
                }
        )

        Text(
            text = stringResource(id = R.string.bracelet),
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier.align(Alignment.Center)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_calendar_icon),
            contentDescription = null,
            modifier = modifier.align(Alignment.CenterEnd)
        )


    }
}

@Composable
fun TabView(
    modifier: Modifier = Modifier,
    TextOfTab: List<TextOfTabData>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inactiveColor = Gray30
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Transparent,
        modifier = modifier.fillMaxWidth()

            .border(
                BorderStroke(1.5.dp, inactiveColor),
                RoundedCornerShape(8.dp)
            )
    ) {
        TextOfTab.forEachIndexed { index, item ->
            Tab(selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)

                }
            ) {
                Text(
                    text = item.text,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp)

                )
            }

        }

    }
}



