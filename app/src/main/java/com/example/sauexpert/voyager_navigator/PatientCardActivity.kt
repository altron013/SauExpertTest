package com.example.sauexpert.voyager_navigator

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.sauexpert.patient_card_screen.PatientCardScreen

object PatientCardActivity : Screen {

    @Composable
    override fun Content() {
        PatientCardScreen()
    }
}