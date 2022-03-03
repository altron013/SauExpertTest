package com.example.sauexpert.profile

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.dimensions.Dimensions
import com.example.sauexpert.dimensions.smallDimensions
import com.example.sauexpert.dimensions.sw360Dimensions
import com.example.sauexpert.model.HistoryOfTreatmentData
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.ui.theme.Pink4294
import com.example.sauexpert.widgets.compose.Toolbars.ActionToolBar


@Composable
fun HistoryOfTreatmentScreen() {
    var historyState by rememberSaveable { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Gray30.copy(alpha = 0.19f)
            )
            .verticalScroll(rememberScrollState())
            .padding(16.dp)

    ) {
        ActionToolBar(
            titleText = stringResource(id = R.string.treatment_history),
            iconBackClick = Icons.Default.ArrowBack,
            sizeText = dimensions.fontSizeSubtitle_2,
            sizeIcon = dimensions.iconSize_2,
            onBackClick = {},
            onRightClick = {}
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextWithIconFieldWithBackground(
            textForHint = stringResource(R.string.search_history),
            icon = Icons.Default.Search,
            textState = historyState,
            onTextChange = { historyState = it }
        )

        Spacer(modifier = Modifier.height(dimensions.grid_4))

        HistoryTreatmentSection(
            HistoryOfTreatment = listOf(
                HistoryOfTreatmentData(
                    historyOfActivity = "Изменение замеров",
                    date = "24 Января 2021"
                ),
                HistoryOfTreatmentData(
                    historyOfActivity = "Новое назначение",
                    date = "22 Января 2021"
                ),
                HistoryOfTreatmentData(
                    historyOfActivity = "Первый осмотр",
                    date = "23 Января 2021"
                ),
            )
        )


    }
}

@Composable
fun HistoryTreatmentSection(
    HistoryOfTreatment: List<HistoryOfTreatmentData>? = null,
    modifier: Modifier = Modifier
) {

    if (HistoryOfTreatment != null) {
        val listSize = HistoryOfTreatment.size
        val heightForGraph = ((listSize - 1) * 84).dp
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heightForGraph)
            ) {
                var height = 0
                val paint = Paint().apply {
                    textAlign = Paint.Align.LEFT
                    textSize = 17.sp.toPx()
                    color = Color.Black.toArgb()
                }

                val paintGray = Paint().apply {
                    textAlign = Paint.Align.LEFT
                    textSize = 12.sp.toPx()
                    color = Gray30.toArgb()
                }

                for (i in 0 until listSize) {
                    drawContext.canvas.nativeCanvas.drawText(
                        HistoryOfTreatment[i].date,
                        12.dp.toPx(),
                        height.dp.toPx(),
                        paintGray
                    )

                    drawContext.canvas.nativeCanvas.drawText(
                        HistoryOfTreatment[i].historyOfActivity,
                        12.dp.toPx(),
                        (height + 20).dp.toPx(),
                        paint
                    )

                    if (i < listSize - 1) {
                        drawLine(
                            start = Offset(0.dp.toPx(), (height + 10).dp.toPx()),
                            end = Offset(0.dp.toPx(), (height + 84).dp.toPx()),
                            color = Gray30,
                            strokeWidth = 1.dp.toPx()
                        )
                    }

                    drawCircle(
                        color = Pink4294,
                        radius = 6.dp.toPx(),
                        center = Offset(0.dp.toPx(), (height + 14).dp.toPx())
                    )

                    drawCircle(
                        color = Color.Red,
                        radius = 3.5.dp.toPx(),
                        center = Offset(0.dp.toPx(), (height + 14).dp.toPx())
                    )

                    height += 74
                }
            }
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.patient_has_no_treatment_history),
                style = MaterialTheme.typography.overline,
                color = Gray30,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(40.dp)
            )

        }
    }

}