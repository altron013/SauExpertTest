package com.example.sauexpert.widgets.compose.Toolbars

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainToolbar(
    modifier: Modifier = Modifier,
    text: String = "",
    onBackClick: () -> Unit,
    onSortClick: (() -> Unit)? = null,
    onFilterClick: (() -> Unit)? = null,
    onMenuClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                maxLines = 1,
                textAlign = TextAlign.Justify,
                style= MaterialTheme.typography.subtitle2,
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
