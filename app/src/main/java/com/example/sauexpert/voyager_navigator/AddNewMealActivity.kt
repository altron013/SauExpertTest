package com.example.sauexpert.voyager_navigator

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.sauexpert.daily_routine.AddNewMealScreen

object AddNewMealActivity : Screen {

    @Composable
    override fun Content() {
        AddNewMealScreen()
    }
}