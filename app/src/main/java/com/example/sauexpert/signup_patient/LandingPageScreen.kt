package com.example.sauexpert.signup_patient

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.buttons.OutlinedMainButton


@ExperimentalComposeUiApi
@Composable
fun LandingScreen() {
    //SauExpertTheme {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                Modifier
                    .padding(top = 40.dp)
            ) {
                Image(
                    modifier = Modifier
                        .height(53.dp)
                        .width(67.dp),
                    painter = painterResource(id = R.drawable.logo_light_1),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Image(
                    modifier = Modifier
                        .height(53.dp)
                        .width(133.dp),
                    painter = painterResource(id = R.drawable.logo_light_2),
                    contentDescription = null
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_landing_page_image),
                modifier = Modifier.size(365.dp,262.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Уникальный Health App", style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.padding(3.dp))
            Text(
                textAlign = TextAlign.Center,
                text = "Следите за здоровьем, замеряйте свои показатели, управляйте медицинской карточкой",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.padding(20.dp))
            MainButton(
                text = stringResource(id = R.string.login),
                onClick = {},
                enableState = true,
            )
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedMainButton(
                text = stringResource(id = R.string.signup),
                onClick = { },
                enableState = true,

                )

        }
    }
//}


@Composable
fun AuthToolbar(text: String) {
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
            text = text,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 26.dp, start = 16.dp, bottom = 26.dp)
        )
//        Text(
//            text = text,
//            style = MaterialTheme.typography.h4,
//            modifier = Modifier.padding(top = 26.dp, start = 16.dp, bottom = 26.dp)
//        )
        Box(
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Row(Modifier.padding(start = 16.dp, top = 24.dp)) {

            }
        }

    }
}