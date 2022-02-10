package com.example.sauexpert.widgets.compose.Toolbars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Green117259
import com.example.sauexpert.ui.theme.SauExpertTheme
import com.example.sauexpert.ui.theme.SauExpertTypography

@Composable
fun MainToolbar(
    modifier: Modifier = Modifier,
    text: String? = null,
    onBackClick: () -> Unit,
    onSortClick: (() -> Unit)? = null,
    onFilterClick: (() -> Unit)? = null,
    onMenuClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = text ?: "",
                maxLines = 1,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 65.dp)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = null,
                    tint = Color.Blue,
                )
            }
        },
        actions = {
            if (onSortClick != null) {
                IconButton(onClick = { onSortClick.invoke() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
            if (onFilterClick != null) {
                IconButton(onClick = { onFilterClick.invoke() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
            if (onMenuClick != null) {
                IconButton(onClick = { onMenuClick.invoke() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp
    )
}


@Composable
fun SubscriptionToolBar(
    modifier: Modifier = Modifier,
    text: String = "",
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp
    ) {
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = null,
                    tint = Green117259,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(start = 8.dp)
                        .clickable { onBackClick() }
                )

                Text(
                    text = "Назад",
                    color = Green117259,
                    style = SauExpertTypography.body1,
                    modifier = Modifier.clickable { onBackClick() })
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    style = SauExpertTypography.body1,
                    modifier = Modifier

                )
            }

            Row(
                Modifier
                    .fillMaxHeight()
                    .weight(0.5f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {}

        }
    }
}

@Composable
fun ActionToolBarRow(
    iconBackClick: ImageVector? = null,
    textBackClick: String? = null,
    onBackClick: () -> Unit,
    iconRightClick: ImageVector? = null,
    textRightClick: String? = null,
    onRightClick: () -> Unit,
    titleText: String,
    colorBackClick: Color = Color.Black,
    colorRightClick: Color = Color.Black,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(0.5f),
        ) {
            iconBackClick?.let {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = it,
                        contentDescription = "Back",
                        tint = colorBackClick,
                    )
                }
            }

            if (iconBackClick != null && textBackClick != null) {
                Spacer(modifier = Modifier.width(5.dp))
            }


            textBackClick?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    color = colorBackClick,
                    modifier = modifier
                        .clickable {
                            onBackClick()
                        }
                )
            }
        }

        Text(
            text = titleText,
            style = MaterialTheme.typography.subtitle2,
        )

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(0.5f),
        ) {
            textRightClick?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    color = colorRightClick,
                    modifier = modifier
                        .clickable {
                            onRightClick()
                        }
                )
            }

            if (iconRightClick != null && textRightClick != null) {
                Spacer(modifier = Modifier.width(10.dp))
            }

            iconRightClick?.let {
                IconButton(onClick = { onRightClick() }) {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = colorRightClick,
                    )
                }
            }
        }
    }
}

@Composable
fun ActionToolBarColumn(
    iconBackClick: ImageVector? = null,
    textBackClick: String? = null,
    titleText: String? = null,
    colorBackClick: Color = Color.Black,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        iconBackClick?.let {
            Icon(
                imageVector = it,
                contentDescription = "Back",
                tint = colorBackClick,
                modifier = Modifier.clickable {
                    onBackClick()
                }
            )
        }

        textBackClick?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.body1,
                color = colorBackClick,
                modifier = Modifier
                    .clickable {
                        onBackClick()
                    }
            )
        }

        if (iconBackClick != null && titleText != null) {
            Spacer(modifier = Modifier.height(20.dp))
        }

        titleText?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.h4,
                fontSize = 28.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ToolbarPrev() {
    SauExpertTheme() {
//        SubscriptionToolBar(onBackClick = { /*TODO*/ }, text = "Подписка")
//        MainToolbar(onBackClick = {}, text = " test")
        ActionToolBarRow(
            titleText = stringResource(id = R.string.profile),
            iconBackClick = Icons.Filled.ArrowBack,
            textBackClick = stringResource(R.string.cancel),
            onBackClick = {},
            onRightClick = {}
        )
    }
}

