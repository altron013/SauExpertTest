package com.example.sauexpert.profile

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton

@Composable
fun InspectionPatientScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray30.copy(alpha = 0.19f))
            .padding(16.dp)
    ) {
        TopBarForInspectionPatientScreen(userName = "User")
        Spacer(modifier = Modifier.height(42.dp))
        PreviousInspectionsStat()
        Spacer(modifier = Modifier.height(12.dp))
        PreviousInspectionsStat2()


    }

}

@Composable
fun TopBarForInspectionPatientScreen(
    userName: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = modifier.align(Alignment.CenterStart)
                .clickable {
                }
        )

        Text(
            text = userName,
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun PreviousInspectionsStat(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.previous_inspections),
            style = MaterialTheme.typography.h5,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(7.dp)
                )
        ) {
            AnalysisInspectionsStatField("Ларионов Игорь Викторович")

            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 2.dp,
                modifier = modifier
                    .padding(horizontal = 24.dp)
            )

            AnalysisInspectionsDateField()
        }
    }
}


@Composable
fun PreviousInspectionsStat2(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(7.dp)
            )
    ) {
        AnalysisInspectionsStatField("Келимбетов Аскар Ахметович")

        Divider(
            color = Gray30.copy(alpha = 0.19f),
            thickness = 2.dp,
            modifier = modifier
                .padding(horizontal = 24.dp)
        )

        AnalysisInspectionsDateField()

        MainButton(
            text = stringResource(id = R.string.supply_detail),
            onClick = { /*TODO*/ },
            enableState = true,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
        )

    }
}

@Composable
fun AnalysisInspectionsStatField(
    doctorName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = doctorName,
                style = MaterialTheme.typography.subtitle2
            )


            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "",
                tint = Color.Black,
                modifier = modifier.size(20.dp)
            )

        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(R.string.conducted_inspection),
            style = MaterialTheme.typography.h5,
            color = Gray30
        )

    }
}

@Composable
fun AnalysisInspectionsDateField(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {

        Text(
            text = stringResource(R.string.date_of_inspections),
            style = MaterialTheme.typography.h5,
            color = Gray30
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "15 Февраля 2021",
            style = MaterialTheme.typography.subtitle1,
        )

    }
}


@Composable
fun TopBarForInspectionScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = modifier
                .clickable {
                }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.general_inspection),
            style = MaterialTheme.typography.h4,
        )
    }
}

@Composable
fun profileForInspection(
    userName: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(7.dp)
            )
    ) {
        RoundImage(
            image = painterResource(id = R.drawable.avatar),
            modifier = Modifier
                .size(55.dp).padding(12.dp)
        )

        Text(
            text = userName,
            style = MaterialTheme.typography.subtitle2,
        )

        Spacer(modifier = Modifier.weight(1f))

        CircularProgressBar(percentage = 0.4f, number = 100)
        Spacer(modifier = Modifier.width(16.dp))
    }

}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    radius: Dp = 12.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 3.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                -90f,
                360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = (curPercentage.value * number).toInt().toString(),
            style = MaterialTheme.typography.h5,
        )

    }
}

@Composable
fun FillTextFiled(
    textForHint: String
) {
    var textStateField by remember { mutableStateOf("") }

    val colorOfTextField = TextFieldDefaults.textFieldColors(
//                    textColor = Color.Gray,
        disabledTextColor = Color.Transparent,
        backgroundColor = Gray30.copy(alpha = 0.19f),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )


    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = textStateField,
        onValueChange = {
            textStateField = it
        },
        placeholder = {
            Text(text = textForHint)
        },
        colors = colorOfTextField,
        shape = RoundedCornerShape(8.dp)
    )
}


@Composable
fun dropDownMenuWithFieldBackGround(
    dataList: List<String>
) {
    Box {
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(top = 8.dp)
                .background(
                    color = Gray30.copy(alpha = 0.19f),
                    shape = RoundedCornerShape(4.dp)
                )
        )

        OutlineTextFildWithDropdownMenu(suggestions = dataList)
    }
}

@Composable
fun OutlineTextFildWithDropdownMenu(
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
            enabled = false,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { expanded = !expanded })
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            label = {},
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(color = Color.Black)
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
//                        color = Color.Red
                    )
                }
            }
        }
    }
}
