package com.example.sauexpert.my_patients

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.ui.theme.Gray15
import com.example.sauexpert.ui.theme.SauExpertTheme
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun MyPatients2() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    val tabTitles = listOf("Новые", "Все", "Гипертония", "Сахарный диабет")
    SauExpertTheme() {
        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetContent = {
                ButtonActionView()
            }, sheetPeekHeight = 0.dp
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Spacer(modifier = Modifier.padding(29.dp))
                Text(
                    text = "Мои пациенты",
                    //style = MaterialTheme.typography.h4,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(9.dp))
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SearchView(textState)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Card(
                        modifier = Modifier
                            .height(44.dp)
                            .width(44.dp),
                        backgroundColor = Gray15,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_left_arrow_right),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(12.dp)
                                .clickable {
                                    coroutineScope.launch {
                                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                            bottomSheetScaffoldState.bottomSheetState.expand()
                                        } else {
                                            bottomSheetScaffoldState.bottomSheetState.collapse()
                                        }
                                    }
                                }
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Column(modifier = Modifier.fillMaxSize()) {
                    Tabs(tabTitles)
                }
            }
        }
    }
}

@Composable
fun ButtonWithTextColorChange(
    text: String,
    onClick: () -> Unit,
    enableState: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    contentColor: Color = Color.Green
) {
    Button(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(height = 56.dp),
        enabled = enableState,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            contentColor = contentColor

        ),
        elevation = ButtonDefaults.elevation(2.dp),
        shape = shape
    ) {
        Text(
            text = text, fontSize = 18.sp
        )
    }
}

@Composable
fun ButtonActionView() {
    Box {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            ButtonWithTextColorChange(
                text = stringResource(id = R.string.on_pulse),
                onClick = {},
                enableState = true,
                shape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp),
                contentColor = Color.Black
            )
            ButtonWithTextColorChange(
                text = stringResource(id = R.string.on_pulse),
                onClick = {},
                enableState = true,
                shape = RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp),
                contentColor = Color.Black
            )
            Spacer(modifier = Modifier.padding(bottom = 9.dp))
            ButtonWithTextColorChange(
                text = stringResource(id = R.string.on_pulse),
                onClick = {},
                enableState = true,
                contentColor = Color.Black
            )
            Spacer(modifier = Modifier.padding(bottom = 9.dp))
            ButtonWithTextColorChange(
                text = stringResource(id = R.string.on_pulse),
                onClick = {},
                enableState = true,
                contentColor = Color.Black
            )
        }
    }
}
//data class ActionButtonData(
//    var title: String = "",
//    var space: Dp = 8.dp,
//    var shape: RoundedCornerShape = RoundedCornerShape(0.dp),
//    val buttonType: ButtonType,
//    val textColor: Int = R.color.green27AE60,
//    val actionButtonType: ActionButtonType?
//)
//enum class ButtonType {
//    BUTTON_ACTION,
//    SPACE,
//    TEXT,
//
//}
//enum class ActionButtonType {
//    JUST_TEXT,
//    SPACE,
//    PUBLISH,
//    PREVIEW,
//    SAVE_TO_DRAFT,
//    SHARE,
//    ADD_TO_COLLECTION,
//    MOVE_TO_COLLECTION,
//    REMOVE_COLLECTION,
//    REMOVE_FROM_COLLECTION,
//    ADD_TO_FAVORITES,
//    REMOVE_FROM_FAVORITES,
//    ADD_TO_HIDDEN,
//    REMOVE_FROM_HIDDEN,
//    COMPLAIN,
//    CANCEL,
//    EDIT,
//    ACTIVATE,
//    DEACTIVATE,
//    DELETE,
//    VIEW_SEARCH,
//    REMOVE_SEARCH
//}
//@Composable
//fun AdvertOfFavoritesOptionsContent(
//    onShareClick: () -> Unit,
//    onAddToCollectionClick: () -> Unit,
//    onRemoveFromLikedClick: () -> Unit,
//    onAddToHiddenClick: () -> Unit,
//    onComplainClick: () -> Unit,
//    onCancelClick: () -> Unit
//) {
//    val actionList = mutableListOf<ActionButtonData>().apply {
//        add(
//            ActionButtonData(
//                title = stringResource(id = R.string.options_title),
//                shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp),
//                buttonType = ButtonType.TEXT,
//                actionButtonType = ActionButtonType.JUST_TEXT,
//                textColor = R.color.grayA9ACB0
//            )
//        )
//        add(
//            ActionButtonData(
//                title = stringResource(id = R.string.share),
//                shape = RoundedCornerShape(0.dp),
//                buttonType = ButtonType.BUTTON_ACTION,
//                actionButtonType = ActionButtonType.SHARE
//            )
//        )
//        add(
//            ActionButtonData(
//                title = stringResource(id = R.string.options_add_to_collection),
//                shape = RoundedCornerShape(0.dp),
//                buttonType = ButtonType.BUTTON_ACTION,
//                actionButtonType = ActionButtonType.ADD_TO_COLLECTION
//            )
//        )
//        add(
//            ActionButtonData(
//                title = stringResource(id = R.string.remove_from_favorites),
//                shape = RoundedCornerShape(0.dp),
//                buttonType = ButtonType.BUTTON_ACTION,
//                actionButtonType = ActionButtonType.REMOVE_FROM_FAVORITES
//            )
//        )
//        add(
//            ActionButtonData(
//                title = stringResource(id = R.string.hide),
//                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
//                buttonType = ButtonType.BUTTON_ACTION,
//                actionButtonType = ActionButtonType.ADD_TO_HIDDEN
//            )
//        )
//        add(
//            ActionButtonData(
//                buttonType = ButtonType.SPACE,
//                actionButtonType = null
//            )
//        )
//        add(
//            ActionButtonData(
//                title = stringResource(id = R.string.complain),
//                shape = RoundedCornerShape(12.dp),
//                buttonType = ButtonType.BUTTON_ACTION,
//                actionButtonType = ActionButtonType.COMPLAIN,
//                textColor = R.color.redB10909
//            )
//        )
//        add(
//            ActionButtonData(
//                buttonType = ButtonType.SPACE,
//                actionButtonType = null
//            )
//        )
//        add(
//            ActionButtonData(
//                title = stringResource(id = R.string.cancellation),
//                shape = RoundedCornerShape(12.dp),
//                buttonType = ButtonType.BUTTON_ACTION,
//                actionButtonType = ActionButtonType.CANCEL
//            )
//        )
//    }
//
//    Column {
//        actionList.forEachIndexed { index, item ->
//            ActionButtonItems(
//                item = item,
//                isVisibilityDivider = if (index != actionList.size - 1) {
//                    actionList[index + 1].buttonType != ButtonType.SPACE
//                } else {
//                    false
//                },
//                onShareClick = { onShareClick() },
//                onAddToCollectionClick = { onAddToCollectionClick() },
//                onRemoveFromFavoritesClick = { onRemoveFromLikedClick() },
//                onAddToHiddenClick = { onAddToHiddenClick() },
//                onComplainClick = { onComplainClick() },
//                onCancelClick = { onCancelClick() }
//            )
//        }
//    }
//}
//
//@Composable
//fun BottomSheetActionsContainer(
//    modifier: Modifier = Modifier,
//    content: @Composable () -> Unit
//) {
//    Box(
//        modifier
//            .fillMaxWidth()
//            .padding(start = 16.dp, end = 16.dp, bottom = 50.dp)
//    ) {
//        content()
//    }
//}
//
//@Preview
//@Composable
//fun FavoriteAdvertBottomSheetContentPreview() {
//    BottomSheetActionsContainer(
//        content = {
//            AdvertOfFavoritesOptionsContent(
//                onShareClick = {},
//                onAddToCollectionClick = {},
//                onRemoveFromLikedClick = {},
//                onAddToHiddenClick = {},
//                onComplainClick = {},
//                onCancelClick = {}
//            )
//        }
//    )
//}
