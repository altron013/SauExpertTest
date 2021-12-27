package com.example.sauexpert.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sauexpert.R
import com.example.sauexpert.model.TextOfTabData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton

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
            0 -> Box(modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                ) {

                    ProfileStatSectionGroup()
                    Spacer(modifier = Modifier.height(spaceHeight))
                    ProfileStatSection(
                        descriptionText = stringResource(id = R.string.city),
                        text = "name of City"
                    )
                    Spacer(modifier = Modifier.height(spaceHeight))
                    ProfileStatSection(
                        descriptionText = stringResource(id = R.string.phone),
                        text = "+7_777_777_7777",
                    )
                    Spacer(modifier = Modifier.height(spaceHeight))
                    ProfileStatSection(
                        descriptionText = stringResource(id = R.string.organization),
                        text = "name of Organization"
                    )
                    Spacer(modifier = Modifier.height(spaceHeight))


                }

                MainButton(
                    text = stringResource(id = R.string.begin_diagnostic),
                    onClick = { /*TODO*/ },
                    enableState = true,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
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
            style = MaterialTheme.typography.body2
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
    val inactiveColor = Gray30
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
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }

        }

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
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {

            Text(
                text = stringResource(id = R.string.gender),
                style = MaterialTheme.typography.h5

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
                    style = MaterialTheme.typography.body1,
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
                style = MaterialTheme.typography.h5

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
                    style = MaterialTheme.typography.body1,
                    modifier = modifier
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Start
                )

            }
        }


    }

}

@Composable
fun ProfileStatSection(
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
            style = MaterialTheme.typography.h5

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
                style = MaterialTheme.typography.body1,
                modifier = modifier
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Start
            )

        }
    }
}
