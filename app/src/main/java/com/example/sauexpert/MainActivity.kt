package com.example.sauexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.sauexpert.navigation.BottomNavItem
import com.example.sauexpert.navigation.BottomNavigationBar
import com.example.sauexpert.navigation.Navigation
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.widgets.compose.snackbars.DefaultSnackbar
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @ExperimentalPagerApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SauExpertTheme {
                // GetAccessScreen()
                // LandingScreen()
                //  RegisterOtp()
                //OTPScreen()
                //RegisterPatientScreen()
                //RegisterPatientScreen2()
                // RegisterDoctorScreen()
                // LoginScreen()
                // RegisterDoctorScreen()
                //MyPatients()

                val scaffoldState = rememberScaffoldState()

                val navController = rememberNavController()
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
                    snackbarHost = { scaffoldState.snackbarHostState}
                ) {
                    Box( modifier = Modifier.padding(it)) {
                        Navigation(navController = navController, scaffoldState = scaffoldState)
                        DefaultSnackbar(snackbarHostState = scaffoldState.snackbarHostState, onDismiss = {
                            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                        }, modifier = Modifier.align(Alignment.TopCenter))
                    }
                }
                // MyPatients2()
                //EmptyTabItem()
            }
        }
    }
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