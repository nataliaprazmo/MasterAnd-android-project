package com.example.masterand.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.masterand.utils.AnimationAndTransitionUtils

@Composable
fun FeedbackCircles(colors: List<Color>) {
    AnimationAndTransitionUtils.AnimatedFeedbackCircles(
        colors = colors
    ) { animatedColors ->
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            animatedColors.chunked(2).forEach { rowColors ->
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    rowColors.forEach { color ->
                        AnimationAndTransitionUtils.AnimateSmallCircle(color = color) { animatedColor ->
                            SmallCircle(color = animatedColor)
                        }
                    }
                }
            }
        }
    }
}