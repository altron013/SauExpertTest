package com.example.sauexpert.voyager_navigator

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.sauexpert.profile.NewInspectionScreen

object NewInspectionActivity : Screen {

    @Composable
    override fun Content() {
        NewInspectionScreen()
    }
}