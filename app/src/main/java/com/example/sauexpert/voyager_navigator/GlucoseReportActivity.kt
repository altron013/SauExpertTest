package com.example.sauexpert.voyager_navigator

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.sauexpert.general_report.GlucoseReportScreen

object GlucoseReportActivity : Screen {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        GlucoseReportScreen()
    }
}