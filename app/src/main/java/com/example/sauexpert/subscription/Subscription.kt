package com.example.sauexpert.subscription

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.diagnosis.SwitchWithText
import com.example.sauexpert.ui.theme.*
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.Toolbars.SubscriptionToolBar

@Composable
fun SubscriptionContent() {
    Column {
        SubscriptionToolBar(onBackClick = {}, text = "Подписка")
        Divider(color = GrayBCBBC1, thickness = 0.5.dp)

        Text(
            text = stringResource(R.string.subscription_notice, "11.08.2022", "1234"),
            fontSize = 17.sp,
            style = SauExpertTypography.caption,
            color = Black333333,
            maxLines = 2,
            modifier = Modifier.padding(all = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SwitchWithText(
            text = "Включить автопродление",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = GrayBCBBC1, thickness = 0.5.dp)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.subscription_info),
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            style = SauExpertTypography.button,
            maxLines = 3,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))

        MainButton(
            text = "Оплатить",
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        )
    }
}

@Composable
fun SubscriptionNotification(text: String, color: Color) {
    Box(
        modifier = Modifier
            .height(85.dp)
            .fillMaxWidth()
            .background(color = color),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text, maxLines = 2,
            color = Black333333,
            modifier = Modifier.padding(horizontal = 80.dp, vertical = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 25.sp
        )
    }
}

@Composable
fun SubscriptionSuccess() {
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.congratulations),
            fontWeight = FontWeight.W700,
            color = Black333333,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_subscription_success),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Вы успешно оплатили подписку на 30 дней с 13 сентября по 13 октября.",
            fontWeight = FontWeight.W700,
            color = Black333333,
            fontSize = 17.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SubPrev() {
    SauExpertTheme {
        SubscriptionSuccess()
    }
}