package com.example.sauexpert.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.model.TextOfTabData

@Composable
fun ProfileScreen() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val spaceHeight = 16.dp

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(Modifier.padding(20.dp))
        ProfileSection()
        Spacer(modifier = Modifier.height(spaceHeight))
        TabView(
            TextOfTab = listOf(
                TextOfTabData(
                    text = stringResource(id = R.string.info)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.diagnosis)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.indicators)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.analysis)
                ),
                TextOfTabData(
                    text = stringResource(id = R.string.inspections)
                )
            )


        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {

                StatSection()
                Spacer(modifier = Modifier.height(spaceHeight))
                ProfileStat(
                    descriptionText = stringResource(id = R.string.city),
                    text = "name of City"
                )
                Spacer(modifier = Modifier.height(spaceHeight))
                ProfileStat(
                    descriptionText = stringResource(id = R.string.phone),
                    text = "+7_777_777_7777",
                )
                Spacer(modifier = Modifier.height(spaceHeight))
                ProfileStat(
                    descriptionText = stringResource(id = R.string.organization),
                    text = "name of Organization"
                )
                Spacer(modifier = Modifier.height(spaceHeight))

                ButtonForBottomForProfileScreen()

            }

        }


    }

}


@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {

        RoundImage(
            image = painterResource(id = R.drawable.avatar),
            modifier = Modifier
                .size(80.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileDescription(
            displayName = "User",
            description = stringResource(id = R.string.no_diagnosis)
        )
    }
}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )

}

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.7.sp,
            fontSize = 17.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description,
            letterSpacing = 0.7.sp,
            fontSize = 13.sp
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
    var inactiveColor = Color(0xFF7777777)
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Red,
        modifier = modifier.fillMaxWidth()
    ) {
        TextOfTab.forEachIndexed { index, item ->
            Tab(selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)

                }
            ) {
                Text(
                    text = item.text,
                    letterSpacing = 0.7.sp,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }

        }

    }
}

@Composable
fun StatSection(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {

            Text(
                text = stringResource(id = R.string.gender),
                fontSize = 12.sp

            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = modifier
                    .size(width = 165.dp, height = 40.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(5.dp))
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.male),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    modifier = modifier
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Start
                )

            }
        }
        Spacer(modifier = Modifier.width(12.dp))


        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {

            Text(
                text = stringResource(id = R.string.age),
                fontSize = 12.sp

            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = modifier
                    .size(width = 165.dp, height = 40.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(5.dp))
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = "22",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    modifier = modifier
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Start
                )

            }
        }


    }

}

@Composable
fun ProfileStat(
    descriptionText: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {

        Text(
            text = descriptionText,
            fontSize = 12.sp

        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = modifier
                .fillMaxWidth()
                .size(width = 343.dp, height = 40.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(5.dp))
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                modifier = modifier
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Start
            )

        }
    }
}

@Composable
fun ButtonForBottomForProfileScreen(modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.profile),
//            contentDescription = "chat",
//            modifier = Modifier
//                .background(Color.LightGray)
//                .padding(10.dp)
//                .size(50.dp)
//        )
//
//        Spacer(modifier = Modifier.width(50.dp))

        Button(
            onClick = {},
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            modifier = Modifier.size(width = 343.dp, height = 50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.begin_diagnostic),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
        }

    }
}
