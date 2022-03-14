package com.example.sauexpert.voyager_navigator

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.sauexpert.general_report.PrescriptionCompletedReportScreen

object PrescriptionCompletedReportActivity: Screen {

    @Composable
    override fun Content() {
        PrescriptionCompletedReportScreen()
    }
}