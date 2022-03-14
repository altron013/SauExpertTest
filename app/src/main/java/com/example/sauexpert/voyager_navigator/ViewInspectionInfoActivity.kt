package com.example.sauexpert.voyager_navigator

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.sauexpert.profile.ViewInspectionInfoScreen

object ViewInspectionInfoActivity : Screen {

    @Composable
    override fun Content() {
        ViewInspectionInfoScreen()
    }
}