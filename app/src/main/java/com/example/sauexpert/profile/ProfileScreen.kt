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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.CardListItemData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBar

@Composable
fun ProfileScreen() {

    val spaceHeight = 16.dp
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Gray30.copy(alpha = 0.19f))
            .padding(16.dp)
    ) {
        ActionToolBar(
            titleText = stringResource(id = R.string.profile),
            textBackClick = stringResource(id = R.string.close),
            colorBackClick = Color.Red,
            sizeText = dimensions.fontSizeSubtitle_2,
            onBackClick = {},
            onRightClick = {}
        )

        Spacer(modifier = Modifier.height(dimensions.grid_5))

        ProfileSection(dimensions = dimensions)

        Spacer(modifier = Modifier.height(dimensions.grid_3))

        CardItem(
            cardList = listOf(
                CardListItemData(
                    image = painterResource(id = R.drawable.ic_heart_circle),
                    text = stringResource(id = R.string.inspections)
                ),
                CardListItemData(
                    image = painterResource(id = R.drawable.ic_folder),
                    text = stringResource(id = R.string.treatment_history)
                ),
                CardListItemData(
                    image = painterResource(id = R.drawable.ic_clock),
                    text = stringResource(id = R.string.monthly_report)
                )
            ),
            dimensions = dimensions
        )

        Spacer(modifier = Modifier.height(spaceHeight))

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ProfileStatSectionGroup(
                textForGender = stringResource(R.string.female),
                textForAge = "42"
            )

            Spacer(modifier = Modifier.height(spaceHeight))

            ProfileStatSection(
                title = stringResource(id = R.string.city).toUpperCase(Locale.current),
                text = "Тараз"
            )

            Spacer(modifier = Modifier.height(spaceHeight))

            ProfileStatSection(
                title = stringResource(id = R.string.phone).toUpperCase(Locale.current),
                text = "+7 (777) 380-99-17",
                textIsLink = true
            )

            Spacer(modifier = Modifier.height(spaceHeight))

            ProfileStatSection(
                title = stringResource(id = R.string.organization).toUpperCase(Locale.current),
                text = "АО “Центргарант”"
            )

            Spacer(modifier = Modifier.height(spaceHeight))

            ProfileStatSection(
                title = stringResource(id = R.string.last_doctor_who_inspect).toUpperCase(Locale.current),
                text = "Келимбетов А.С. (вы)"
            )

            Spacer(modifier = Modifier.height(spaceHeight))

            ProfileStatSection(
                title = stringResource(id = R.string.last_day_of_check_up).toUpperCase(Locale.current),
                text = "29 Ноября 2021"
            )
        }
    }
}

@Composable
fun ProfileSection(
    dimensions: Dimensions,
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
                .size(dimensions.imageSize_0)
        )
        Spacer(modifier = Modifier.height(12.dp))

        ProfileDescription(
            displayName = "User",
            description = stringResource(id = R.string.no_diagnosis),
            dimensions = dimensions
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
    dimensions: Dimensions
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = displayName,
            style = MaterialTheme.typography.subtitle2,
            fontSize = dimensions.fontSizeSubtitle_2
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            fontSize = dimensions.fontSizeBody_2
        )

    }
}

@Composable
fun CardItem(
    cardList: List<CardListItemData>,
    dimensions: Dimensions,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier.fillMaxWidth()) {
        items(cardList.size) {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = modifier
                    .padding(end = 12.dp)
            ) {
                Column(
                    modifier = modifier
                        .border(
                            width = 1.dp,
                            color = Gray30.copy(alpha = 0.35f),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(top = 14.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
                ) {
                    cardList[it].image?.let { it1 ->
                        Image(
                            painter = it1,
                            contentDescription = null,
                            modifier = modifier.size(dimensions.iconSize_4)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = cardList[it].text,
                        style = MaterialTheme.typography.h5,
                        fontSize = dimensions.fontSizeCustom_3
                    )
                }
            }
        }
    }

}

@Composable
fun ProfileStatSectionGroup(
    textForGender: String,
    textForAge: String,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = (configuration.screenWidthDp.dp / 2) - 20.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()

    ) {
        ProfileStatSection(
            title = stringResource(id = R.string.gender).toUpperCase(Locale.current),
            text = textForGender,
            modifier = Modifier.width(screenWidth)
        )

        ProfileStatSection(
            title = stringResource(id = R.string.age).toUpperCase(Locale.current),
            text = textForAge,
            modifier = Modifier.width(screenWidth)
        )
    }
}


@Composable
fun ProfileStatSection(
    title: String,
    text: String,
    textIsLink: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 15.dp, vertical = 11.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                color = if (textIsLink) Color.Blue else Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterStart)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProf() {
    SauExpertTheme {
        ProfileScreen()
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
