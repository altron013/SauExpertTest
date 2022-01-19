package com.example.sauexpert.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton

@Composable
fun InspectionPatientScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
            .padding(16.dp)
    ) {
        TopBarForInspectionPatientScreen(userName = "User")
        Spacer(modifier = Modifier.height(42.dp))
        PreviousInspectionsStat()
        Spacer(modifier = Modifier.height(12.dp))
        PreviousInspectionsStat2()


    }

}

@Composable
fun TopBarForInspectionPatientScreen(
    userName: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = modifier.align(Alignment.CenterStart)
                .clickable {
                }
        )

        Text(
            text = userName,
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun PreviousInspectionsStat(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.previous_inspections),
            style = MaterialTheme.typography.h5,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(7.dp)
                )
        ) {
            AnalysisInspectionsStatField("Ларионов Игорь Викторович")

            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 2.dp,
                modifier = modifier
                    .padding(horizontal = 24.dp)
            )

            AnalysisInspectionsDateField()
        }
    }
}


@Composable
fun PreviousInspectionsStat2(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(7.dp)
            )
    ) {
        AnalysisInspectionsStatField("Келимбетов Аскар Ахметович")

        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 2.dp,
            modifier = modifier
                .padding(horizontal = 24.dp)
        )

        AnalysisInspectionsDateField()

        MainButton(
            text = stringResource(id = R.string.supply_detail),
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
        )

    }
}

@Composable
fun AnalysisInspectionsStatField(
    doctorName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = doctorName,
                style = MaterialTheme.typography.subtitle2
            )


            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
                tint = Color.Black,
                modifier = modifier.size(20.dp)
            )

        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(R.string.conducted_inspection),
            style = MaterialTheme.typography.h5,
            color = Gray30
        )

    }
}

@Composable
fun AnalysisInspectionsDateField(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {

        Text(
            text = stringResource(R.string.date_of_inspections),
            style = MaterialTheme.typography.h5,
            color = Gray30
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "15 Февраля 2021",
            style = MaterialTheme.typography.subtitle1,
        )

    }
}
