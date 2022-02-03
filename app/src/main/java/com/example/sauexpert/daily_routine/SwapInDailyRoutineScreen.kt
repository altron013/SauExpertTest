package com.example.sauexpert.daily_routine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray30

@Composable
fun SwapDailyRoutineScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))

    ) {
        TopBarForAddNewMeal(
            closeText = stringResource(id = R.string.cancellation),
            titleText = stringResource(id = R.string.meal_time),
            showDoneButton = true
        )

        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(23.dp))

        MainSectionForAddNewMeal()

    }
}


@Composable
fun MainSectionForSwap() {

    val listActivity = mutableListOf(
        TimeActivity(activity = "Завтрак", time = "09:00"),
        TimeActivity(activity = "Обед", time = "09:00"),
        TimeActivity(activity = "Ужин", time = "10:00"),
    )


}