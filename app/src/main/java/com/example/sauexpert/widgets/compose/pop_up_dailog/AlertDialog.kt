package com.example.sauexpert.widgets.compose.pop_up_dailog

import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.sauexpert.R
import com.example.sauexpert.profile.OutlinedTextFieldWithBackground
import com.example.sauexpert.ui.theme.Blue007AFF
import com.example.sauexpert.ui.theme.Gray15
import com.example.sauexpert.ui.theme.Gray30
import com.example.sauexpert.widgets.compose.buttons.OutlinedMainButton

@Composable
fun AlertDialog() {

}

@Composable
fun RenameDialog(
    modifier: Modifier = Modifier,
    stateForRename: String,
    onNameChange: (String) -> Unit,
    isDialogOpen: MutableState<Boolean>

) {
    if (isDialogOpen.value) {
        Dialog(onDismissRequest = { isDialogOpen.value = false }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(
                        color = Gray15,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(top = 18.dp),
            ) {
                Text(
                    text = stringResource(R.string.meal_time),
                    style = MaterialTheme.typography.subtitle2,
                )

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedTextFieldWithBackground(
                    textState = stateForRename,
                    onTextChange = onNameChange,
                    colorBackground = Color.White,
//                    textSize = 12.sp,
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                )


                Spacer(modifier = Modifier.height(15.dp))

                Divider(
                    color = Gray30.copy(alpha = 0.19f),
                    thickness = 1.dp,
                    modifier = modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.height(IntrinsicSize.Min)
                ) {
                    OutlinedMainButton(
                        text = stringResource(id = R.string.cancellation),
                        onClick = { isDialogOpen.value = false },
                        enableState = true,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Gray15,
                            contentColor = Blue007AFF,
                        ),
                        textColor = Color.Transparent,
                        modifier = Modifier.weight(0.5f)
                    )

                    Divider(
                        color = Gray30.copy(alpha = 0.19f),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )

                    OutlinedMainButton(
                        text = stringResource(id = R.string.done),
                        onClick = { /*TODO*/ },
                        enableState = true,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Gray15,
                            contentColor = Blue007AFF,
                        ),
                        textColor = Color.Transparent,
                        modifier = Modifier.weight(0.5f)
                    )

                }
            }
        }
    }
}