package com.example.sauexpert.daily_routine

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.profile.OutlinedTextFieldWithBackground
import com.example.sauexpert.ui.theme.Gray15
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBar

@Composable
fun AddNewMealScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ActionToolBar(
                titleText = stringResource(id = R.string.new_meal_time),
                textBackClick = stringResource(id = R.string.close),
                colorBackClick = Color.Red,
                onBackClick = {},
                onRightClick = {}
            )
        }

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
fun MainSectionForAddNewMeal(
    modifier: Modifier = Modifier
) {
    var newMeal by rememberSaveable { mutableStateOf("") }
    val stateBoolean = newMeal != ""

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        OutlinedTextFieldWithBackground(
            textForHint = stringResource(R.string.example_afternoon_snack),
            textState = newMeal,
            onTextChange = { newMeal = it },
            colorBackground = Color.White
        )

        Spacer(modifier = Modifier.height(23.dp))

        TimePickerForAddNewMeal(
            timeText = "09:00"
        )

        Spacer(modifier = Modifier.height(23.dp))

        MainButton(
            text = stringResource(id = R.string.done),
            onClick = { /*TODO*/ },
            enableState = stateBoolean,
        )

    }
}


@Composable
fun TimePickerForAddNewMeal(
    timeText: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(vertical = 11.dp, horizontal = 15.dp)
    ) {
        Text(
            text = stringResource(R.string.time_for_meal),
            style = MaterialTheme.typography.body1,
        )


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .background(
                    color = Gray15,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Text(
                text = timeText,
                style = MaterialTheme.typography.body1,
                fontSize = 22.sp,
                modifier = modifier.clickable { }
            )
        }

    }
}

