package com.example.sauexpert.signup_doctor

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.signup_patient.AuthItem
import com.example.sauexpert.ui.theme.Gray15
import com.example.sauexpert.ui.theme.GrayF0F
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.ui.theme.SurfaceBlue
import com.example.sauexpert.ui.theme.SurfaceBlue
import com.example.sauexpert.widgets.compose.MainButtonM
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.sample.pager.HorizontalPagerIndicator1

data class InfoCardFields(
    val image: Painter,
    val title: String,
    val desc: String
)
@ExperimentalPagerApi
@Composable
fun RegisterDoctorScreen() {
    //val pagerState = rememberPagerState(pageCount = 3)
    val name = remember {
        mutableStateOf(TextFieldValue())
    }
    val mobileNo = remember {
        mutableStateOf(TextFieldValue())
    }
    val context = LocalContext.current
    val errorState = remember { mutableStateOf(false) }
    val errorStateNo = remember { mutableStateOf(false) }


    val infoCardList = listOf(
        InfoCardFields(
            painterResource(id = R.drawable.ic_heart_text_square_fill),
            stringResource(id = R.string.online_access_to_medical_data),
            stringResource(id = R.string.save_record_look_medical)),
        InfoCardFields(
            painterResource(id = R.drawable.ic_staroflife_circle_fill),
            stringResource(
                id = R.string.manage_the_treatment_process
            ),
            stringResource(
                id = R.string.prescribe_medications_tests_monitor_implementation
            )
        ),
        InfoCardFields(
            painterResource(id = R.drawable.ic_message_circle_fill),
            stringResource(
                id = R.string.be_always_in_touch_with_your_patients
            ),
            stringResource(id = R.string.keep_in_constant_contact_with_chat)
        )
    )

    val pagerState = rememberPagerState(
        pageCount = infoCardList.size,
        initialOffscreenLimit = 2,
        initialPage = 0,
        infiniteLoop = false
    )

    //SauExpertTheme() {
        Column() {
            SubmitApplicationToolbar(
                stringResource(id = R.string.close),
                stringResource(id = R.string.submit_application)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .verticalScroll(rememberScrollState()),

                // horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(top = 24.dp))

                Box() {
                    HorizontalPager(
                        state = pagerState,
                        // Add 32.dp horizontal padding to 'center' the pages
                        // Add some horizontal spacing between items
                        itemSpacing = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) { page ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(242.dp),
                            shape = RoundedCornerShape(12.dp),
                            backgroundColor = SurfaceBlue
                        )
                        {
                            Text(
                                text = stringResource(id = R.string.what_will_be_available_to_me),
                                modifier = Modifier.padding(24.dp),
                                style = MaterialTheme.typography.subtitle2,
                                fontWeight = FontWeight.SemiBold
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = infoCardList[page].image,
                                    contentDescription = null,
                                    modifier = Modifier.weight(0.4f)
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = infoCardList[page].title,
                                        style = MaterialTheme.typography.subtitle2,
                                        modifier = Modifier.padding(top = 20.dp),
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Spacer(modifier = Modifier.padding(4.dp))
                                    Text(
                                        text = infoCardList[page].desc,
                                        style = MaterialTheme.typography.body2,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }
                    }
                    HorizontalPagerIndicator1(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(top = 22.dp))
                MessageCard(text = stringResource(id = R.string.leave_your_contact_numbers___))
                AuthItem(
                    textTitle = stringResource(id = R.string.your_name),
                    name = name,
                    nameErrorState = errorState,
                    placeholder = stringResource(
                        id = R.string.write_name
                    )
                )
                AuthItem(
                    textTitle = stringResource(id = R.string.your_phone_number),
                    name = mobileNo,
                    nameErrorState = errorState,
                    placeholder = stringResource(id = R.string.enter_phone_number)
                )
                Spacer(modifier = Modifier.padding(top = 26.dp))
                MainButtonM(
                    text = stringResource(id = R.string.send),
                    onClick = {
                        when {
                            name.value.text.isEmpty() -> {
                                errorState.value = true
                            }
                            mobileNo.value.text.isEmpty() -> {
                                errorStateNo.value = true
                            }
//                        password.value.text.isEmpty() -> {
//                            passwordErrorState.value = true
//                        }
//                        confirmPassword.value.text.isEmpty() -> {
//                            confirmPasswordErrorState.value = true
//                        }
//                        confirmPassword.value.text != password.value.text -> {
//                            confirmPasswordErrorState.value = true
//                        }
                            else -> {
                                Toast.makeText(
                                    context,
                                    "Registered successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
//                        navController.navigate("login_screen") {
//                            popUpTo(navController.graph.startDestinationId)
//                            launchSingleTop = true
//                        }
                            }
                        }
                    },
                    enableState = true
                )
                Spacer(modifier = Modifier.padding(top = 70.dp))
            }
        }
    }
//}

@Composable
fun SubmitApplicationToolbar(leftText: String, centerText: String) {
    Row(
        Modifier
            //.padding(16.dp)
            .fillMaxWidth()
            .border(0.5.dp, Gray15)
            .background(GrayF0F)
    ) {
        Text(
            text = leftText,
            color = Color.Red,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        )
        Text(
            text = centerText,
            style = MaterialTheme.typography.subtitle2,
            color = Color.Black,
            modifier = Modifier
                .weight(2f)
                .padding(start = 0.dp, 16.dp)
        )
    }
}


@Composable
fun InfoCard(cardIcon: Painter, header: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(242.dp),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = SurfaceBlue
    )
    {
        Text(
            text = stringResource(id = R.string.what_will_be_available_to_me),
            modifier = Modifier.padding(24.dp),
            style = MaterialTheme.typography.subtitle2
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = cardIcon,
                contentDescription = null,
                modifier = Modifier.weight(0.4f)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = header,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.padding(top = 20.dp)
                )
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun MessageCard(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = SurfaceBlue,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(top = 16.dp, bottom = 16.dp)
            .clickable { },
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_message_box),
            tint = Color.Black,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = text,
            modifier = Modifier.weight(4f),
            fontSize = 13.sp
            //style = MaterialTheme.typography.h5
        )
    }
}
