package com.example.sauexpert.profile

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Green117259
import com.example.sauexpert.widgets.compose.buttons.MainButtonWithIcon
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
        Spacer(modifier = Modifier.height(44.dp))
        PreviousInspectionsSection()
        Spacer(modifier = Modifier.height(12.dp))
        PreviousInspectionsStat(
            doctorName = "Ларионов Игорь Викторович",
            dateOfInspection = "15 Февраля 2021", yourInspection = true
        )
        Spacer(modifier = Modifier.height(24.dp))
        MainButton(
            text = stringResource(id = R.string.new_inspections),
            onClick = { /*TODO*/ },
            enableState = true,
            icon = R.drawable.ic_plus_circle,
            backgroundColor = Color(255, 205, 211),
            textColor = Color.Red
        )
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
            modifier = modifier
                .align(Alignment.CenterStart)
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
fun PreviousInspectionsSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.previous_inspections),
            style = MaterialTheme.typography.body2,
        )

        Spacer(modifier = Modifier.height(12.dp))

        PreviousInspectionsStat(
            doctorName = "Ларионов Игорь Викторович",
            dateOfInspection = "15 Февраля 2021"
        )
    }
}


@Composable
fun PreviousInspectionsStat(
    yourInspection: Boolean = false,
    doctorName: String,
    dateOfInspection: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = Gray30.copy(alpha = 0.35f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(24.dp)
    ) {
        AnalysisInspectionsStatField(doctorName)

        Divider(
            color = Gray30.copy(alpha = 0.35f),
            thickness = 2.dp,
            modifier = modifier
                .padding(vertical = 16.dp)
        )
        AnalysisInspectionsDateField(dateOfInspection)
        if (yourInspection) {
            Spacer(modifier = Modifier.height(24.dp))

            MainButton(
                text = stringResource(id = R.string.supply_detail),
                onClick = { /*TODO*/ },
                enableState = true,
                icon = R.drawable.ic_square_and_pencil,
                buttonHeight = 35.dp,
                backgroundColor = Color(255, 205, 211),
                textColor = Color.Red
            )
        }
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
    dateOfInspection: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Text(
            text = stringResource(R.string.date_of_inspections),
            style = MaterialTheme.typography.h5,
            color = Gray30
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = dateOfInspection,
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
            fontSize = 28.sp
        )
    }
}

@Composable
fun ProfileForInspection(
    content: String,
    text: Float,
    showPercentage: Boolean = false,
    modifier: Modifier = Modifier,
    image: Painter=painterResource(id = R.drawable.avatar),
    painter:Painter?=null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 11.dp
                )
        ) {
            RoundImage(
                image = image,
                modifier = Modifier
                    .size(32.dp)
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = content,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.weight(3f)
            )
            Spacer(modifier = Modifier.weight(1f))
            if(text!=null){
            CircularProgressBar(
                percentage = text,
                number = 100,
                showPercentage = showPercentage
            )}
            else{
              Icon(
                  painter = painterResource(id = R.drawable.ic_report_davlenie),
                  contentDescription = "",
                  tint = Color.Black,
                  modifier = modifier.size(20.dp)
               )
          }
        }
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    radius: Dp = 12.dp,
    color: Color = Green117259,
    strokeWidth: Dp = 3.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    showPercentage: Boolean = false
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
                color = Gray30.copy(alpha = 0.2f),
                -90f,
                360f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                color = color,
                -90f,
                360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        if (showPercentage) {
            Text(
                text = (curPercentage.value * number).toInt().toString(),
                style = MaterialTheme.typography.h5,
            )
        }
    }
}

@Composable
fun FillTextFiled(
    textForHint: String,
    enableStatus: Boolean = true,
    textState: String,
    onTextChange: (String) -> Unit

) {
//    var textStateField by remember { mutableStateOf(textStateField) }
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
            .fillMaxWidth()
            .height(height = 55.dp),
        enabled = enableStatus,
        value = textState,
        onValueChange = onTextChange,
        placeholder = {
            Text(
                text = textForHint,
                style = MaterialTheme.typography.body1,
            )
        },
        colors = colorOfTextField,
        shape = RoundedCornerShape(10.dp)
    )
}


@Composable
fun dropDownMenuWithFieldBackGround(
    dataList: List<String>,
    enableStatus: Boolean = true
) {
    Box {
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(top = 8.dp)
                .background(
                    color = Gray30.copy(alpha = 0.19f),
                    shape = RoundedCornerShape(10.dp)
                )
        )

        OutlineTextFildWithDropdownMenu(suggestions = dataList, enableStatus = enableStatus)
    }
}

@Composable
fun OutlineTextFildWithDropdownMenu(
    suggestions: List<String>,
    enableStatus: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var color: Color = Color.Black

    if (!enableStatus) {
        selectedText = suggestions[0]
        color = Gray30

    }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column() {
        OutlinedTextField(
            value = selectedText,
            readOnly = true,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .height(height = 55.dp)
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            if (enableStatus) {
                                expanded = !expanded
                            }
                        })
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(color = color, fontSize = 17.sp)

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) {
                    textFieldSize.width.toDp()
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
                    )
                }
            }
        }
    }
}
