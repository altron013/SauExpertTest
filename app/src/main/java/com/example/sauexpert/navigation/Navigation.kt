package com.example.sauexpert.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sauexpert.auth.LoginScreen
import com.example.sauexpert.home.HomeScreen
import com.example.sauexpert.my_patients.MyPatients
import com.example.sauexpert.my_patients.MyPatients2
import com.example.sauexpert.patients_bracelet_indicators.CriticalCaseIndicators
import com.example.sauexpert.patients_bracelet_indicators.GraphicIndicators
import com.example.sauexpert.patients_bracelet_indicators.WorkAroundExample
import com.example.sauexpert.my_patients.MyPatientsNewGroup
import com.example.sauexpert.signup_doctor.RegisterDoctorScreen
import com.example.sauexpert.signup_patient.GetAccessScreen
import com.example.sauexpert.signup_patient.RegisterPatientScreen2
import com.example.sauexpert.ui.theme.GrayF0F
import com.example.sauexpert.well_being.GlucoseLevelScreen
import com.example.sauexpert.well_being.WellBeingDescription
import com.example.sauexpert.well_being.WellBeingScreen
import com.google.accompanist.pager.ExperimentalPagerApi


data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: Painter,
    val badgeCount: Int = 0
)

@ExperimentalPagerApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController, scaffoldState: ScaffoldState) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // HomeScreen()
            // LoginScreen()
            GraphicIndicators()
           // WorkAroundExample(criticalIndicatorsList)
        }
        composable("myPatients") {
            //MyPatients2()
            MyPatientsNewGroup(scaffoldState = scaffoldState)=======
          
           // MyPatients2()
            // RegisterDoctorScreen()
        }
        composable("settings") {
            GetAccessScreen()
        }
        composable("notification") {
            WellBeingDescription()
            // GlucoseLevelScreen()
        }
        composable("profile") {
            //LoginScreen()
            WellBeingScreen()
        }
    }

}


@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = Modifier.border(
            border = BorderStroke(
                width = 1.dp,
                Color.LightGray
            )
        ), backgroundColor = GrayF0F
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgeBox(
                                badgeContent = {
                                    Text(text = item.badgeCount.toString())
                                },
                                backgroundColor = Color.Red,
                                contentColor = Color.White
                            ) {

                                Icon(item.icon, contentDescription = null)
//                                Text(text=item.name,
//                                    textAlign = TextAlign.Center,
//                                    fontSize = 10.sp)

                            }
                        } else {
                            Icon(item.icon, contentDescription = null)
                        }

                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp
                        )
                    }
                }
            )
        }

    }
}