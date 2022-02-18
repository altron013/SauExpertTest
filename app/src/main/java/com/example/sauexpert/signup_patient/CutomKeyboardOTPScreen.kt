package com.example.sauexpert.signup_patient

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.Toolbars.MainActionToolBar

@Composable
fun CustomKeyboardOTPScreen() {
    val pinList = remember { mutableStateListOf<String>() }
    val pinList2 = remember { mutableStateListOf<String>() }
    val context = LocalContext.current

    CustomKeyboard(
        list = pinList,
        list2 = pinList2,
        onClick = { digit ->
            if (pinList.size < 4) {
                pinList.add(digit.toString())
            } else if (pinList.size == 4 && pinList2.size < 4) {
                pinList2.add(digit.toString())
            } else {
                val isEqual = isEqual(pinList, pinList2)
                Toast.makeText(context, "$isEqual", Toast.LENGTH_SHORT).show()
            }
        }
    )
}

fun <T> isEqual(first: List<T>, second: List<T>): Boolean {

    if (first.size != second.size) {
        return false
    }

    first.forEachIndexed { index, value ->
        if (second[index] != value) {
            return false
        }
    }
    return true
}

@Composable
fun CustomKeyboard(
    list: MutableList<String>,
    list2: MutableList<String>,
    onClick: (digit: Char) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        MainActionToolBar(
            textBackClick = stringResource(R.string.skip),
            onBackClick = {},
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
        )

        PasscodeScreenDescription()

        Spacer(modifier = Modifier.height(20.dp))

        visibleDotsForPin(pinList = list)

        Spacer(modifier = Modifier.height(15.dp))

        visibleDotsForPin(pinList = list2)

        Spacer(modifier = Modifier.height(10.dp))

//        PhoneButtons(
//            data = listOf(
//                "1", "2", "3", "4", "5", "6", "7", "8", "9"
//            ),
//            onClick = onClick
//        )


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(number = 1, onClick = onClick)
            NumberButton(number = 2, onClick = onClick)
            NumberButton(number = 3, onClick = onClick)

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(number = 4, onClick = onClick)
            NumberButton(number = 5, onClick = onClick)
            NumberButton(number = 6, onClick = onClick)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(number = 7, onClick = onClick)
            NumberButton(number = 8, onClick = onClick)
            NumberButton(number = 9, onClick = onClick)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            NumberButton(number = 0, onClick = onClick, modifier = Modifier.weight(1f))
            DeleteLeftIcon(
                list = list,
                list2 = list2,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun NumberButton(
    number: Int,
    onClick: (digit: Char) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(10.dp)
                .background(color = Gray30.copy(alpha = 0.19f), shape = CircleShape)
                .clip(shape = CircleShape)
                .size(72.dp)
                .clickable {
                    onClick(number.digitToChar())
                }
        ) {
            Text(
                text = number.toString(),
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.overline

            )
        }
    }
}


@Composable
fun DeleteLeftIcon(
    list: MutableList<String>,
    list2: MutableList<String>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.padding(start = 5.dp)
    ) {
        IconButton(
            onClick = {
                if (list2.size > 0) {
                    list2.remove(list2.last())
                } else if (list2.size == 0 && list.size > 0) {
                    list.remove(list.last())
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete_left),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .size(38.dp)

            )
        }
    }
}


@Composable
fun PasscodeScreenDescription(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_locked_icon),
            contentDescription = null,
            modifier = modifier
                .size(36.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.install_password_code),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.install_password_code_description),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            fontSize = 15.sp,
            modifier = modifier.padding(horizontal = 30.dp)
        )
    }
}


@Composable
fun visibleDotsForPin(
    pinList: MutableList<String>
) {
    val rowWidth = 26.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {

        if (pinList.size > 0) {
            doteImage(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_oval)
            )
        } else {
            doteImage(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_oval_with_empty)
            )
        }

        Spacer(modifier = Modifier.width(rowWidth))

        if (pinList.size > 1) {
            doteImage(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_oval)
            )
        } else {
            doteImage(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_oval_with_empty)
            )
        }

        Spacer(modifier = Modifier.width(rowWidth))

        if (pinList.size > 2) {
            doteImage(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_oval)
            )
        } else {
            doteImage(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_oval_with_empty)
            )
        }
        Spacer(modifier = Modifier.width(rowWidth))

        if (pinList.size > 3) {
            doteImage(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_oval)
            )
        } else {
            doteImage(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_oval_with_empty)
            )
        }
    }

}

@Composable
fun doteImage(
    modifier: Modifier,
    painter: Painter
) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .size(13.dp)
    )
}

//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun PhoneButtons(
//    data: List<String>,
//    onClick: (digit: Char) -> Unit,
//    modifier: Modifier = Modifier
//
//) {
//    LazyVerticalGrid(
//        cells = GridCells.Fixed(3),
//        contentPadding = PaddingValues(horizontal = 60.dp)
//    ) {
//        items(data.size) {
//            var number = data[it].toInt()
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = modifier
//                    .padding(10.dp)
//                    .background(color = Gray30.copy(alpha = 0.19f), shape = CircleShape)
//                    .size(72.dp)
//                    .clickable {
//                        onClick(number.digitToChar())
//                    }
//            ) {
//                Text(
//                    text = data[it],
//                    fontSize = 36.sp,
//                    textAlign = TextAlign.Center,
//                    style = MaterialTheme.typography.overline
//                )
//            }
//        }
//    }
//}