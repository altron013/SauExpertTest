package com.example.sauexpert.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.Toolbars.MainActionToolBar

@Composable
fun ViewInspectionInfoScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions
    val navigator = LocalNavigator.currentOrThrow

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
    ) {
        MainActionToolBar(
            titleText = stringResource(R.string.general_inspection),
            iconBackClick = Icons.Default.ArrowBack,
            onBackClick = { navigator.pop() },
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        ProfileForInspection(
            content = "user",
            text = 0.4f,
            dimensions = dimensions
        )

        Spacer(modifier = Modifier.height(32.dp))

        InfoStatInspectionSection(
            titleIllness = "Мочевыделительная система",
            subtitleIllness = "МОЧЕИСПУСКАНИЕ",
            subtitleIllness2 = "НЕДЕРЖАНИЕ МОЧИ",
            subtitleIllness3 = "СИМПТОМ ПОКОЛАЧИВАНИЯ",
            bottomText = stringResource(R.string.next_section),
            parameterChange = false,
            dimensions = dimensions
        )
    }

}

