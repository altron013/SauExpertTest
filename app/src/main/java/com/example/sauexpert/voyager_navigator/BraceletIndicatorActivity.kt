package com.example.sauexpert.voyager_navigator

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.sauexpert.bracelet_indicator.BraceletIndicatorScreen

object BraceletIndicatorActivity : Screen {

    @OptIn(ExperimentalMaterialApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        BraceletIndicatorScreen()
    }
}