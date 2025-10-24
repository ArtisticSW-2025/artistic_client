package com.snacks.nuvo.ui.script

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.R
import com.snacks.nuvo.Routes
import com.snacks.nuvo.rememberNuvoAppState
import com.snacks.nuvo.ui.component.LoadingIndicator
import com.snacks.nuvo.ui.home.FakeCallSessionRepository
import com.snacks.nuvo.ui.script.component.ScriptChip
import com.snacks.nuvo.ui.script.component.ScriptListItem
import com.snacks.nuvo.ui.script.component.ScriptSearchBar
import com.snacks.nuvo.ui.script.component.ScriptToggleButton
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun ScriptScreen(
    appState: NuvoAppState,
    viewModel: ScriptViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = NuvoTheme.colors.white),
    ) {
        Column {
            Text(
                "나에게 맞춘 스크립트",
                style = NuvoTheme.typography.pretendardBlack24,
                color = NuvoTheme.colors.subNavy,
                modifier = Modifier.padding(start = 20.dp, top = 44.dp)
            )
            Spacer(Modifier.height(48.dp))
            ScriptSearchBar(
                modifier = Modifier.padding(horizontal = 20.dp),
                keyword = uiState.searchKeyword,
                onValueChange = { it -> viewModel.onValueChage(it) },
                onSearch = { viewModel.onSearch() }
            )
            Spacer(Modifier.height(32.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(uiState.chipLabels) { index, label ->
                    ScriptChip(
                        // 첫번째/마지막 아이템에만 패딩 추기
                        modifier = Modifier.padding(
                            start = if (index == 0) 20.dp else 0.dp,
                            end = if (index == uiState.chipLabels.size - 1) 20.dp else 0.dp
                        ),
                        isSelected = index in uiState.selectedChipIndexes,
                        label = label,
                        onClick = { viewModel.onChipClick(index) }
                    )
                }
            }
//            Spacer(Modifier.height(24.dp))
//            Row(
//                horizontalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                ScriptToggleButton(
//                    modifier = Modifier.padding(start = 20.dp),
//                    icon = R.drawable.ic_users_outlined,
//                    label = "스몰토크 모드",
//                    isSelected = uiState.isSmallTalkMode,
//                    onClick = { viewModel.toggleSmallTalkMode() }
//                )
//                ScriptToggleButton(
//                    icon = R.drawable.ic_danger_outlined,
//                    label = "돌발상황 모드",
//                    isSelected = uiState.isEmergencyMode,
//                    onClick = { viewModel.toggleEmergencyMode() }
//                )
//            }
            Spacer(Modifier.height(25.dp))
            LazyColumn(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                itemsIndexed(uiState.scriptItems) { index, item ->
                    ScriptListItem(
                        title = item.title,
                        description = item.description,
                        onClick = { appState.navigate("${Routes.Script.SCRIPT_DETAIL}/${item.id}?isSmallTalkMode=${uiState.isSmallTalkMode}&isEmergencyMode=${uiState.isEmergencyMode}") }
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = NuvoTheme.colors.gray3
                    )
                }
            }
        }
        if (uiState.isLoading) {
            LoadingIndicator()
        }
    }
}

@Preview
@Composable
fun ScriptScreenPreview() {
    val viewModel = remember { ScriptViewModel(
            callSessionRepository = FakeCallSessionRepository()
    ) }
    ScriptScreen(
        appState = rememberNuvoAppState(),
        viewModel = viewModel
    )
}