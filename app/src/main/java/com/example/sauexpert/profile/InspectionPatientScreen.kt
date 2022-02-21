package com.example.sauexpert.profile

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.*
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Green117259
import com.example.sauexpert.ui.theme.Pink42949
import com.example.sauexpert.widgets.compose.MainButton
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBar
import java.util.*

@Composable
fun InspectionPatientScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Gray30.copy(alpha = 0.19f))
            .padding(16.dp)
    ) {
        ActionToolBar(
            titleText = "Zhanna Akhmetova",
            iconBackClick = Icons.Default.ArrowBack,
            onBackClick = {},
            onRightClick = {}
        )

        Spacer(modifier = Modifier.height(44.dp))

        PreviousInspectionsSection()

        Spacer(modifier = Modifier.height(24.dp))
        MainButton(
            text = stringResource(id = R.string.new_inspections),
            onClick = { /*TODO*/ },
            enableState = true,
            icon = R.drawable.ic_plus_circle,
            backgroundColor = Pink42949,
            textColor = Color.Red
        )
    }
}

@Composable
fun PreviousInspectionsSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.previous_inspections).toUpperCase(Locale.current),
            style = MaterialTheme.typography.body2,
        )

        Spacer(modifier = Modifier.height(12.dp))

        PreviousInspectionsSection(
            doctorName = "Ларионов Игорь Викторович",
            dateOfInspection = "15 Февраля 2021"
        )

        Spacer(modifier = Modifier.height(12.dp))

        PreviousInspectionsSection(
            doctorName = "Келимбетов Аскар Ахметович",
            dateOfInspection = "22 Мая 2021", yourInspection = true
        )
    }
}


@Composable
fun PreviousInspectionsSection(
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
        AnalysisInspectionsField(doctorName)

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
                backgroundColor = Pink42949,
                textColor = Color.Red
            )
        }
    }
}

@Composable
fun AnalysisInspectionsField(
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
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.weight(0.8f)
            )

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(0.2f),
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = modifier
                        .size(20.dp)
                        .clickable { }
                )
            }

        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(R.string.conducted_inspection),
            style = MaterialTheme.typography.h5,
            color = Gray30,
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
fun ProfileForInspection(
    content: String,
    text: Float,
    showPercentage: Boolean = false,
    modifier: Modifier = Modifier,
    image: Painter = painterResource(id = R.drawable.avatar),
    painter: Painter? = null,
    color: Color = Color.Green
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
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
        if (text != 0f) {
            CircularProgressBar(
                percentage = text,
                number = 100,
                showPercentage = showPercentage,
                color = color
            )
        } else {
            painter?.let {
                Icon(
                    painter = it,
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
fun OutlinedTextFieldWithBackground(
    textForHint: String? = null,
    enableStatus: Boolean = true,
    textState: String,
    colorBackground: Color = Gray30.copy(alpha = 0.19f),
    onTextChange: (String) -> Unit,
    textSize: TextUnit = 17.sp,
    modifier: Modifier = Modifier

) {
    val colorOfTextField = TextFieldDefaults.textFieldColors(
        disabledTextColor = Color.Transparent,
        backgroundColor = colorBackground,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 55.dp),
        enabled = enableStatus,
        value = textState,
        onValueChange = onTextChange,
        placeholder = {
            textForHint?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                )
            }

        },
        textStyle = TextStyle(fontSize = textSize),
        colors = colorOfTextField,
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun OutlinedTextWithIconFieldWithBackground(
    textForHint: String? = null,
    icon: ImageVector,
    enableStatus: Boolean = true,
    textState: String,
    colorBackground: Color = Gray30.copy(alpha = 0.19f),
    onTextChange: (String) -> Unit,
    textSize: TextUnit = 17.sp,
    modifier: Modifier = Modifier

) {
    val colorOfTextField = TextFieldDefaults.textFieldColors(
        disabledTextColor = Color.Transparent,
        backgroundColor = colorBackground,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 55.dp),
        enabled = enableStatus,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier
                    .padding(11.dp)
                    .size(19.dp)
            )
        },
        value = textState,
        onValueChange = onTextChange,
        placeholder = {
            textForHint?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                )
            }

        },
        textStyle = TextStyle(fontSize = textSize),
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
