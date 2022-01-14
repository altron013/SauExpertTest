package com.example.sauexpert.bracelet_indicator

import android.graphics.Paint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.sauexpert.R
import com.example.sauexpert.model.Sp02Data
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.MainButton

@ExperimentalComposeUiApi
@Composable
fun Sp02Screen() {
    Column(
        modifier = Modifier
            .fillMaxWidth().verticalScroll(rememberScrollState())
            .padding(bottom = 60.dp)
    ) {
        SP02withLineGraph()
        Spacer(modifier = Modifier.height(24.dp))
        AnalysisSOASStat()
        Spacer(modifier = Modifier.height(24.dp))
        AnalysisSOASStat2()
    }
}


@Composable
fun SP02withLineGraph(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(7.dp)
            ).padding(16.dp)
    ) {
        SP02Stat()
        Spacer(modifier = Modifier.height(12.dp))
        LineChartForSp02()
        Spacer(modifier = Modifier.height(10.dp))
        SP02Stat2()

    }
}

@Composable
fun SP02Stat(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.sp02),
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Circle,
                contentDescription = "",
                tint = Color.Green.copy(alpha = 0.25f),
                modifier = modifier.size(9.dp)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = stringResource(id = R.string.oxygen_level),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = Gray30
            )
        }

        Text(
            text = "18-20 ноября 2021",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Gray30
        )
    }
}


@Composable
fun LineChartForSp02() {
    var scale by remember { mutableStateOf(1f) }
    val point = listOf(
        Sp02Data(10f, 10f),
        Sp02Data(50f, 100f),
        Sp02Data(100f, 30f),
        Sp02Data(150f, 200f),
        Sp02Data(200f, 120f),
        Sp02Data(250f, 10f),
        Sp02Data(300f, 280f),
        Sp02Data(350f, 100f),
        Sp02Data(400f, 10f),
        Sp02Data(450f, 100f),
        Sp02Data(500f, 200f)
    )

    val path = Path()
    for ((index, item) in point.withIndex()) {
        if (index == 0) {
            path.moveTo(item.X * scale, item.Y)
        } else {
            path.lineTo(item.X * scale, item.Y)
        }
    }

    val paint = Paint().apply {
        textAlign = Paint.Align.CENTER
        textSize = 34f
//            color = Color(0xFF0018A8).toArgb()
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.White)
    ) {

        drawLine(
            start = Offset(10f, 0.dp.toPx()),
            end = Offset(264.dp.toPx(), 0.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "100",
            280.dp.toPx(),
            10.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 34.dp.toPx()),
            end = Offset(264.dp.toPx(), 34.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "90",
            280.dp.toPx(),
            44.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 68.dp.toPx()),
            end = Offset(264.dp.toPx(), 68.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "80",
            280.dp.toPx(),
            78.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 102.dp.toPx()),
            end = Offset(264.dp.toPx(), 102.dp.toPx()),
            color = Gray30,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "70",
            280.dp.toPx(),
            112.dp.toPx(),
            paint
        )

        drawLine(
            start = Offset(10f, 136.dp.toPx()),
            end = Offset(264.dp.toPx(), 136.dp.toPx()),
            color = Color.Black,
            strokeWidth = 2f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "60",
            280.dp.toPx(),
            146.dp.toPx(),
            paint
        )

        drawContext.canvas.nativeCanvas.drawText(
            "00:00",
            20.dp.toPx(),
            152.dp.toPx(),
            paint
        )

        drawContext.canvas.nativeCanvas.drawText(
            "02:00",
            60.dp.toPx(),
            152.dp.toPx(),
            paint
        )

        drawContext.canvas.nativeCanvas.drawText(
            "04:00",
            100.dp.toPx(),
            152.dp.toPx(),
            paint
        )

        drawContext.canvas.nativeCanvas.drawText(
            "06:00",
            140.dp.toPx(),
            152.dp.toPx(),
            paint
        )

        drawContext.canvas.nativeCanvas.drawText(
            "08:00",
            180.dp.toPx(),
            152.dp.toPx(),
            paint
        )

        drawContext.canvas.nativeCanvas.drawText(
            "10:00",
            220.dp.toPx(),
            152.dp.toPx(),
            paint
        )

        drawPath(
            path = path,
            color = Color.Green,
            style = Stroke(width = 5f)
        )
    }
}

@Composable
fun SP02Stat2(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {

        Icon(
            imageVector = Icons.Filled.CropSquare,
            contentDescription = "",
            tint = Color.Green,
            modifier = modifier.size(9.dp)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = stringResource(R.string.sp02),
            style = MaterialTheme.typography.button,
        )

        Spacer(modifier = Modifier.width(21.dp))

        Icon(
            imageVector = Icons.Filled.CropSquare,
            contentDescription = "",
            tint = Color.Red,
            modifier = modifier.size(9.dp)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = stringResource(R.string.sleep_apnea),
            style = MaterialTheme.typography.button,
        )

    }
}

@ExperimentalComposeUiApi
@Composable
fun AnalysisSOASStat(modifier: Modifier = Modifier) {
    val visible: MutableState<Boolean> = remember { mutableStateOf(false) }

    InfoDialogForSOAS(visible = visible)

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.soas_analysis),
                style = MaterialTheme.typography.subtitle2,
            )

            Text(
                text = stringResource(R.string.more_detail),
                style = MaterialTheme.typography.body2,
                color = Color.Red,
                modifier = modifier.clickable {
                    visible.value = true
                }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(7.dp)
                )
        ) {
            AnalysisStatField(
                title = stringResource(R.string.severe_degree),
                value = "18",
                isIconVisible = true
            )
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisStatField(title = stringResource(R.string.sleep_apnea_case), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisStatField(title = stringResource(R.string.hypopnea_case), value = "18")

        }

    }
}


fun Modifier.customDialogModifier(pos: String) = layout { measurable, constraints ->

    val placeable = measurable.measure(constraints)
    layout(constraints.maxWidth, constraints.maxHeight) {
        when (pos) {
            "BOTTOM" -> {
                placeable.place(0, constraints.maxHeight - placeable.height, 10f)
            }
            "TOP" -> {
                placeable.place(0, 0, 10f)
            }
        }
    }
}


@ExperimentalComposeUiApi
@Composable
fun InfoDialogForSOAS(
    visible: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    if (visible.value) {
        AlertDialog(
            onDismissRequest = { visible.value = false },
            title = {
                Text(
                    text = stringResource(R.string.soas_analysis),
                    style = MaterialTheme.typography.caption
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.soas_description),
                    style = MaterialTheme.typography.body1
                )
            },
            confirmButton = {
                MainButton(
                    text = stringResource(id = R.string.understand),
                    onClick = { visible.value = false },
                    enableState = true,
                    modifier = modifier.padding(16.dp)
                )
            },
            shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
            modifier = modifier.fillMaxWidth().customDialogModifier("BOTTOM"),
            properties = DialogProperties(usePlatformDefaultWidth = false),
        )
    }

}


@Composable
fun AnalysisSOASStat2(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.sp02),
            style = MaterialTheme.typography.subtitle2,
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
            AnalysisStatField(title = stringResource(R.string.sp02_average), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisStatField(title = stringResource(R.string.breathing_rate), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisStatField(title = stringResource(R.string.hypoxia_case), value = "18")
            Divider(
                color = Gray30.copy(alpha = 0.19f),
                thickness = 1.dp,
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
            AnalysisStatField(title = stringResource(R.string.cardiac_pressure), value = "18")

        }

    }
}
