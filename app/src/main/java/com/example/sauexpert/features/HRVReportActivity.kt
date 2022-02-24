package com.example.sauexpert.features

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.sauexpert.general_report.HRVReportScreen

object HRVReportActivity: Screen {

    @Composable
    override fun Content() {
        HRVReportScreen()
    }
}