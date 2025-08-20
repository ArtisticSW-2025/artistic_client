package com.snacks.nuvo.ui.challenge.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.challenge.ChallengeNode
import com.snacks.nuvo.ui.theme.NuvoTheme

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CourseMap(
    nodes: List<ChallengeNode>,
    modifier: Modifier = Modifier,
    onNodeClick: (Int?) -> Unit
) {
    // ## 레이아웃 설정값 ##
    val nodesPerRow = 4
    val rowHeight = 205.dp
    val nodeSize = 70.dp

    // 전체 캔버스 높이 계산
    val rowCount = (nodes.size - 1) / nodesPerRow + 1
    val totalHeight = rowHeight * rowCount + 300.dp

    val density = LocalDensity.current

    val pathColor = NuvoTheme.colors.mainGreen

    // Box를 사용해 캔버스(배경)와 노드(콘텐츠)를 겹치게 배치
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(totalHeight)
    ) {
        // ## X좌표 계산을 위한 기준 위치 (Dp) ##
        // 이 값들을 조정하여 좌/우/중간 위치를 결정합니다.
        val leftPos = nodeSize / 2
        val rightPos = maxWidth - (nodeSize / 2)
        val midLeftPos = leftPos + (nodeSize + 7.dp)
        val midRightPos = rightPos - (nodeSize + 7.dp)

        // ## 1. 모든 노드의 픽셀(Px) 좌표를 미리 계산 ##
        val nodePixelPositions = nodes.mapIndexed { index, _ ->
            val rowIndex = index / nodesPerRow
            val colIndex = index % nodesPerRow

            // --- 1-1. Y 좌표 계산 (Dp 단위) ---
            val baseY = totalHeight - (rowHeight * rowIndex) - (rowHeight / 2) + 40.dp
            val yInDp = when {
                index == 0 -> baseY
                colIndex == 3 -> baseY - 59.dp
                colIndex == 0 -> baseY + 59.dp
                else -> baseY
            }

            // --- 1-2. X 좌표 계산 (Dp 단위) ---
            val xInDp = if (rowIndex % 2 == 0) {
                // 짝수 줄 (0, 2, ...): 오른쪽 -> 왼쪽
                when (colIndex) {
                    0 -> if (index == 0) maxWidth - 20.dp else rightPos
                    1 -> midRightPos
                    2 -> midLeftPos
                    else -> leftPos
                }
            } else {
                // 홀수 줄 (1, 3, ...): 왼쪽 -> 오른쪽
                when (colIndex) {
                    0 -> leftPos
                    1 -> midLeftPos
                    2 -> midRightPos
                    else -> rightPos
                }
            }

            Offset(
                with(density) { xInDp.toPx() },
                with(density) { yInDp.toPx() }
            )
        }

        // ## 2. 배경에 부드러운 경로만 그리는 Canvas ##
        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = Path().apply {
                moveTo(nodePixelPositions.first().x, nodePixelPositions.first().y)
                for (i in 1 until nodePixelPositions.size - 1) {
                    val current = nodePixelPositions[i]
                    val next = nodePixelPositions[i+1]
                    if (i % 8 == 4) {
                        cubicTo(current.x + 30, current.y - 100, next.x - 75, next.y, next.x, next.y)
                    }
                    if (i % 8 == 0) {
                        cubicTo(current.x - 30, current.y - 100, next.x + 75, next.y, next.x, next.y)
                    }

                    if (i % 8 == 3) {
                        cubicTo(current.x - 30, current.y - 50, next.x - 30, next.y + 50, next.x, next.y)
                    }
                    if (i % 8 == 7) {
                        cubicTo(current.x + 30, current.y - 50, next.x + 30, next.y + 50, next.x, next.y)
                    }

                    if (i % 8 == 2) {
                        cubicTo(current.x - 75, current.y, next.x + 30, next.y + 100, next.x, next.y)
                    }
                    if (i % 8 == 6) {
                        cubicTo(current.x + 75, current.y, next.x - 30, next.y + 100, next.x, next.y)
                    }

                    if (i % 4 == 1) {
                        lineTo(next.x, next.y)
                    }
                }
            }
            drawPath(path, color = pathColor, style = Stroke(width = 25f))
        }

        // ## 3. 계산된 좌표에 NodeComponent들을 배치 ##
        nodes.zip(nodePixelPositions).forEach { (node, position) ->
            val xInDp = with(density) { position.x.toDp() }
            val yInDp = with(density) { position.y.toDp() }

            NodeComponent(
                node = node,
                modifier = Modifier
                    .offset(
                        x = xInDp - (nodeSize / 2),
                        y = yInDp - (nodeSize / 2)
                    )
                    .then(
                        if (node.id != null) {
                            Modifier.clickable (
                                indication = null,
                                interactionSource = remember {
                                    MutableInteractionSource()
                                }
                            ) {
                                onNodeClick(node.id)
                            }
                        } else {
                            Modifier
                        }
                    )
            )
        }
    }
}