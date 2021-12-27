package com.example.otp_example

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R

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
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    )
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
            .padding(8.dp)
    ) {
        TopBarForOTP()

        PasscodeScreenDescription()

        Spacer(modifier = Modifier.height(20.dp))

        visibleDotsForPin(pinList = list)

        Spacer(modifier = Modifier.height(15.dp))

        visibleDotsForPin(pinList = list2)

        Spacer(modifier = Modifier.height(10.dp))

        PhoneButtons(
            data = listOf(
                "1", "2", "3", "4", "5", "6", "7", "8", "9"
            ),
            onClick = onClick
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 95.dp)
        ) {
            PhoneButtonZero(
                onClick = onClick
            )
            Spacer(modifier = Modifier.width(20.dp))

            DeleteLeftIcon(
                list = list,
                list2 = list2
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhoneButtons(
    data: List<String>,
    onClick: (digit: Char) -> Unit,
    modifier: Modifier = Modifier

) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 70.dp)
    ) {
        items(data.size) {
            var number = data[it].toInt()
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .padding(10.dp)
                    .background(Color.LightGray, shape = CircleShape)
                    .size(70.dp)
                    .clickable {
                        onClick(number.digitToChar())
                    }
            ) {
                Text(
                    text = data[it],
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun PhoneButtonZero(
    onClick: (digit: Char) -> Unit,
    modifier: Modifier = Modifier
) {
    val number: Int = 0
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(10.dp)
            .background(Color.LightGray, shape = CircleShape)
            .size(70.dp)
            .clickable {
                onClick(number.digitToChar())
            }
    ) {
        Text(
            text = number.toString(),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = modifier

        )
    }
}

@Composable
fun DeleteLeftIcon(
    list: MutableList<String>,
    list2: MutableList<String>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.ic_delete_left),
        contentDescription = null,
        modifier = modifier
            .size(38.dp)
            .clickable {
                if (list2.size > 0) {
                    list2.remove(list2.last())
                } else if (list2.size == 0 && list.size > 0) {
                    list.remove(list.last())
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
    )
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
                .size(38.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.install_password_code),
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = stringResource(id = R.string.install_password_code_description),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun TopBarForOTP(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.fillMaxWidth().padding(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.skip),
            style = MaterialTheme.typography.body1,
            modifier = modifier
                .clickable {
                }
        )
    }
}


@Composable
fun visibleDotsForPin(
    pinList: MutableList<String>
) {
    val rowWidth = 15.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        AnimatedVisibility(visible = pinList.size > 0) {
            doteImage(modifier = Modifier)

        }
        Spacer(modifier = Modifier.width(rowWidth))

        AnimatedVisibility(visible = pinList.size > 1) {
            doteImage(modifier = Modifier)

        }

        Spacer(modifier = Modifier.width(rowWidth))

        AnimatedVisibility(visible = pinList.size > 2) {
            doteImage(modifier = Modifier)

        }

        Spacer(modifier = Modifier.width(rowWidth))

        AnimatedVisibility(visible = pinList.size > 3) {
            doteImage(modifier = Modifier)

        }
    }

}

@Composable
fun doteImage(
    modifier: Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_oval),
        contentDescription = null,
        modifier = modifier
            .size(13.dp)
    )
}