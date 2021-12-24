package com.example.sauexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.example.otp_example.OTPScreen
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalPagerApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SauExpertTheme() {
                // GetAccessScreen()
                // LandingScreen()
                // RegisterOtp()
                OTPScreen()
                //RegisterPatientScreen()
                //RegisterPatientScreen2()
                // RegisterDoctorScreen()
                // LoginScreen()
                // RegisterDoctorScreen()
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