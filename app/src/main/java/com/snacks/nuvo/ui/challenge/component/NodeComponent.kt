package com.snacks.nuvo.ui.challenge.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.challenge.ChallengeNode
import com.snacks.nuvo.ui.challenge.NodeStatus
import com.snacks.nuvo.ui.theme.NuvoTheme
import java.time.LocalDate

@Composable
fun NodeComponent(
    node: ChallengeNode,
    modifier: Modifier,
) {
    val fillColor = NuvoTheme.colors.mainGreen
    if (node.id == null)
    {
        Box(
            modifier = modifier
                .size(size = 70.dp),
            contentAlignment = Alignment.Center,
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                rotate(degrees = 45F) {
                    drawRect(
                        color = fillColor,
                        topLeft = Offset(x = size.width / 4.9F, y = size.height / 4.9F),
                        size = size / 1.7F
                    )
                }
            }
        }
    }
    else
    {
        Box(
            modifier = modifier
                .size(size = 70.dp)
                .clip(CircleShape)
                .border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = when (node.status) {
                        NodeStatus.LOCKED -> NuvoTheme.colors.gray3
                        NodeStatus.UNLOCKED -> NuvoTheme.colors.subLightGreen
                        NodeStatus.COMPLETED -> NuvoTheme.colors.mainGreen
                    }
                ),
            contentAlignment = Alignment.Center,
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = when (node.status) {
                            NodeStatus.LOCKED -> NuvoTheme.colors.white.copy(alpha = 0.8f)
                            NodeStatus.UNLOCKED -> NuvoTheme.colors.subLightGreen.copy(alpha = 0.8f)
                            NodeStatus.COMPLETED -> NuvoTheme.colors.mainGreen.copy(alpha = 0.8f)
                        }
                    )
                    .blur(radius = 20.dp)
            ) { }
            Text(
                text = "${node.id}",
                style = when (node.status) {
                    NodeStatus.LOCKED -> NuvoTheme.typography.interBold24.copy(color = NuvoTheme.colors.gray3)
                    NodeStatus.UNLOCKED -> NuvoTheme.typography.interBold24.copy(color = NuvoTheme.colors.white)
                    NodeStatus.COMPLETED -> NuvoTheme.typography.interBold24.copy(color = NuvoTheme.colors.white)
                }
            )
        }
    }
}

@Preview
@Composable
fun NodePreview(
    @PreviewParameter(NodePreviewParameterProvider::class) node: ChallengeNode
) {
    NodeComponent(node, modifier = Modifier)
}

@RequiresApi(Build.VERSION_CODES.O)
class NodePreviewParameterProvider : PreviewParameterProvider<ChallengeNode> {
    override val values = sequenceOf(
        ChallengeNode(
            id = null,
            date = LocalDate.now(),
        ),
        ChallengeNode(
            id = 1,
            date = LocalDate.now(),
            status = NodeStatus.LOCKED
        ),
        ChallengeNode(
            id = 2,
            date = LocalDate.now(),
            status = NodeStatus.UNLOCKED
        ),
        ChallengeNode(
            id = 3,
            date = LocalDate.now(),
            status = NodeStatus.COMPLETED
        ),
    )
}