package com.example.sauexpert.general_report

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sauexpert.patient_card_screen.CriticalCaseCell

@Composable
fun CriticalCaseReportScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        CriticalCaseCell(
            hypoglycemiaValue = 5,
            hyperglycemiaValue = 4,
            hypertensionValue = 8,
            hypotensionValue = 3
        )
    }
}