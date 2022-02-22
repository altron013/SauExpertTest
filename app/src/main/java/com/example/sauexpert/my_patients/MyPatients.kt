package com.example.sauexpert.my_patients

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.GrayF0F
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.ui.theme.Surface1F7
import com.example.sauexpert.ui.theme.SurfaceF9
import com.example.sauexpert.widgets.compose.MainButton
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
//            Keyboard.Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                SearchView(textState)
//                Spacer(modifier = Modifier.padding(4.dp))
//                Card(
//                    modifier = Modifier
//                        .height(44.dp)
//                        .width(44.dp),
//                    backgroundColor = Gray15,
//                    shape = RoundedCornerShape(10.dp)
//                ) {
//                    Icon(
//                        Icons.Default.DoubleArrow, contentDescription = "",
//                    )
//                }
//            }
            Button(onClick = {}
            ) {
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Column(modifier = Modifier.fillMaxSize()) {
            }
        }
    }
}

@Composable
fun EmptyTabItem() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

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
            .padding(4.dp),
    )
}

data class NewPatientList(
    val image: Painter,
    val name: String
)

@Composable
fun NewPatientContent() {
    val newPatientList = listOf(
        NewPatientList(
            image = painterResource(R.drawable.logo_light_1),
            name = "Ерасыл Нурахметов"
        ),
        NewPatientList(
            image = painterResource(id = R.drawable.ic_home),
            name = "Нурхан Шаяметов"
        ),
        NewPatientList(
            image = painterResource(id = R.drawable.ic_home),
            name = "Ерасыл Нурахметов"
        )
    )
    Column(modifier=Modifier.padding(top=18.dp)) {
        for (i in newPatientList) {
            NewPatientCard(i.image, i.name)
        }
    }
}

@Composable
fun NewPatientCard(painter: Painter, text: String) {
    Card(
        modifier = Modifier
            .padding( top = 8.dp, bottom = 8.dp)
            .fillMaxWidth(),
        backgroundColor = SurfaceF9,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            Modifier.padding(16.dp),
        )
        {
            Image(
                painter = painter,
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Column {
                Text(text = text, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(5.dp))
                MainButton(text = "Осмотреть", onClick = { /*TODO*/ }, enableState = true)
            }
        }
    }
}

data class AllPatientsData(
    val avatar: Painter,
    val name: String,
    val illnessDescription: String
)

@ExperimentalMaterialApi
@Composable
fun AllPatientsContent() {
    val allPatientsList = listOf(
        AllPatientsData(
            avatar = painterResource(id = R.drawable.ic_patient),
            name = "Irina Rechkova",
            illnessDescription = "I11.9 Гипертоническая болезнь"
        ),
        AllPatientsData(
            avatar = painterResource(id = R.drawable.ic_home),
            name = "Irina Rechkova",
            illnessDescription = "I11.9 Гипертоническая болезнь"
        ),
        AllPatientsData(
            avatar = painterResource(id = R.drawable.ic_patient),
            name = "Irina Rechkova",
            illnessDescription = "I11.9 Гипертоническая болезнь"
        )
    )
    val bottomSheetScaffoldState2 = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        drawerScrimColor = MaterialTheme.colors.onSurface.copy(alpha = 0.40f),
        scaffoldState = bottomSheetScaffoldState2,
        sheetShape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
        sheetContent = {
            // ButtonActionView()
            ChoosePriority()
        }
    ) {
        Column(modifier = Modifier.padding(top = 24.dp)) {
            Text(text = "Азия Финанс", fontSize = 15.sp)
            Spacer(modifier = Modifier.padding(6.dp))
            for (i in allPatientsList) {
                AllPatientsCard(
                    onClick = {
                        coroutineScope.launch {
                            if (bottomSheetScaffoldState2.bottomSheetState.isCollapsed) {
                                bottomSheetScaffoldState2.bottomSheetState.expand()
                            } else {
                                bottomSheetScaffoldState2.bottomSheetState.collapse()
                            }
                        }
                    },
                    image = i.avatar,
                    name = i.name,
                    illnessDescription = i.illnessDescription
                )
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = "Азия Финанс", fontSize = 15.sp)
            Spacer(modifier = Modifier.padding(6.dp))
            for (i in allPatientsList) {
                AllPatientsCard(
                    onClick = {
                        coroutineScope.launch {
                            if (bottomSheetScaffoldState2.bottomSheetState.isCollapsed) {
                                bottomSheetScaffoldState2.bottomSheetState.expand()
                            } else {
                                bottomSheetScaffoldState2.bottomSheetState.collapse()
                            }
                        }
                    },
                    image = i.avatar,
                    name = i.name,
                    illnessDescription = i.illnessDescription
                )
            }
        }
    }
}

@Composable
fun AllPatientsCard(onClick: () -> Unit, image: Painter, name: String, illnessDescription: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = GrayF0F
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = image,
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .weight(0.5f)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Column(Modifier.weight(3f)) {
                Text(text = name, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(1.dp))
                Text(text = illnessDescription, fontSize = 12.sp)
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = "",
                modifier = Modifier
                    .clickable { onClick.invoke() }
                    .weight(1f))
        }
    }
}
