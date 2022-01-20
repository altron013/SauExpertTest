package com.example.sauexpert.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.model.CardListItemData
import com.example.sauexpert.ui.theme.Gray30

@Composable
fun ProfileScreen() {

    val spaceHeight = 16.dp

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
            .padding(16.dp)
    ) {
        TopBarForProfile()

        Spacer(modifier = Modifier.height(40.dp))

        ProfileSection()

        Spacer(modifier = Modifier.height(24.dp))

        CardItem(
            cardList = listOf(
                CardListItemData(
                    image = painterResource(id = R.drawable.ic_heart_circle),
                    text = stringResource(id = R.string.inspections)
                ),
                CardListItemData(
                    image = painterResource(id = R.drawable.ic_folder_icon),
                    text = stringResource(id = R.string.treatment_history)
                ),
                CardListItemData(
                    image = painterResource(id = R.drawable.ic_clock_icon),
                    text = stringResource(id = R.string.monthly_report)
                )
            )
        )

        Spacer(modifier = Modifier.height(spaceHeight))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            ProfileStatSectionGroup()

            Spacer(modifier = Modifier.height(spaceHeight))

            ProfileStatSection(
                title = stringResource(id = R.string.city),
                text = "name of City"
            )

            Spacer(modifier = Modifier.height(spaceHeight))

            ProfileStatSection(
                title = stringResource(id = R.string.phone),
                text = "+7_777_777_7777"
            )

            Spacer(modifier = Modifier.height(spaceHeight))

            ProfileStatSection(
                title = stringResource(id = R.string.organization),
                text = "name of Organization"
            )

            Spacer(modifier = Modifier.height(spaceHeight))

            ProfileStatSection(
                title = stringResource(id = R.string.last_doctor_who_inspect),
                text = "name of Doctor"
            )

            Spacer(modifier = Modifier.height(spaceHeight))

            ProfileStatSection(
                title = stringResource(id = R.string.last_day_of_check_up),
                text = "29 Ноября 2021"
            )
        }
    }
}


@Composable
fun CardItem(
    cardList: List<CardListItemData>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier.fillMaxWidth()) {
        items(cardList.size) {
            Card(
                modifier = modifier
                    .padding(end = 12.dp)
            ) {
                Column(
                    modifier = modifier
                        .padding(horizontal = 11.dp, vertical = 12.dp)
                ) {
                    Image(
                        painter = cardList[it].image,
                        contentDescription = null,
                        modifier = modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = cardList[it].text,
                        style = MaterialTheme.typography.h5,
                    )
                }
            }
        }
    }

}


@Composable
fun TopBarForProfile(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.close),
            style = MaterialTheme.typography.body1,
            color = Color.Red,
            modifier = modifier.align(Alignment.CenterStart)
                .clickable {
                }
        )

        Text(
            text = stringResource(id = R.string.profile),
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier.align(Alignment.Center)
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
                .size(50.dp)
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
        contentScale = ContentScale.Crop,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
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
            style = MaterialTheme.typography.subtitle2
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )

    }
}

@Composable
fun ProfileStatSectionGroup(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()

    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.gender),
                style = MaterialTheme.typography.h5

            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = modifier
                    .size(width = 165.dp, height = 44.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(7.dp)
                    )
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.male),
                    style = MaterialTheme.typography.body1,
                    modifier = modifier
                        .align(Alignment.CenterStart)
                )

            }
        }

        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.age),
                style = MaterialTheme.typography.h5

            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = modifier
                    .size(width = 165.dp, height = 44.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(7.dp)
                    )
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = "22",
                    style = MaterialTheme.typography.body1,
                    modifier = modifier
                        .align(Alignment.CenterStart)
                )

            }
        }


    }

}


@Composable
fun ProfileStatSection(
    title: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5

        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = modifier
                .fillMaxWidth()
                .size(width = 343.dp, height = 44.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(7.dp)
                )
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                modifier = modifier
                    .align(Alignment.CenterStart)
            )
        }
    }
}

//@Composable
//fun ProfileStatSection(
//    descriptionText: String,
//    text: String,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        modifier = modifier.fillMaxWidth()
//    ) {
//
//        Text(
//            text = descriptionText,
//            style = MaterialTheme.typography.h5
//
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Box(
//            modifier = modifier
//                .fillMaxWidth()
//                .size(width = 343.dp, height = 44.dp)
//                .background(
//                    color = Color.White,
//                    shape = RoundedCornerShape(7.dp)
//                )
//                .padding(horizontal = 15.dp)
//        ) {
//            Text(
//                text = text,
//                style = MaterialTheme.typography.overline,
//                fontSize = 20.sp,
//                modifier = modifier
//                    .align(Alignment.CenterStart)
//            )
//
//        }
//    }
//}


//@Composable
//fun TabView(
//    modifier: Modifier = Modifier,
//    TextOfTab: List<TextOfTabData>,
//    onTabSelected: (selectedIndex: Int) -> Unit
//) {
//    var selectedTabIndex by remember {
//        mutableStateOf(0)
//    }
//    val inactiveColor = Gray30
//    ScrollableTabRow(
//        selectedTabIndex = selectedTabIndex,
//        backgroundColor = Color.Transparent,
//        contentColor = Color.Red,
//        modifier = modifier.fillMaxWidth()
//    ) {
//        TextOfTab.forEachIndexed { index, item ->
//            Tab(selected = selectedTabIndex == index,
//                selectedContentColor = Color.Black,
//                unselectedContentColor = inactiveColor,
//                onClick = {
//                    selectedTabIndex = index
//                    onTabSelected(index)
//
//                }
//            ) {
//                Text(
//                    text = item.text,
//                    style = MaterialTheme.typography.subtitle1,
//                    modifier = Modifier
//                        .padding(5.dp)
//                )
//            }
//
//        }
//
//    }
//}


//@Composable
//fun ProfileScreen() {
//    var selectedTabIndex by remember {
//        mutableStateOf(0)
//    }
//    val spaceHeight = 18.dp
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        TopBar(Modifier.padding(20.dp))
//        ProfileSection()
//        Spacer(modifier = Modifier.height(spaceHeight))
//        TabView(
//            TextOfTab = listOf(
//                TextOfTabData(
//                    text = stringResource(id = R.string.info)
//                ),
//                TextOfTabData(
//                    text = stringResource(id = R.string.diagnosis)
//                ),
//                TextOfTabData(
//                    text = stringResource(id = R.string.indicators)
//                ),
//                TextOfTabData(
//                    text = stringResource(id = R.string.analysis)
//                ),
//                TextOfTabData(
//                    text = stringResource(id = R.string.inspections)
//                )
//            )
//
//
//        ) {
//            selectedTabIndex = it
//        }
//        when (selectedTabIndex) {
//            0 -> Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(15.dp)
//            ) {
//                Column(
//                    modifier = Modifier
//                ) {
//
//                    ProfileStatSectionGroup()
//                    Spacer(modifier = Modifier.height(spaceHeight))
//                    ProfileStatSection(
//                        descriptionText = stringResource(id = R.string.city),
//                        text = "name of City"
//                    )
//                    Spacer(modifier = Modifier.height(spaceHeight))
//                    ProfileStatSection(
//                        descriptionText = stringResource(id = R.string.phone),
//                        text = "+7_777_777_7777",
//                    )
//                    Spacer(modifier = Modifier.height(spaceHeight))
//                    ProfileStatSection(
//                        descriptionText = stringResource(id = R.string.organization),
//                        text = "name of Organization"
//                    )
//                    Spacer(modifier = Modifier.height(spaceHeight))
//
//
//                }
//
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center,
//                    modifier = Modifier
//                        .fillMaxWidth().align(Alignment.BottomCenter)
//                ) {
//                    Box(
//                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
//                            .weight(0.17f)
//                            .background(
//                                color = Gray30.copy(alpha = 0.19f),
//                                shape = RoundedCornerShape(5.dp)
//                            )
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Message,
//                            contentDescription = null,
//                            tint = Color.Red,
//                            modifier = Modifier.align(Alignment.Center)
//
//                        )
//                    }
//
//
//                    Spacer(modifier = Modifier.width(12.dp))
//
//                    MainButton(
//                        text = stringResource(id = R.string.begin_diagnostic),
//                        onClick = { /*TODO*/ },
//                        enableState = true,
//                        modifier = Modifier.weight(0.4f)
//                    )
//
//                    Spacer(modifier = Modifier.weight(0.1f))
//                }
//            }
//        }
//    }
//
//}
