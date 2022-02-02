package com.example.sauexpert.inspection

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.my_patients.ButtonWithTextColorChange
import com.example.sauexpert.profile.ProfileForInspection
import com.example.sauexpert.profile.TopBarForInspectionScreen
import com.example.sauexpert.ui.theme.Blue007AFF
import com.example.sauexpert.ui.theme.Gray15
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.SauExpertTheme
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun DailyRoutineScreen() {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    SauExpertTheme() {
        BottomSheetScaffold(
            drawerScrimColor = MaterialTheme.colors.onSurface.copy(alpha = 0.40f),
            scaffoldState = bottomSheetScaffoldState,
//            sheetShape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
            sheetContent = {
                BottomSheetContentForDailyRoutine(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }

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
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun BottomSheetContentForDailyRoutine(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
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
                onClick = {},
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
                onClick = {},
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

        CardForMainDailyRoutine("Завтрак", "09:00", onClick = onClick)

        Spacer(modifier = Modifier.height(12.dp))

        CardForMainDailyRoutine("Обед", "09:00", onClick = onClick)

        Spacer(modifier = Modifier.height(12.dp))

        CardForMainDailyRoutine("Ужин", "09:00", onClick = onClick)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.sleep),
            style = MaterialTheme.typography.subtitle2,
        )

        Spacer(modifier = Modifier.height(12.dp))

        CardForMainDailyRoutine("Подъём", "09:00", onClick = onClick)

        Spacer(modifier = Modifier.height(12.dp))

        CardForMainDailyRoutine("Сон", "09:00", onClick = onClick)


    }
}

@Composable
fun CardForMainDailyRoutine(
    title: String,
    text: String,
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

