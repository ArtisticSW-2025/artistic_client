package com.snacks.nuvo.ui.ranking.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.snacks.nuvo.ui.ranking.RankingItem

@Composable
internal fun RankingList(
    rankings: List<RankingItem>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(rankings) { index, item ->
            RankingCard(
                rank = item.rank,
                name = item.name,
                score = item.score,
                profileImageUrl = item.profileImageUrl
            )
        }
    }
}