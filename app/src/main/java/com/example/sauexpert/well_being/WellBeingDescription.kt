package com.example.sauexpert.well_being

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Green57C3A7
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.Toolbars.MainToolbar
import com.example.sauexpert.widgets.compose.buttons.OutlinedMainButton


@ExperimentalAnimationApi
@Composable
fun WellBeingDescription() {
    Column() {
        var sad = remember { mutableStateOf(TextFieldValue()) }
        MainToolbar(onBackClick = {}, text = stringResource(id = R.string.arterial_pressure))
        Column(
            Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            WellBeingGeneral(
                heading = stringResource(id = R.string.arterial_pressure),
                date = "8 июня 2020 | 10:30",
                patient = "Анатолий Смирнов"
            )
            ArterialForm(sad)
            Spacer(Modifier.padding(14.dp))
            WellBeingCheck()
            Spacer(Modifier.padding(5.dp))
            DefineWellBeing()
        }
    }
}

@Composable
fun TextWithAsterisk() {

}

@Composable
fun ArterialForm(sad: MutableState<TextFieldValue>) {
    var code: List<Char> by remember { mutableStateOf(listOf()) }
    var sad = remember { mutableStateOf(TextFieldValue()) }
    var dad = remember { mutableStateOf(TextFieldValue()) }
    var pulse = remember { mutableStateOf(TextFieldValue()) }

    WellBeingTextField(textFieldContent = sad, name = "САД")
    WellBeingTextField(textFieldContent = dad, name = "ДАД")
    WellBeingTextField(textFieldContent = pulse, name = "Пульс")
    Text(text = "* обязательное поле", fontSize = 15.sp)
}

@Composable
fun WellBeingTextField(
    textFieldContent: MutableState<TextFieldValue>,
    name: String
) {
    val focus = remember { mutableStateOf(false) }
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(color = if (!focus.value) Color.Gray else Green57C3A7)) {
            append(name)
        }
        withStyle(style = SpanStyle(color = Color.Red)) {
            append("*")
        }
    }
    )
    Spacer(Modifier.padding(6.dp))

    TextField(
        value = textFieldContent.value,
        onValueChange = {
//            if (passwordErrorState.value) {
//                passwordErrorState.value = false
//            }
            textFieldContent.value = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                if (focus.value != it.isFocused) {
                    focus.value = it.isFocused
                }
            }

            .border(
                BorderStroke(
                    width = 1.dp,
                    color =
                    // if (passwordErrorState.value) Color.Red else
                    Color.Transparent,

                    ),
                shape = RoundedCornerShape(10.dp)

            ),
        // isError = passwordErrorState.value,
        label = null,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Green57C3A7,
            cursorColor = Green57C3A7,
        ),
        placeholder = {
            Text(
                "Введите данные",
                style = MaterialTheme.typography.body1
            )
        },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )
    Spacer(Modifier.padding(10.dp))
}

@Composable
fun WellBeingCheck() {
    var selectedOption by remember {
        mutableStateOf("")
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }
    Text(text = "Хорошо ли Вы себя чувствуете?", fontSize = 17.sp)
    Spacer(Modifier.padding(12.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedMainButton(
            text = "Да",
            onClick = {
                onSelectionChange("Да")
            },
            enableState = true,
            modifier = Modifier
                .clickable {
                    onSelectionChange("Да")
                }
                .weight(0.5f)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color =
                        if ("Да" == selectedOption) {
                            Color.Red
                        } else {
                            Color.Transparent
                        },
                    ),
                    shape = RoundedCornerShape(
                        size = 8.dp,
                    )
                )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedMainButton(
            text = "Нет",
            onClick = {
                onSelectionChange("Нет")
            },
            enableState = true,
            modifier = Modifier
                .clickable {
                    onSelectionChange("Нет")
                }
                .weight(0.5f)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color =
                        if ("Нет" == selectedOption) {
                            Color.Red
                        } else {
                            Color.Transparent
                        },
                    ),
                    shape = RoundedCornerShape(
                        size = 8.dp,
                    )
                )
        )
    }

}


@Composable
fun OutlinedActionButton(
    text: String,
    selectedOption: String,
    onSelectionChange: (String) -> Unit
) {
    OutlinedMainButton(
        text = text,
        onClick = {
            onSelectionChange(text)
        },
        enableState = true,
        modifier = Modifier
            .clickable {
                onSelectionChange(text)
            }
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color =
                    if (text == selectedOption) {
                        Color.Red
                    } else {
                        Color.Transparent
                    },
                ),
                shape = RoundedCornerShape(
                    size = 8.dp,
                )
            )
    )
}

@Composable
fun DefineWellBeing() {
    var selectedOption by remember {
        mutableStateOf("")
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }
    var illnesses = listOf(
        "ГОЛОВНАЯ БОЛЬ",
        "НАРУШЕНИЕ ЗРЕНИЯ",
        "СЛАБОСТЬ В КОНЕЧНОСТЯХ",
        "ГОЛОВОКРУЖЕНИЕ",
        "НАРУШЕНИЕ КООРДИНАЦИИ",
        "ТОШНОТА/РВОТА"
    )
    Text(text = "Опишите самочувствие", fontSize = 17.sp)
    Column(
        modifier = Modifier
            .padding(
                end = 36.dp, start = 36.dp
            )
    ) {
        for (it in illnesses) {
            Spacer(Modifier.padding(10.dp))
            OutlinedMainButton(text = it,
                onClick = { onSelectionChange(it) },
                enableState = true,
                modifier = Modifier
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color =
                            if (it == selectedOption) {
                                Color.Red
                            } else {
                                Color.Gray
                            },
                        ),
                        shape = RoundedCornerShape(
                            size = 8.dp,
                        )
                    ))
        }
    }
    Spacer(Modifier.padding(10.dp))
    MainButton(
        text = stringResource(id = R.string.confirm),
        onClick = { /*TODO*/ },
        enableState = selectedOption == null
    )
}