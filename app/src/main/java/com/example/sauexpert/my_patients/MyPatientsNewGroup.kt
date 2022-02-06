package com.example.sauexpert.my_patients

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.TextFieldDefaults.BackgroundOpacity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.sauexpert.BottomSheetType
import com.example.sauexpert.R
import com.example.sauexpert.SheetLayout
import com.example.sauexpert.navigation.BottomNavItem
import com.example.sauexpert.navigation.BottomNavigationBar
import com.example.sauexpert.navigation.Navigation
import com.example.sauexpert.ui.theme.*
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.snackbars.DefaultSnackbar
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class, androidx.compose.animation.ExperimentalAnimationApi::class,
    androidx.compose.animation.ExperimentalAnimationApi::class,
    androidx.compose.animation.ExperimentalAnimationApi::class,
    androidx.compose.ui.ExperimentalComposeUiApi::class,
    androidx.compose.ui.ExperimentalComposeUiApi::class,
    androidx.compose.foundation.ExperimentalFoundationApi::class
)
@ExperimentalMaterialApi
@Composable
fun MyPatientsNewGroup(scaffoldState: ScaffoldState, openSheet: () -> Job, toNewGroup: () -> Unit, toActionView: () -> Unit) {

    val tabTitles = listOf("Новые", "Все", "Гипертония", "Новая группа")

    val coroutineScope = rememberCoroutineScope()

    val textStateMain = remember { mutableStateOf(TextFieldValue("")) }


    val showSnackOnText = {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(textStateMain.value.text)
        }
    }

    val textState = remember { mutableStateOf(TextFieldValue("")) }

    SauExpertTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    Spacer(modifier = Modifier.padding(29.dp))
                    Text(
                        text = stringResource(R.string.my_patients),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(9.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        SearchViewNewGroup(
                            textStateMain,
                            Modifier,
                        ) { showSnackOnText() }
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
                                        toActionView()
                                        openSheet()
                                    }
                                    .padding(12.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Column(modifier = Modifier.fillMaxSize()) {
                        TabsNewGroup(
                            tabTitles,
                            openSheet = { openSheet() },
                            onBottomSheetChange = { toNewGroup() })
                    }
                }
            }
        )
    }
}



@Composable
fun AddGroup(onBackPressed: () -> Unit, showSnackBar: () -> Job) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 40.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, start = 8.dp)
                    .clickable { onBackPressed() }
                    .align(alignment = Alignment.CenterStart)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "",
                )

                Text(
                    text = stringResource(R.string.back),
                    style = SauExpertTypography.body1,
                    modifier = Modifier.padding(start = 5.dp),
                    color = Red435B
                )
            }

            Text(
                text = stringResource(R.string.new_group),
                style = SauExpertTypography.subtitle2,
                color = BlackAccent,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .padding(top = 16.dp)
            )
        }

        val textState = remember { mutableStateOf(TextFieldValue("")) }
        val buttonEnabled = remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                value = textState.value, onValueChange = { value ->
                    textState.value = value
                    buttonEnabled.value = value.text.isNotEmpty() && value.text.isNotBlank()
                },
                placeholder = {
                    Text(
                        stringResource(R.string.name_group),
                        fontSize = 17.sp,
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .wrapContentSize(align = Alignment.CenterStart)
                            .padding(0.dp)

                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.DarkGray.copy(LocalContentAlpha.current),
                    cursorColor = Red435B,
                    leadingIconColor = Gray50,
                    trailingIconColor = Color.DarkGray,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            )

            MainButton(
                text = stringResource(R.string.create_group),
                onClick = { showSnackBar()},
                enableState = true,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 24.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(300.dp))
        }

    }
}

@Composable
fun NewGroup(
    closeSheet: () -> Unit,
    onNextPressed: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { closeSheet() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Red435B
                ),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
                    .weight(0.9f, false),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            ) {
                Text(text = stringResource(id = R.string.cancel), style = SauExpertTypography.body1)
            }

            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(1f, false),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.new_group),
                    style = SauExpertTypography.subtitle2,
                    color = BlackAccent
                )

                Text(
                    text = stringResource(R.string.patients_chosen),
                    style = SauExpertTypography.body2,
                    color = BlackAccent
                )
            }

            Button(
                onClick = { onNextPressed() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Red435B
                ),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp)
                    .weight(0.9f, false),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.next),
                    style = SauExpertTypography.subtitle2
                )
            }
        }
        Spacer(modifier = Modifier.padding(2.dp))

        val textStateMain = remember { mutableStateOf(TextFieldValue("")) }
        SearchViewNewGroup(
            state = textStateMain,
            modifier = Modifier
                .background(color = Surface1F7)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
        )
        Spacer(modifier = Modifier.padding(1.dp))
        Row() {
            PatientChip()
            PatientChip()
            PatientChip()
        }
        AllPatients()

    }
}

@Composable
fun AllPatients() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.padding(bottom = 5.dp))
        AllPatientsCardNewGroup()
        Spacer(modifier = Modifier.padding(bottom = 5.dp))
        AllPatientsCardNewGroup()
        Spacer(modifier = Modifier.padding(bottom = 5.dp))
        AllPatientsCardNewGroup()
        Spacer(modifier = Modifier.padding(bottom = 5.dp))
        AllPatientsCardNewGroup()
        Spacer(modifier = Modifier.padding(bottom = 5.dp))
        AllPatientsCardNewGroup()
    }
}

@ExperimentalMaterialApi
@Composable
fun TabsNewGroup(tabTitles: List<String>, openSheet: () -> Job, onBottomSheetChange: () -> Unit) {
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
                if (title == stringResource(id = R.string.new_group)) {
                    LeadingIconTab(
                        selected = tabIndex == index,
                        onClick = {
                            tabIndex = index
                        },
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
                        },
                    )
                } else {
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = title) },
                    )
                }
            }
        }
        when (tabIndex) {
            0 -> EmptyTabItem()
            1 -> NewPatientContent()
            2 -> AllPatientsContent()
            3 -> {
                onBottomSheetChange()
                openSheet()
            }
        }
    }
}

@Composable
fun SearchViewNewGroup(
    state: MutableState<TextFieldValue>,
    modifier: Modifier,
    onValueChange: (() -> Job)? = null
) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
            onValueChange?.let { it() }
        },
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
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
            textColor = Color.DarkGray.copy(LocalContentAlpha.current),
            cursorColor = Color.DarkGray,
            leadingIconColor = Gray50,
            trailingIconColor = Color.DarkGray,
            backgroundColor = Surface1F7.copy(alpha = BackgroundOpacity),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.search_placeholder_patients),
                fontSize = 17.sp,
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .wrapContentSize(align = Alignment.CenterStart)
                    .padding(0.dp)

            )
        },
        modifier = modifier
            .height(55.dp)
            .padding(0.dp)
    )
}

@Composable
fun PatientChip() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Text(text = "Ерасыл", style = SauExpertTypography.body2, color = Color.Black)
        }

        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "",
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .clickable { /*TODO()*/ }
        )
    }
}

@Composable
fun AllPatientsCardNewGroup() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        backgroundColor = GrayF0F
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            //Image(painter = painterResource(id = R.drawable.ic_patient),contentDescription = "")
            Image(
                painter = painterResource(R.drawable.avatar),
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
            RoundedCheckView()
        }
    }
}


@Composable
fun RoundedCheckView(id : Int = R.drawable.ic_checkbox_checked) {
    val isChecked = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Transparent)
            .toggleable(value = isChecked.value, role = Role.Checkbox) {
                isChecked.value = it
            },
        contentAlignment = Alignment.Center
    ) {
        if (isChecked.value) {
            Image(
                painter = painterResource(id = id),
                modifier = Modifier.size(24.dp),
                contentDescription = ""
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_checkbox_unchecked),
                modifier = Modifier.size(24.dp),
                contentDescription = ""
            )
        }
    }
}

