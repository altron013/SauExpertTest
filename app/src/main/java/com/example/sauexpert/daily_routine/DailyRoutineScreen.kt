package com.example.sauexpert.daily_routine

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.TimeActivityData
import com.example.sauexpert.profile.OutlinedTextFieldWithBackground
import com.example.sauexpert.profile.ProfileForInspection
import com.example.sauexpert.ui.theme.Blue007AFF
import com.example.sauexpert.ui.theme.Gray15
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.Toolbars.MainActionToolBar
import com.example.sauexpert.widgets.compose.buttons.OutlinedMainButton
import com.example.sauexpert.widgets.compose.pop_up_dailog.RenameDialog
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@Composable
fun DailyRoutineScreen() {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    val listActivity = mutableListOf(
        TimeActivityData(activity = "Завтрак", time = "09:00"),
        TimeActivityData(activity = "Обед", time = "09:00"),
        TimeActivityData(activity = "Ужин", time = "10:00"),
        TimeActivityData(activity = "Подъём", time = "10:00", meal = false),
        TimeActivityData(activity = "Сон", time = "10:00", meal = false),
    )

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val index = remember { mutableStateOf(0) }


    val coroutineScope = rememberCoroutineScope()


    SauExpertTheme() {
        BottomSheetScaffold(
//            sheetBackgroundColor = Gray30.copy(alpha = 0.19f),
            drawerScrimColor = MaterialTheme.colors.onSurface.copy(alpha = 0.40f),
            scaffoldState = bottomSheetScaffoldState,
            sheetContent = {
                BottomSheetContentForDailyRoutine(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    },
                    listActivity = listActivity,
                    indexFromList = index

                )
            },
            sheetPeekHeight = 0.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Gray30.copy(alpha = 0.19f)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    MainActionToolBar(
                        titleText = stringResource(R.string.daily_routine),
                        iconBackClick = Icons.Default.ArrowBack,
                        onBackClick = {},
                        modifier = Modifier.padding(16.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    ProfileForInspection(
                        content = "user",
                        text = 0.4f,
                        dimensions = dimensions
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    MainDailyRoutineSection(
                        onClick = {
                            coroutineScope.launch {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            }

                        },
                        listActivity = listActivity,
                        index = index
                    )

                }
            }
        }
    }
}


@Composable
fun MainDailyRoutineSection(
    modifier: Modifier = Modifier,
    listActivity: MutableList<TimeActivityData>,
    index: MutableState<Int>,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.describe_daily_routine_of_patient),
            style = MaterialTheme.typography.body1,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(27.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.meal_time),
                style = MaterialTheme.typography.subtitle2,
            )

            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = null,
                tint = Color.Red,
                modifier = modifier
                    .clickable {
                    }
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        for (i in listActivity) {
            if (i.meal) {
                CardForMainDailyRoutine(
                    title = i.activity,
                    text = i.time,
                    onClick = onClick,
                    index = index,
                    id = listActivity.indexOf(i),
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.sleep),
            style = MaterialTheme.typography.subtitle2,
        )

        Spacer(modifier = Modifier.height(12.dp))

        for (i in listActivity) {
            if (!i.meal) {
                CardForMainDailyRoutine(
                    title = i.activity,
                    text = i.time,
                    onClick = onClick,
                    index = index,
                    id = listActivity.indexOf(i),
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        MainButton(
            text = stringResource(id = R.string.complete_general_inspection),
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = Modifier.fillMaxWidth()
        )


    }
}


@Composable
fun CardForMainDailyRoutine(
    title: String,
    text: String,
    index: MutableState<Int>,
    id: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
            .background(
                color = Gray30.copy(alpha = 0.19f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(vertical = 21.dp, horizontal = 15.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
        )

        Spacer(modifier = Modifier.weight(1f))

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
                text = text,
                style = MaterialTheme.typography.body1,
                fontSize = 22.sp
            )
        }

        Spacer(modifier = Modifier.width(22.dp))

        IconButton(
            onClick = {
                onClick()
                index.value = id
            },
        ) {
            Icon(
                imageVector = Icons.Default.MoreHoriz,
                contentDescription = null,
                tint = Color.Red,
            )
        }

    }

}


@Composable
fun BottomSheetContentForDailyRoutine(
    modifier: Modifier = Modifier,
    listActivity: MutableList<TimeActivityData>,
    indexFromList: MutableState<Int>,
    onClick: () -> Unit,
) {

    var stateForRename by rememberSaveable { mutableStateOf("") }

    val visible: MutableState<Boolean> = remember { mutableStateOf(false) }

    RenameDialog(
        isDialogOpen = visible,
        stateForRename = stateForRename,
        onNameChange = { stateForRename = it }
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(28.dp)

    ) {
        Column(
            modifier = Modifier
        ) {

            Text(
                text = stringResource(R.string.rename),
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        visible.value = true
                        stateForRename = listActivity[indexFromList.value].activity
                    }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.swap),
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                    }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.delete),
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                    }
            )

//                        ButtonWithTextColorChange(
//                text = stringResource(R.string.rename),
//                onClick = {
//                    visible.value = true
//                    stateForRename = listActivity[indexFromList.value].activity
//                },
//                enableState = true,
//                contentColor = Blue007AFF
//            )
//                        ButtonWithTextColorChange(
//                text = stringResource(R.string.swap),
//                onClick = {},
//                enableState = true,
//                contentColor = Blue007AFF
//            )
//                        ButtonWithTextColorChange(
//                text = stringResource(R.string.delete),
//                onClick = {
////                    if (listActivity.size == 0) {
////                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
////                    } else {
////                        listActivity.removeAt(0)
////                    }
//                },
//                enableState = true,
//                contentColor = Color.Red
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//                        ButtonWithTextColorChange(
//                text = stringResource(R.string.cancel),
//                onClick = onClick,
//                enableState = true,
//                contentColor = Blue007AFF
//            )
        }
    }
}


