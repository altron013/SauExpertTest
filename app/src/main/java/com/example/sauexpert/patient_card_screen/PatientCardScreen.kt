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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.analysis.AnalysisContent
import com.example.sauexpert.diagnosis.DiagnosisContent
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
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

    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
    ) {
        MainActionToolBar(
            iconBackClick = Icons.Default.ArrowBack,
            sizeIconBackClick = dimensions.iconSize_2,
            onBackClick = {},
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(dimensions.grid_1))

        ProfileForPatientCard(
            userName = "Жанна Ахметова",
            diagnosisString = "E11.9 Сахарный диабет",
            dimensions = dimensions
        )

        Spacer(modifier = Modifier.height(dimensions.grid_2))

        AnamnesisCell(
            lastAnamnesisDiagnos = "Перенесла операцию на сердце в 2017",
            dimensions = dimensions
        )

        Spacer(modifier = Modifier.height(dimensions.grid_3_5))

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
            ),
            dimensions = dimensions
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
                    buttonHeight = dimensions.buttonHeight_0,
                    sizeText = dimensions.fontSizeBody_1,
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

            2 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                AnalysisContent()
            }

            3 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                DiagnosisContent()
            }
        }
    }
}

@Composable
fun ProfileForPatientCard(
    userName: String,
    profileImage: Painter = painterResource(id = R.drawable.avatar),
    diagnosisString: String,
    dimensions: Dimensions,
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
            image = profileImage,
            modifier = Modifier
                .size(dimensions.imageSize_0)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column() {
            Text(
                text = userName,
                style = MaterialTheme.typography.subtitle2,
                fontSize = dimensions.fontSizeSubtitle_2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = diagnosisString,
                style = MaterialTheme.typography.body2,
                fontSize = dimensions.fontSizeBody_2
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.Black,
            modifier = modifier.size(dimensions.iconSize_2)
        )


    }

}

@Composable
fun AnamnesisCell(
    lastAnamnesisDiagnos: String,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = Gray30.copy(alpha = 0.25f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(
                horizontal = 16.dp,
                vertical = 11.dp
            )
    ) {
        Text(
            text = stringResource(R.string.anamnesis),
            style = MaterialTheme.typography.body2,
            fontSize = dimensions.fontSizeBody_2
        )

        Spacer(modifier = Modifier.height(4.dp))


        Text(
            text = lastAnamnesisDiagnos,
            style = MaterialTheme.typography.button,
            fontSize = dimensions.fontSizeCustom_1
        )
    }
}


@Composable
fun TabViewForPatientCardScreen(
    modifier: Modifier = Modifier,
    TextOfTab: List<TextOfTabData>,
    dimensions: Dimensions,
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
                    fontSize = dimensions.fontSizeCustom_1,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }

        }

    }
}
