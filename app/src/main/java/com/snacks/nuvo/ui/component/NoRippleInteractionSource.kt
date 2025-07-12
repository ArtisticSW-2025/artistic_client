package com.snacks.nuvo.ui.component

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

// onClick Ripple 효과 제거
class NoRippleInteractionSource: MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction): Boolean = true

}