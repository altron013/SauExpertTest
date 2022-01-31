package com.example.sauexpert.widgets.compose.Toolbars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Preview(showBackground = true)
@Composable
fun ToolbarPrev() {
    SauExpertTheme() {
        SubscriptionToolBar(onBackClick = { /*TODO*/ }, text = "Подписка")
    }
}

