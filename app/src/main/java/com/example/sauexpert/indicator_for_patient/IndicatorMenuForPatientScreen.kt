package com.example.sauexpert.indicator_for_patient

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
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray30

@Composable
fun IndicatorMenuForPatientScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                color = Gray30.copy(alpha = 0.19f)
            )
            .padding(horizontal = 20.dp)
    ) {
        TitleForIndicatorMenuForPatient()
        Spacer(modifier = Modifier.height(20.dp))

        CardMenuForIndicatorMenuScreen(
            text = stringResource(R.string.report),
            painter = painterResource(R.drawable.ic_indicator_report)
        )

        Spacer(modifier = Modifier.height(7.dp))

        CardMenuForIndicatorMenuScreen(
            text = stringResource(R.string.graphs),
            painter = painterResource(R.drawable.ic_indicator_graph)
        )

        Spacer(modifier = Modifier.height(100.dp))

        BraceletIndicatorSection()

    }
}

@Composable
fun TitleForIndicatorMenuForPatient(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp)
    ) {
        Text(
            text = stringResource(id = R.string.indicators),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.W700,
            fontSize = 30.sp
        )

        Text(
            text = stringResource(id = R.string.synchronization),
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.W700,
            fontSize = 15.sp
        )
    }
}

@Composable
fun CardMenuForIndicatorMenuScreen(
    modifier: Modifier = Modifier,
    text: String,
    painter: Painter
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(vertical = 16.dp, horizontal = 24.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = modifier.size(30.dp)
        )

        Spacer(modifier = Modifier.width(20.dp))


        Text(
            text = text,
            style = MaterialTheme.typography.subtitle2
        )

        Spacer(modifier = Modifier.weight(1f))


        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.Black,
            modifier = modifier.size(20.dp)
        )

    }
}

@Composable
fun BraceletIndicatorSection(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.bracelet_indicator),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.W700,
            fontSize = 24.sp,
            modifier = modifier.padding(horizontal = 9.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        CardMenuForIndicatorMenuScreen(
            text = stringResource(R.string.sleep),
            painter = painterResource(R.drawable.ic_indicator_sleep)
        )

        Spacer(modifier = Modifier.height(7.dp))

        CardMenuForIndicatorMenuScreen(
            text = stringResource(R.string.sp02),
            painter = painterResource(R.drawable.ic_indicator_spo2)
        )

        Spacer(modifier = Modifier.height(7.dp))

        CardMenuForIndicatorMenuScreen(
            text = stringResource(R.string.hrv),
            painter = painterResource(R.drawable.ic_indicator_hrv)
        )

        Spacer(modifier = Modifier.height(7.dp))

        CardMenuForIndicatorMenuScreen(
            text = stringResource(R.string.pressure),
            painter = painterResource(R.drawable.ic_indicator_pressure)
        )

        Spacer(modifier = Modifier.height(7.dp))

        CardMenuForIndicatorMenuScreen(
            text = stringResource(R.string.pulse),
            painter = painterResource(R.drawable.ic_indicator_pulse)
        )


    }

}
