package com.example.sauexpert.voyager_navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.sauexpert.R
import com.example.sauexpert.general_report.GeneralCaseReportScreen

object GeneralCaseReportActivity : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.general_inspection)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                )
            }
        }

    @Composable
    override fun Content() {
        GeneralCaseReportScreen()
    }
}