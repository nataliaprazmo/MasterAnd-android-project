package com.example.masterand.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.masterand.utils.AnimationAndTransitionUtils

@Composable
fun CircularButton(color: Color, onClick: () -> Unit) {
    AnimationAndTransitionUtils.AnimateColor(color) { animatedColor ->
        Button(
            onClick = onClick,
            modifier = Modifier
                .size(50.dp)
                .background(color = MaterialTheme.colorScheme.background),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface),
            colors = ButtonDefaults.buttonColors(containerColor = animatedColor)
        ) {}
    }
}
