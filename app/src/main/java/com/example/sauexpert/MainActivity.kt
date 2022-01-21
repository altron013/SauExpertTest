package com.example.sauexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.sauexpert.navigation.BottomNavItem
import com.example.sauexpert.navigation.BottomNavigationBar
import com.example.sauexpert.navigation.Navigation
import com.example.sauexpert.ui.theme.SauExpertTheme
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
                    }
                ) {
                    Navigation(navController = navController)
                }

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