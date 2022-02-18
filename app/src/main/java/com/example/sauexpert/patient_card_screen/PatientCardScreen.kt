package com.example.sauexpert.patient_card_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.profile.RoundImage
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.Toolbars.MainActionToolBar
import com.example.sauexpert.widgets.compose.buttons.MainButtonsInRow

@Composable
fun PatientCardScreen() {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
    ) {
        MainActionToolBar(
            iconBackClick = Icons.Default.ArrowBack,
            onBackClick = {},
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        ProfileForPatientCard(
            userName = "Жанна Ахметова",
            diagnosisString = "E11.9 Сахарный диабет"
        )
        Spacer(modifier = Modifier.height(16.dp))
        AnamnesisCell(lastAnamnesisDiagnos = "Перенесла операцию на сердце в 2017")
        Spacer(modifier = Modifier.height(28.dp))

        TabViewForPatientCardScreen(
            TextOfTab = listOf(
                TextOfTabData(
                    text = stringResource(id = R.string.indicators)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.diagnosis)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.analysis)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.appointments)
                )
            )


        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(bottom = 10.dp)

            ) {
                IndicatorPatientCardScreen()

                MainButtonsInRow(
                    iconForOutlineBtn = R.drawable.ic_sms,
                    textForMainBtn = stringResource(id = R.string.begin_diagnostic),
                    iconForMainBtn = R.drawable.ic_play_fill,
                    borderColor = Color.Transparent,
                    weightOfFirstButton = 0.3f,
                    onClickForOutlineBtn = { /*TODO*/ },
                    onClickForMainBtn = { /*TODO*/ },
                    enableStateForOutlineBtn = true,
                    enableStateForMainBtn = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .align(Alignment.BottomCenter)
                )


            }

            1 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                BeginInspectionScreen()
            }
        }
    }
}

@Composable
fun ProfileForPatientCard(
    userName: String,
    diagnosisString: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        RoundImage(
            image = painterResource(id = R.drawable.avatar),
            modifier = Modifier
                .size(48.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column() {
            Text(
                text = userName,
                style = MaterialTheme.typography.subtitle2,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = diagnosisString,
                style = MaterialTheme.typography.body2,
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.Black,
            modifier = modifier.size(20.dp)
        )


    }

}

@Composable
fun AnamnesisCell(
    lastAnamnesisDiagnos: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = Gray30.copy(alpha = 0.35f),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 11.dp
                )
        ) {

            Text(
                text = stringResource(R.string.anamnesis),
                style = MaterialTheme.typography.body2,
            )

            Spacer(modifier = Modifier.height(4.dp))


            Text(
                text = lastAnamnesisDiagnos,
                style = MaterialTheme.typography.button,
                fontSize = 15.sp
            )
        }
    }
}


@Composable
fun TabViewForPatientCardScreen(
    modifier: Modifier = Modifier,
    TextOfTab: List<TextOfTabData>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inactiveColor = Gray30
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Red,
        modifier = modifier.fillMaxWidth()
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
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }

        }

    }
}
