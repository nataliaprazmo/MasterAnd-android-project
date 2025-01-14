package com.example.masterand.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.masterand.utils.AnimationAndTransitionUtils

@Composable
fun GameRow(
    selectedColors: List<Color>,
    feedbackColors: List<Color>,
    clickable: Boolean,
    onSelectColorClick: (Int) -> Unit,
    onCheckClick: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    AnimationAndTransitionUtils.AnimateExpandFromTop(visible) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(left = 16.dp, right = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SelectableColorsRow(colors = selectedColors, onColorSelected = onSelectColorClick)
            val isButtonEnabled = clickable && selectedColors.all { it != Color.White }
            AnimationAndTransitionUtils.AnimateButton(isEnabled = isButtonEnabled) {
                FilledIconButton(
                    onClick = onCheckClick,
                    enabled = isButtonEnabled,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                ) {
                    Icon(Icons.Filled.Check, contentDescription = "Check")
                }
            }
            FeedbackCircles(colors = feedbackColors)
        }
    }
}