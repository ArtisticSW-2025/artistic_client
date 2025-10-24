package com.snacks.nuvo.ui.ranking.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.ranking.RankingItem

@Composable
internal fun RankingList(
    modifier: Modifier = Modifier,
    rankings: List<RankingItem>,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        itemsIndexed(rankings) { index, item ->
            RankingCard(
                modifier = Modifier,
                rank = item.rank,
                name = item.name,
                score = item.score,
                profileImageUrl = item.profileImageUrl
            )
        }
    }
}