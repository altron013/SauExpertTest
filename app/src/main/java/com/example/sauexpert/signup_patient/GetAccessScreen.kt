package com.example.sauexpert.signup_patient

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray15
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.widgets.compose.MainButtonM

@Composable
fun GetAccessScreen() {
    val color = remember { mutableStateOf(Color.Blue) }
    val scrollState = rememberScrollState()
    val selectedRole = remember { mutableStateOf("") }
   // SauExpertTheme() {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F2F7))
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                modifier = Modifier.padding(top = 26.dp, start = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.registration),
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 26.dp, start = 16.dp, bottom = 26.dp)
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                Column(Modifier.padding(start = 16.dp, top = 32.dp, end = 16.dp)) {
                    ButtonRole(
                        "Я -пациент",
                        image = painterResource(id = R.drawable.ic_doctor),
                        selectedGender = selectedRole
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                    ButtonRole(
                        text = "Я-доктор",
                        image = painterResource(id = R.drawable.ic_doctor),
                        selectedGender = selectedRole
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                    MainButtonM(
                        text = stringResource(id = R.string.proceed),
                        onClick = { /*TODO*/ },
                        enableState = true
                    )
                }
            }
        }
    }
//}


@Composable
fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        },
        title = {
            // Text(text = stringResource(R.string.login))
        },
        backgroundColor = Color.White
    )
}

@Composable
fun ButtonRole(text: String, image: Painter, selectedGender: MutableState<String>) {
    //SauExpertTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(144.dp)
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Gray15), shape = RoundedCornerShape(10.dp))
                .clickable { selectedGender.value = text }
        ) {
            Row(modifier = Modifier.padding(start = 30.dp)) {
                Row(modifier = Modifier.padding(top = 40.dp)) {
                    RadioButton(selected = selectedGender.value == text, onClick = {
                        selectedGender.value = text }
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(text = text,modifier = Modifier.clickable { selectedGender.value = text })
                }
                Spacer(modifier = Modifier.padding(30.dp))
                Image(
                    painter = image, contentDescription = null,
                    //Modifier.size(48.dp)
                )
            }
        }
    }
//}