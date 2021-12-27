package com.example.sauexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.example.otp_example.CustomKeyboardOTPScreen
import com.example.sauexpert.on_board_screen.OnBoardInfoScreen
import com.example.sauexpert.profile.ProfileScreen
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SauExpertTheme {
                // GetAccessScreen()
                // LandingScreen()
                // RegisterOtp()
                CustomKeyboardOTPScreen()
                //RegisterPatientScreen()
                //RegisterPatientScreen2()
                // RegisterDoctorScreen()
                // LoginScreen()
                // RegisterDoctorScreen()
//                ProfileScreen()
//                OnBoardInfoScreen()
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