package com.example.sauexpert.inspection

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.sauexpert.R
import com.example.sauexpert.my_patients.ButtonWithTextColorChange
import com.example.sauexpert.profile.ProfileForInspection
import com.example.sauexpert.profile.TopBarForInspectionScreen
import com.example.sauexpert.ui.theme.Blue007AFF
import com.example.sauexpert.ui.theme.Gray15
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.buttons.OutlinedMainButton
import kotlinx.coroutines.launch


data class TimeActivity(
    var activity: String,
    var time: String,
    val meal: Boolean
)


@ExperimentalMaterialApi
@Composable
fun DailyRoutineScreen() {

    val listActivity = mutableListOf(
        TimeActivity(activity = "Завтрак", time = "09:00", meal = true),
        TimeActivity(activity = "Обед", time = "09:00", meal = true),
        TimeActivity(activity = "Ужин", time = "10:00", meal = true),
        TimeActivity(activity = "Подъём", time = "10:00", meal = false),
        TimeActivity(activity = "Сон", time = "10:00", meal = false),
    )

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val index = rememberSaveable{ mutableStateOf(0) }


    val coroutineScope = rememberCoroutineScope()




    SauExpertTheme() {
        BottomSheetScaffold(
            sheetBackgroundColor = Color.Transparent,
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
                    TopBarForInspectionScreen(titleString = stringResource(R.string.daily_routine))
                    Spacer(modifier = Modifier.height(4.dp))
                    ProfileForInspection(content = "user", text = 0.4f)
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
fun BottomSheetContentForDailyRoutine(
    modifier: Modifier = Modifier,
    listActivity: MutableList<TimeActivity>,
    indexFromList: MutableState<Int>,
    onClick: () -> Unit,
) {

    var stateForRename by rememberSaveable { mutableStateOf(listActivity[indexFromList.value].activity) }

    val visible: MutableState<Boolean> = remember { mutableStateOf(false) }

    RenameDialog(
        isDialogOpen = visible,
        stateForRename = stateForRename,
        onNameChange = { stateForRename = it }
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Column(
            modifier = Modifier
        ) {
            ButtonWithTextColorChange(
                text = stringResource(R.string.rename),
                onClick = {
                    visible.value = true
                },
                enableState = true,
                shape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp),
                contentColor = Blue007AFF
            )
            ButtonWithTextColorChange(
                text = stringResource(R.string.swap),
                onClick = {},
                enableState = true,
                shape = RoundedCornerShape(0.dp),
                contentColor = Blue007AFF
            )
            ButtonWithTextColorChange(
                text = stringResource(R.string.delete),
                onClick = {
//                    if (listActivity.size == 0) {
//                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
//                    } else {
//                        listActivity.removeAt(0)
//                    }
                },
                enableState = true,
                shape = RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp),
                contentColor = Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))
            ButtonWithTextColorChange(
                text = stringResource(R.string.cancel),
                onClick = onClick,
                enableState = true,
                contentColor = Blue007AFF
            )
        }
    }
}


@Composable
fun MainDailyRoutineSection(
    modifier: Modifier = Modifier,
    listActivity: MutableList<TimeActivity>,
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
//                index.value = listActivity.indexOf(i)
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
//                index.value = listActivity.indexOf(i)
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
    val context = LocalContext.current

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
            modifier = modifier.clickable {
                index.value = id
                Toast.makeText(context, "${index.value}", Toast.LENGTH_SHORT).show()

            }
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
            onClick = onClick
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
fun RenameDialog(
    modifier: Modifier = Modifier,
    stateForRename: String,
    onNameChange: (String) -> Unit,
    isDialogOpen: MutableState<Boolean>

) {
    if (isDialogOpen.value) {
        Dialog(onDismissRequest = { isDialogOpen.value = false }) {
            Column(
                modifier = Modifier
                    .background(
                        color = Gray15,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.meal_time),
                    style = MaterialTheme.typography.subtitle2,
                )

                Spacer(modifier = Modifier.padding(15.dp))

                OutlinedTextField(
                    value = stateForRename,
                    onValueChange = onNameChange,
                    placeholder = { Text(text = stateForRename) },
                    singleLine = true,
                )


                Spacer(modifier = Modifier.padding(15.dp))

                Divider(
                    color = Gray30.copy(alpha = 0.19f),
                    thickness = 1.dp,
                    modifier = modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.height(IntrinsicSize.Min)
                ) {
                    OutlinedMainButton(
                        text = stringResource(id = R.string.cancellation),
                        onClick = { isDialogOpen.value = false },
                        enableState = true,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Gray15,
                            contentColor = Blue007AFF,
                        ),
                        textColor = Color.Transparent,
                        modifier = Modifier.weight(0.5f)
                    )

                    Divider(
                        color = Gray30.copy(alpha = 0.19f),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )

                    OutlinedMainButton(
                        text = stringResource(id = R.string.done),
                        onClick = { /*TODO*/ },
                        enableState = true,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Gray15,
                            contentColor = Blue007AFF,
                        ),
                        textColor = Color.Transparent,
                        modifier = Modifier.weight(0.5f)
                    )

                }
            }
        }
    }
}

