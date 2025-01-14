package com.example.masterand.utils

import androidx.compose.ui.graphics.Color

object GameUtils {
    fun selectNextAvailableColor(
        availableColors: List<Color>,
        selectedColors: List<Color>,
        buttonIndex: Int
    ): List<Color> {
        var remainingColors = availableColors.filterNot { it in selectedColors }
        remainingColors = remainingColors.shuffled()
        return selectedColors.toMutableList().apply { set(buttonIndex, remainingColors[0]) }
    }

    fun selectRandomColors(availableColors: List<Color>, count: Int): List<Color> {
        return availableColors.shuffled().take(count)
    }

    fun checkColors(selectedColors: List<Color>, correctColors: List<Color>): List<Color> {
        val feedback = MutableList(4) { notFoundColor }
        val correctColorsMutable = correctColors.toMutableList()

        if (selectedColors == List<Color>(4) { Color.White }) {
            return feedback
        }

        selectedColors.forEachIndexed { index, color ->
            if (color == correctColors[index]) {
                feedback[index] = Color.Red
                correctColorsMutable[index] = notFoundColor
            }
        }

        selectedColors.forEachIndexed { index, color ->
            if (color in correctColorsMutable && feedback[index] != Color.Red) {
                feedback[index] =
                    Color.Yellow
                correctColorsMutable[correctColorsMutable.indexOf(color)] =
                    notFoundColor
            }
        }

        return feedback
    }

    val colorList = listOf(
        Color(0xFFFF3D00),
        Color(0xFFE83C8C),
        Color(0xFFD500F9),
        Color(0xFF651FFF),
        Color(0xFF2541EA),
        Color(0xFF00B0FF),
        Color(0xFF00E5FF),
        Color(0xFF025046),
        Color(0xFF0C8D10),
        Color(0xFF64DD17),
        Color(0xFFFCFCA3),
        Color(0xFFFFDD00),
        Color(0xFFFF9E80),
        Color(0xFFFFA500),
        Color(0xFF790808),
        Color(0xFF000000)
    )

    val notFoundColor = Color.White
}