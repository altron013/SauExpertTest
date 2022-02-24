package com.example.sauexpert.voyager_navigator

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.sauexpert.general_report.SleepReportScreen

object SleepReportActivity : Screen {

    @Composable
    override fun Content() {
        SleepReportScreen()
    }
}