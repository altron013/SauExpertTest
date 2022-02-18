package com.example.sauexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.sauexpert.my_patients.AddGroup
import com.example.sauexpert.my_patients.NewGroup
import com.example.sauexpert.navigation.BottomNavItem
import com.example.sauexpert.navigation.BottomNavigationBar
import com.example.sauexpert.navigation.Navigation
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.widgets.compose.snackbars.DefaultSnackbar
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @ExperimentalPagerApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SauExpertTheme {

                val scaffoldState = rememberScaffoldState()

                val navController = rememberNavController()

                val modalBottomSheetState = rememberModalBottomSheetState(
                    ModalBottomSheetValue.Hidden
                )

                var currentBottomSheet: BottomSheetType? by remember {
                    mutableStateOf(
                        BottomSheetType.INITIAL
                    )
                }


                val coroutineScope = rememberCoroutineScope()

                val closeSheet = {
                    coroutineScope.launch { modalBottomSheetState.hide() }
                }

                val openSheet = {
                    coroutineScope.launch { modalBottomSheetState.show() }
                }

                ModalBottomSheetLayout(
                    modifier = Modifier.fillMaxSize(),
                    sheetState = modalBottomSheetState,
                    sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    sheetContent = {
                        currentBottomSheet?.let {
                            Box(modifier = Modifier.defaultMinSize(minHeight = 1.dp)) {
                                SheetLayout(
                                    bottomSheetType = it,
                                    closeSheet = { closeSheet() },
                                    onBackPressed = { currentBottomSheet = BottomSheetType.NEW_GROUP },
                                    onNextPressed = { currentBottomSheet = BottomSheetType.ADD_GROUP }
                                )
                            }
                        }
                    },
                    content = {
                        Scaffold(
                            bottomBar = {
                                BottomNavigationBar(
                                    items = listOf(
                                        BottomNavItem(
                                            name = stringResource(id = R.string.home),
                                            route = "home",
                                            icon = painterResource(id = R.drawable.ic_home)
                                        ),
                                        BottomNavItem(
                                            name = stringResource(id = R.string.my_patients),
                                            route = "myPatients",
                                            icon = painterResource(id = R.drawable.ic_patients),
                                            badgeCount = 23
                                        ),
                                        BottomNavItem(
                                            name = stringResource(id = R.string.messages),
                                            route = "settings",
                                            icon = painterResource(id = R.drawable.ic_mail)
                                        ),
                                        BottomNavItem(
                                            name = stringResource(id = R.string.notifications),
                                            route = "notification",
                                            icon = painterResource(id = R.drawable.ic_notification)
                                        ),
                                        BottomNavItem(
                                            name = stringResource(id = R.string.profile),
                                            route = "profile",
                                            icon = painterResource(id = R.drawable.ic_profile_squared)
                                        )
                                    ),
                                    navController = navController,
                                    onItemClick = {
                                        navController.navigate(it.route)
                                    }
                                )
                            },
                            scaffoldState = scaffoldState,
                            snackbarHost = { scaffoldState.snackbarHostState }
                        ) {
                            Box(modifier = Modifier.padding(it)) {
                                Navigation(
                                    navController = navController,
                                    scaffoldState = scaffoldState,
                                    openSheet = { openSheet() },
                                    toNewGroup = { currentBottomSheet = BottomSheetType.NEW_GROUP},
                                    toActionView = { currentBottomSheet = BottomSheetType.ACTION_VIEW}
                                )
                                DefaultSnackbar(
                                    snackbarHostState = scaffoldState.snackbarHostState,
                                    onDismiss = {
                                        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                    },
                                    modifier = Modifier.align(Alignment.TopCenter)
                                )
                            }
                        }
                    })
            }
        }
    }
}

@Composable
fun SheetLayout(
    bottomSheetType: BottomSheetType,
    closeSheet: () -> Unit,
    onBackPressed: () -> Unit,
    onNextPressed: () -> Unit
) {
    when (bottomSheetType) {
        BottomSheetType.ACTION_VIEW -> {
//            ButtonActionView()
        }
        BottomSheetType.NEW_GROUP -> NewGroup(closeSheet, onNextPressed)
        BottomSheetType.INITIAL -> {}
        BottomSheetType.ADD_GROUP -> AddGroup(onBackPressed)
    }
}

enum class BottomSheetType {
    ACTION_VIEW, NEW_GROUP, INITIAL, ADD_GROUP //Add new type to add new bottom sheet
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SauExpertTheme {
        Greeting("Android")
    }
}