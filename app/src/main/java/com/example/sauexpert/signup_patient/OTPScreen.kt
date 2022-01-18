package com.example.otp_example

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R

@ExperimentalFoundationApi
@Composable
fun OTPScreen() {
    val otpVal: String? = null
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Enter the OTP",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        OTPTextFields(
            length = 4
        ) { getOpt ->
            otpVal
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun OTPTextFields(
    modifier: Modifier = Modifier,
    length: Int,
    onFilled: (code: String) -> Unit
) {
    var code: MutableList<Char> by remember { mutableStateOf(mutableListOf()) }
    var data = listOf(
        "1", "2", "3", "4", "5", "6", "7", "8", "9"
    )
    var qw = data.forEach() {
        it
    }
//    val focusRequesters: List<FocusRequester> = remember {
//        val temp = mutableListOf<FocusRequester>()
//        repeat(length) {
//            temp.add(FocusRequester())
//        }
//        temp
//    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.height(50.dp)
        ) {
            (0 until length).forEach { index ->
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White, disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .size(50.dp)
//                        .focusOrder(focusRequester = focusRequesters[index]) {
//                            focusRequesters[index + 1].requestFocus()
//                        }
                        .background(Color.Transparent),
                    textStyle = MaterialTheme.typography.body2.copy(
                        textAlign = TextAlign.Center, color = Color.Black
                    ),
                    singleLine = true,
                    value = code.getOrNull(index = index)?.takeIf {
                        it.isDigit()
                    }?.toString() ?: "",
                    onValueChange = { value: String ->
//                        if (focusRequesters[index].freeFocus()) {
//                            val temp = code.toMutableList()
//                            if (value == "") {
//                                if (temp.size > index) {
//                                    temp.removeAt(index = index)
//                                    code = temp
//                                    focusRequesters.getOrNull(index - 1)?.requestFocus()
//                                }
//                            } else {
//                                temp.add(value.getOrNull(0) ?: ' ')
//                                code = temp
//                                focusRequesters.getOrNull(index + 1)?.requestFocus() ?: onFilled(
//                                    code.joinToString(separator = "")
//                                )
//                            }
//                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.width(15.dp))
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        PhoneButtons(
            data = data,
            onClick = {
                if (code.size < 4) {
                    it.firstOrNull()?.let {
                        code = (code + listOf(it)).toMutableList()
                    }
                } else {
//                  code = code.subList(0, code.size - 1)
                    // code=code
                }
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp)
        ) {
//            PhoneButton(
//                pinList = pinList
//            )
            Spacer(modifier = Modifier.width(20.dp))
            DeleteLeftIcon(
                onClick = {
//                    if (code.isNotEmpty()) {
//                        code.removeLast()
                    if (code.size == 0) return@DeleteLeftIcon
                    code = code.subList(0, code.size - 1)
                    // }
                }
            )
        }
    }

}


@ExperimentalFoundationApi
@Composable
fun PhoneButtons(
    data: List<String>,
//    pinList: MutableList<Int>
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
//    val context = LocalContext.current
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 70.dp)
    ) {
        itemsIndexed(data) { index, item ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .padding(10.dp)
                    .background(Color.LightGray, shape = CircleShape)
                    .size(70.dp)
                    .clickable {
                        onClick(item)
                        //item
//                        if (pinList.size < 4) {
//                            pinList.add(Integer.parseInt(data[it]))
//                            Toast
//                                .makeText(context, data[it], Toast.LENGTH_SHORT)
//                                .show()
//                        }
                    }
            ) {
                Text(
                    text = item,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun PhoneButton(
//    pinList: MutableList<Int>,
    modifier: Modifier = Modifier
) {
//    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(10.dp)
            .background(Color.LightGray, shape = CircleShape)
            .size(70.dp)
            .clickable {
//                if (pinList.size < 4) {
//                    pinList.add(0)
//                    Toast
//                        .makeText(context, "0", Toast.LENGTH_SHORT)
//                        .show()
//                }
            }
    ) {
        Text(
            text = "0",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
        )
    }
}

@Composable
fun DeleteLeftIcon(
//    pinList: MutableList<Int>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit

) {
//    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.ic_bandage_fill),
        contentDescription = null,
        modifier = modifier
            .size(38.dp)
            .clickable {
                onClick()
            }
    )
}