package com.example.sauexpert.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray30

@Composable
fun ModifyInspectionInfoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
    ) {
        TopBarForInspectionScreen()
        Spacer(modifier = Modifier.height(20.dp))
        profileForInspection(userName = "user")
        Spacer(modifier = Modifier.height(32.dp))
//        FillInfoStatFiled()
        InfoStatInspectionChange(
            titleIllness = "Мочевыделительная система",
            subtitleIllness = "МОЧЕИСПУСКАНИЕ"
        )
    }

}


@Composable
fun InfoStatInspectionChange(
    titleIllness: String,
    subtitleIllness: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = titleIllness,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = subtitleIllness,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(20.dp))

            dropDownMenu(
                suggestions = listOf(
                    "Option 1", "Option 2", "Option 3", "Option 4",
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
//            DropDownMenuForText()

            FillTextFiled(stringResource(R.string.description))
            Spacer(modifier = Modifier.height(20.dp))
            FillTextFiled(stringResource(R.string.deep_sleep))

        }
    }

}

@Composable
fun dropDownMenu(
    suggestions: List<String>
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown

    Column() {
        OutlinedTextField(
            value = selectedText,
//            enabled = false,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = {},
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) {
                    textfieldSize.width.toDp()
                })
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = label
                        expanded = false
                    }
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.body1,
//                        color = Color.Black
                    )
                }
            }
        }
    }
}






//@Composable
//fun DropDownMenuForText(
//    modifier: Modifier = Modifier
//) {
//    val stateHolder = rememberExposedMenuStateHolder()
//    Column {
//        Box {
//            OutlinedTextField(
//                value = stateHolder.value,
//                onValueChange = {},
//                label = { Text(text = "Label") },
//                trailingIcon = {
//                    Icon(
//                        imageVector = stateHolder.icon,
//                        contentDescription = null,
//                        modifier = modifier.clickable {
//                            stateHolder.onEnabled(!(stateHolder.enabled))
//                        }
//                    )
//                },
//                modifier = modifier.onGloballyPositioned {
//                    stateHolder.onSize(it.size.toSize())
//                }
//            )
//            DropdownMenu(
//                expanded = stateHolder.enabled,
//                onDismissRequest = {
//                    stateHolder.onEnabled(false)
//                },
//                modifier = modifier
//                    .width(with(LocalDensity.current) {
//                        stateHolder.size.width.toDp()
//                    })
//            ) {
//
//                stateHolder.items.forEachIndexed { index, s ->
//                    DropdownMenuItem(
//                        onClick = {
//                            stateHolder.onSelectedIndex(index)
//                            stateHolder.onEnabled(false)
//                        }
//
//                    ) {
//                        Text(
//                            text = s
//                        )
//
//                    }
//                }
//
//
//            }
//
//        }
//    }
//}
//
//
//class ExposedDropMenuStateHolder {
//    var enabled by mutableStateOf(false)
//    var value by mutableStateOf("")
//    var selectedIndex by mutableStateOf(-1)
//    var size by mutableStateOf(Size.Zero)
//    val icon: ImageVector
//        @Composable get() = if (enabled) {
//            Icons.Filled.KeyboardArrowUp
//        } else {
//            Icons.Filled.KeyboardArrowDown
//        }
//
//
//    val items = (1..5).map {
//        "option $it"
//    }
//
//    fun onEnabled(newValue: Boolean) {
//        enabled = newValue
//    }
//
//    fun onSelectedIndex(newValue: Int) {
//        selectedIndex = newValue
//        value = items[selectedIndex]
//    }
//
//    fun onSize(newValue: Size) {
//        size = newValue
//    }
//}
//
//@Composable
//fun rememberExposedMenuStateHolder() = remember() {
//    ExposedDropMenuStateHolder()
//}