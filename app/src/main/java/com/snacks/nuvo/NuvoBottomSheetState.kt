//package com.snacks.nuvo
//
//import androidx.compose.foundation.layout.ColumnScope
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.rememberModalBottomSheetState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.Stable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.platform.LocalSoftwareKeyboardController
//import androidx.compose.ui.platform.SoftwareKeyboardController
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
//typealias SheetContent = @Composable ColumnScope.() -> Unit
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun rememberNuvoBottomSheetState(
//    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
//    scope: CoroutineScope = rememberCoroutineScope(),
//): NuvoBottomSheetState {
//
//    val bottomSheetContent: MutableState<SheetContent?> = remember {
//        mutableStateOf(null)
//    }
//    val onHideBottomSheet = remember {
//        mutableStateOf<() -> Unit>({})
//    }
//
//    val bottomSheetState = rememberModalBottomSheetState(
//        initialValue = ModalBottomSheetValue.Hidden,
//        confirmValueChange = {
//            if (it == ModalBottomSheetValue.Hidden) {
//                onHideBottomSheet.value()
//            }
//            true
//        },
//        skipHalfExpanded = true
//    )
//
//    return remember(bottomSheetState, bottomSheetContent, onHideBottomSheet) {
//        NuvoBottomSheetState(
//            bottomSheetState = bottomSheetState,
//            setOnHideBottomSheet = { onHideBottomSheet.value = it },
//            bottomSheetContent = bottomSheetContent,
//            keyboardController = keyboardController,
//            scope = scope
//        )
//    }
//}
//
//@Stable
//class NuvoBottomSheetState @OptIn(ExperimentalMaterial3Api::class) constructor(
//    val bottomSheetContent: MutableState<SheetContent?>,
//    val bottomSheetState: ModalBottomSheetState,
//    val setOnHideBottomSheet: (() -> Unit) -> Unit,
//    val keyboardController: SoftwareKeyboardController?,
//    val scope: CoroutineScope,
//) {
//
//    val setBottomSheet: (SheetContent) -> Unit = { content: SheetContent ->
//        bottomSheetContent.value = content
//    }
//    val clearBottomSheet: () -> Unit = {
//        bottomSheetContent.value = null
//    }
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    fun showBottomSheet(content: SheetContent) = scope.launch {
//        keyboardController?.hide()
//        bottomSheetState.hide()
//        setBottomSheet(content)
//        bottomSheetState.show()
//    }
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    fun hideBottomSheet() = scope.launch {
//        keyboardController?.hide()
//        clearBottomSheet()
//        bottomSheetState.hide()
//    }
//}