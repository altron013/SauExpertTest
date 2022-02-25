package com.example.sauexpert.general_report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.patient_card_screen.TabViewForPatientCardScreen
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.voyager_navigator.GeneralCaseReportActivity
import com.example.sauexpert.voyager_navigator.PrescriptionReportActivity
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBarWithSubtitle


@Composable
fun GeneralReportScreen() {
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

        Spacer(modifier = Modifier.height(dimensions.grid_2))

        ActionToolBarWithSubtitle(
            titleText = "Zhanna Akhmetova",
            subtitleText = "Общая оценка: 90 из 100",
            iconBackClick = Icons.Default.ArrowBack,
            sizeText = dimensions.fontSizeSubtitle_2,
            sizeSubtitleText = dimensions.fontSizeBody_2,
            sizeIcon = dimensions.iconSize_2,
            onBackClick = {},
            onRightClick = {}
        )

        Spacer(modifier = Modifier.height(dimensions.grid_3))

//        TabNavigator(GeneralCaseReportActivity) { tabNavigator ->
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        title = { Text(text = tabNavigator.current.options.title) }
//                    )
//                },
//                content = {
//                    CurrentTab()
//                },
//
//                bottomBar = {
//                    BottomNavigation {
//                        TabNavigationItem(GeneralCaseReportActivity)
//                        TabNavigationItem(PrescriptionReportActivity)
//                    }
//                }
//            )
//        }
//
//        Spacer(modifier = Modifier.height(50.dp))

        TabViewForPatientCardScreen(
            TextOfTab = listOf(
                TextOfTabData(
                    text = stringResource(id = com.example.sauexpert.R.string.general)
                ),
                TextOfTabData(
                    text = stringResource(id = com.example.sauexpert.R.string.appointments)
                ),
                TextOfTabData(
                    text = stringResource(id = com.example.sauexpert.R.string.severely_case)
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
            ) {
                GeneralCaseReportScreen()
            }

            1 -> Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                PrescriptionReportScreen()
            }
            2 -> Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CriticalCaseReportScreen()

            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        icon = { }
    )
}