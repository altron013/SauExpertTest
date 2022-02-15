package com.example.sauexpert.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Surface1F7
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBar


@Composable
fun HistoryOfTreatmentScreen() {
    var historyState by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                color = Gray30.copy(alpha = 0.19f)
            )
            .padding(16.dp)

    ) {
        ActionToolBar(
            titleText = stringResource(id = R.string.treatment_history),
            iconBackClick = Icons.Default.ArrowBack,
            onBackClick = {},
            onRightClick = {}
        )

        Spacer(modifier = Modifier.width(18.dp))

        OutlinedTextFieldWithBackground(
            textForHint = stringResource(R.string.search_history),
            textState = historyState,
            onTextChange = { historyState = it }
        )


    }

}


@Composable
fun SearchViewForHistory(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(11.dp)
                    .size(19.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            // .padding(15.dp)
                            .size(17.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.DarkGray,
            cursorColor = Color.DarkGray,
            leadingIconColor = Color.DarkGray,
            trailingIconColor = Color.DarkGray,
            backgroundColor = Surface1F7,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                "Поиск по ФИО пациента",
                fontSize = 17.sp,
                modifier = Modifier.padding(4.dp)
            )
        },
        modifier = Modifier
            .height(46.dp)
            .padding(4.dp),
    )
}