package com.example.sauexpert.bracelet_indicator

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray15
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton


@ExperimentalComposeUiApi
@Composable
fun BraceletIndicatorScreen() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                color = Gray30.copy(alpha = 0.19f),
                shape = RoundedCornerShape(7.dp)
            )
            .padding(16.dp)
    ) {
        TopBarForBracelet()
        Spacer(modifier = Modifier.height(28.dp))
        TabView(
            TextOfTab = listOf(
                TextOfTabData(
                    text = stringResource(id = R.string.sleep)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.sp02)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.hrv)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.pressure)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.pulse)
                )
            )


        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp)
            ) {
                SleepScreen()
                MainButton(
                    text = stringResource(id = R.string.range_customize),
                    onClick = { /*TODO*/ },
                    enableState = true,
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.BottomCenter)
                )
            }
            1 -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp)
            ) {
                Sp02Screen()
                MainButton(
                    text = stringResource(id = R.string.range_customize),
                    onClick = { /*TODO*/ },
                    enableState = true,
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.BottomCenter)
                )

            }
            2 -> Box(
                modifier = Modifier.fillMaxSize()
            ) {
                HRVScreen()
            }
            3 -> Box(
                modifier = Modifier.fillMaxSize()

            ) {
                PressureScreen()
            }
            4 -> Box(
                modifier = Modifier.fillMaxSize()
            ) {
                PulseScreen()
            }
        }
    }
}


@Composable
fun TopBarForBracelet(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = modifier.align(Alignment.CenterStart)
                .clickable {
                }
        )

        Text(
            text = stringResource(id = R.string.bracelet),
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier.align(Alignment.Center)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_calendar_icon),
            contentDescription = null,
            modifier = modifier.align(Alignment.CenterEnd)
        )


    }
}

@Composable
fun TabView(
    modifier: Modifier = Modifier,
    TextOfTab: List<TextOfTabData>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val shape = RoundedCornerShape(8.dp)
    val backgroundColor = Color(220, 220, 223)

    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = backgroundColor,
        contentColor = Color.Transparent,
        divider = {},
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = shape)
            .border(width = 2.dp, color = backgroundColor, shape = shape)
    ) {
        TextOfTab.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)

                },
                modifier = modifier
                    .background(
                        if (selectedTabIndex == index) Color.White else backgroundColor,
//                        if (selectedTabIndex == index) RoundedCornerShape(10.dp) else RoundedCornerShape(0.dp),
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Text(
                    text = item.text,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp)

                )
            }

        }

    }
}


@Composable
fun AnalysisStatField(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(11.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
        )

        Text(
            text = value,
            style = MaterialTheme.typography.body1,
        )

    }
}

@Composable
fun AnalysisStatFieldWithIconAtBeg(
    title: String,
    value: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(11.dp)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = Color.Red,
            modifier = modifier.size(10.dp)
        )

        Spacer(modifier = Modifier.width(3.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.body1,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = value,
            style = MaterialTheme.typography.body1,
        )

    }
}

@Composable
fun AnalysisStatFieldWithIconAtEnd(
    title: String,
    value: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier
) {
    val myId = "inlineContent"
    val text = buildAnnotatedString {
        append(title)
        appendInlineContent(myId, "[icon]")
    }

    val inlineContent = mapOf(
        Pair(
            myId,
            InlineTextContent(
                Placeholder(
                    width = 20.sp,
                    height = 20.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                )
            ) {

                Icon(imageVector, "", tint = Color.Yellow)
            }
        )
    )


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(11.dp)
    ) {

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            inlineContent = inlineContent
        )

        Text(
            text = value,
            style = MaterialTheme.typography.body1,
        )

    }
}



