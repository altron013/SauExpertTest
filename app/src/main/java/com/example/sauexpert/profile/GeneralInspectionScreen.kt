package com.example.sauexpert.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Pink42949
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.Toolbars.MainActionToolBar

@Composable
fun GeneralInspectionScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
    ) {
        MainActionToolBar(
            titleText = stringResource(R.string.general_inspection),
            iconBackClick = Icons.Default.ArrowBack,
            onBackClick = {},
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        ProfileForInspection(content = "Zhanna Akhmetova", text = 0f)

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp)
                )
                .padding(16.dp)
        ) {
            PreviousInspectionsSection(
                doctorName = "Келимбетов Аскар Ахметович",
                dateOfInspection = "22 Мая 2021",
                yourInspection = true,
                dimensions = dimensions
            )

            Spacer(modifier = Modifier.height(24.dp))

            MainButton(
                text = stringResource(id = R.string.new_inspections),
                onClick = { /*TODO*/ },
                enableState = true,
                icon = R.drawable.ic_plus_circle,
                backgroundColor = Pink42949,
                textColor = Color.Red
            )
        }
    }
}