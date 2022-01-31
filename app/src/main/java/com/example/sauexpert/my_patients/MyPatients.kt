package com.example.sauexpert.my_patients

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.*
import com.example.sauexpert.widgets.compose.MainButtonS
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun MyPatients() {
    val coroutineScope = rememberCoroutineScope()
    SauExpertTheme() {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(30.dp))
            Text(
                text = "Мои пациенты",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(8.dp))
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
                        Icons.Default.DoubleArrow, contentDescription = "",
                    )
                }
            }
            Button(onClick = {

            }
            ) {
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Column(modifier = Modifier.fillMaxSize()) {
                //Tabs(tabTitles)
            }
        }
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
//             indicator = {
//                     TabDefaults.Indicator(
//                         color = Color.Red,
//                         height = 4.dp,
//                         modifier = Modifier
//                             .tabIndicatorOffset(it[selectedSeason])
//                     )
//
//            }

            modifier = Modifier.fillMaxWidth(),
            edgePadding = 0.dp,
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = title) }
                )
            }
        }
        when (tabIndex) {
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

@Composable
fun EmptyTabItem() {
    Column(

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Нет пациентов",
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Попросите администратора добавить ваших пациентов")
    }
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(11.dp)
                    .size(19.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            // .padding(15.dp)
                            .size(17.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.DarkGray,
            cursorColor = Color.DarkGray,
            leadingIconColor = Color.DarkGray,
            trailingIconColor = Color.DarkGray,
            backgroundColor = Surface1F7,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                "Поиск по ФИО пациента",
                fontSize = 17.sp,
                modifier = Modifier.padding(4.dp)
            )
        },
        modifier = Modifier
            .height(46.dp)
            .padding(0.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(textState)
}

@Composable
fun NewPatientContent() {
    Column() {
        NewPatientCard()
        Spacer(modifier = Modifier.padding(5.dp))
        NewPatientCard()
        Spacer(modifier = Modifier.padding(5.dp))
        NewPatientCard()
    }
}

@Composable
fun NewPatientCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        backgroundColor = SurfaceF9
    ) {
        Row(
            Modifier.padding(16.dp),
            //horizontalArrangement = Arrangement.spacedBy(5.dp)
        )
        {
            //Image(painter = painterResource(id = R.drawable.ic_patient),contentDescription = "")
            Image(
                painter = painterResource(R.drawable.logo_light_1),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Column() {
                Text(text = "Ерасыл Нурахметов")
                Spacer(modifier = Modifier.padding(5.dp))
                MainButtonS(text = "Осмотреть", onClick = { /*TODO*/ }, enableState = true)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun AllPatientsContent() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    Column() {
        Text(text = "Азия Финанс")
        AllPatientsCard(onClick = {
            coroutineScope.launch {
                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                } else {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        })
        Spacer(modifier = Modifier.padding(5.dp))
        AllPatientsCard(onClick = {
            coroutineScope.launch {
                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                } else {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        })
        Spacer(modifier = Modifier.padding(5.dp))
        AllPatientsCard(onClick = {
            coroutineScope.launch {
                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                } else {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        })
        Text(text = "Банк ЦентрКредит")
        Spacer(modifier = Modifier.padding(5.dp))
        AllPatientsCard(onClick = {
            coroutineScope.launch {
                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                } else {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        })
        Spacer(modifier = Modifier.padding(5.dp))
        AllPatientsCard(onClick = {
            coroutineScope.launch {
                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                } else {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        })
        Spacer(modifier = Modifier.padding(5.dp))
        AllPatientsCard(onClick = {
            coroutineScope.launch {
                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                } else {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        })
    }
}

@Composable
fun AllPatientsCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        backgroundColor = GrayF0F
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            //Image(painter = painterResource(id = R.drawable.ic_patient),contentDescription = "")
            Image(
                painter = painterResource(R.drawable.logo_light_1),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            //Spacer(modifier = Modifier.padding(5.dp))
            Column() {
                Text(text = "Ерасыл Нурахметов", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(1.dp))
                Text(text = "Ерасыл Нурахметов")
            }
            // Spacer(modifier = Modifier.padding(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = "",
                modifier = Modifier.clickable { onClick })
        }
    }
}
