package com.example.sauexpert.daily_routine

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.TimeActivityData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBar
import com.example.sauexpert.widgets.compose.drag_drop.DragDropList
import com.example.sauexpert.widgets.compose.drag_drop.move

@Composable
fun SwapDailyRoutineScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

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
                titleText = stringResource(id = R.string.meal_time),
                textBackClick = stringResource(id = R.string.cancellation),
                textRightClick = stringResource(id = R.string.done),
                sizeText = dimensions.fontSizeSubtitle_2,
                colorBackClick = Color.Red,
                colorRightClick = Color.Red,
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

        MainSectionForSwap(
            dimensions = dimensions
        )

    }
}


@Composable
fun MainSectionForSwap(
    dimensions: Dimensions
) {
    val listActivity = listOf(
        TimeActivityData(activity = "Завтрак", time = "09:00"),
        TimeActivityData(activity = "Обед", time = "09:00"),
        TimeActivityData(activity = "Ужин", time = "10:00"),
    ).toMutableStateList()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        DragDropList(
            items = listActivity,
            onMove = { fromIndex, toIndex -> listActivity.move(fromIndex, toIndex) },
            dimensions = dimensions
        )
    }
}


@Composable
fun ItemForSwapInDailyRoutine(
    items: List<TimeActivityData>,
    index: Int,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 11.dp, horizontal = 16.dp)
    ) {

        Icon(
            imageVector = Icons.Default.RemoveCircle,
            contentDescription = null,
            tint = Color.Red,
            modifier = modifier
                .size(dimensions.iconSize_5)
                .clickable { }
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = items[index].activity,
            style = MaterialTheme.typography.body1,
            fontSize = dimensions.fontSizeBody_1
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = items[index].time,
            style = MaterialTheme.typography.body1,
            fontSize = dimensions.fontSizeBody_1
        )

        Spacer(modifier = Modifier.width(24.dp))

        Icon(
            imageVector = Icons.Default.Reorder,
            contentDescription = null,
            tint = Color.Black,
            modifier = modifier
                .size(dimensions.iconSize_5)
        )
    }
}

