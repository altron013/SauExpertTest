package com.example.sauexpert.my_patients

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.profile.RoundImage
import com.example.sauexpert.ui.theme.Blue007AFF
import com.example.sauexpert.ui.theme.Gray15
import com.example.sauexpert.ui.theme.Red435B
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.widgets.compose.MainButtonM
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun MyPatients2() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    val tabTitles = listOf("Новые", "Все", "Гипертония", "Сахарный диабет", "Новая группа")
    SauExpertTheme() {
        BottomSheetScaffold(
            drawerScrimColor = MaterialTheme.colors.onSurface.copy(alpha = 0.40f),
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = 0.dp,
            sheetShape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
            sheetContent = {
                //ButtonActionView()
                //ChoosePriority()
                InspectionFailure(onClick = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }
                }
                )
            }
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Spacer(modifier = Modifier.padding(29.dp))
                Text(
                    text = "Мои пациенты",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(9.dp))
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SearchView(textState)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Card(
                        modifier = Modifier
                            .height(44.dp)
                            .width(44.dp),
                        backgroundColor = Gray15,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_left_arrow_right),
                            contentDescription = "",
                            modifier = Modifier
                                .clickable {
                                    coroutineScope.launch {
                                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                            bottomSheetScaffoldState.bottomSheetState.expand()
                                        } else {
                                            bottomSheetScaffoldState.bottomSheetState.collapse()
                                        }
                                    }
                                }
                                .padding(12.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Column(modifier = Modifier.fillMaxSize()) {
                    Tabs(tabTitles)
                }
            }
        }
    }
}

@Composable
fun ButtonWithTextColorChange(
    text: String,
    onClick: () -> Unit,
    enableState: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    contentColor: Color = Color.Green
) {
    Button(
        modifier = Modifier
            //.padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(height = 56.dp),
        enabled = enableState,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            contentColor = contentColor
        ),
        elevation = ButtonDefaults.elevation(2.dp),
        shape = shape
    ) {
        Text(
            text = text, fontSize = 18.sp
        )
    }
}

@Composable
fun ButtonActionView() {
    Box {
        Column(
            modifier = Modifier
            // .align(Alignment.BottomCenter)
        ) {
            ButtonWithTextColorChange(
                text = "Осмотреть пациента",
                onClick = {},
                enableState = true,
                shape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp),
                contentColor = Blue007AFF
            )
            ButtonWithTextColorChange(
                text = "Перейти к карточке",
                onClick = {},
                enableState = true,
                shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                contentColor = Blue007AFF
            )
            ButtonWithTextColorChange(
                text = "Поставить приоритет",
                onClick = {},
                enableState = true,
                shape = RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp),
                contentColor = Blue007AFF
            )
            Spacer(modifier = Modifier.padding(bottom = 9.dp))
            ButtonWithTextColorChange(
                text = "Отменить",
                onClick = {},
                enableState = true,
                contentColor = Blue007AFF
            )
        }
    }
}

@Composable
fun ChoosePriority() {
    Spacer(modifier = Modifier.padding(8.dp))
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Поставить приоритет", fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Spacer(modifier = Modifier.padding(14.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(7.dp)
                )
        ) {
            RoundImage(
                image = painterResource(id = R.drawable.avatar),
                modifier = Modifier
                    .size(48.dp)
            )
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(
                    text = "Jabe",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    text = "Description",
                    fontSize = 12.sp
                    //style = MaterialTheme.typography.subtitle2,
                )
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Jabe",
                    //style = MaterialTheme.typography.subtitle2,
                )
                Text(
                    text = "Description",
                    //style = MaterialTheme.typography.subtitle2,
                )
                Text(
                    text = "Jabe",
                    //style = MaterialTheme.typography.subtitle2,
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        MainButtonM(text = "Сохранить", onClick = { /*TODO*/ }, enableState = true)
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun InspectionFailure(onClick: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Поставить приоритет", fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Spacer(modifier = Modifier.padding(14.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(7.dp)
                )
        ) {
            RoundImage(
                image = painterResource(id = R.drawable.avatar),
                modifier = Modifier
                    .size(48.dp)
            )
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(
                    text = "Jabe",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    text = "Description",
                    fontSize = 12.sp
                )
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Вы продолжите заполнять данные  осмотра с этапа, где Вы остановились.")
        Spacer(modifier = Modifier.padding(10.dp))
        MainButtonM(text = "Продолжить осмотр", onClick = { /*TODO*/ }, enableState = true)
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "Закрыть",
            color = Color.Red,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .clickable { onClick() }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun Tabs(tabTitles: List<String>) {
    var tabIndex by remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = tabIndex, backgroundColor = Color.Transparent,
            contentColor = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            edgePadding = 0.dp,
            indicator = {
                TabRowDefaults.Indicator(
                    color = Red435B,
                    height = 2.dp,
                    modifier = Modifier.tabIndicatorOffset(it[tabIndex])
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                if (title == "Новая группа") {
                    LeadingIconTab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = title, color = Red435B) },
                        icon = {
                            Image(
                                painter = painterResource(R.drawable.ic_plus_circle_conflict),
                                contentDescription = "tabIcon",
                                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                                modifier = Modifier
                                    .size(13.dp)
                                    .clip(CircleShape)
                            )
                        })
                } else {
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = title) },
                    )
                }
            }
        }
        when (tabIndex){
            0 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                EmptyTabItem()
            }
            1 -> NewPatientContent()
            2 -> AllPatientsContent()
        }
    }
}
