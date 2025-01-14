package com.example.masterand.ui.screens

//import com.example.masterand.viewmodel.AppViewModelProvider
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.masterand.ui.components.GameRow
import com.example.masterand.utils.GameUtils
import com.example.masterand.viewmodel.GameViewModel
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    colorsCount: Int,
    navigateToProfileScreen: () -> Unit,
    navigateToResultsScreen: (attemptCount: Int) -> Unit,
//    viewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory)
    viewModel: GameViewModel = hiltViewModel<GameViewModel>()
) {
    val coroutineScope = rememberCoroutineScope()

    val initialAttempt = List(4) { Color.White }
    val attempts = rememberSaveable { mutableStateOf(listOf(initialAttempt)) }
    val feedbacks = rememberSaveable { mutableStateOf(listOf(List(4) { GameUtils.notFoundColor })) }
    val availableColors = rememberSaveable {
        mutableStateOf(
            GameUtils.selectRandomColors(
                GameUtils.colorList,
                colorsCount
            )
        )
    }
    val correctColors =
        rememberSaveable { mutableStateOf(GameUtils.selectRandomColors(availableColors.value, 4)) }
    val highScoreButtonVisible = remember { mutableStateOf(false) }

    fun clearGame() {
        attempts.value = listOf(initialAttempt)
        feedbacks.value = listOf(List(4) { GameUtils.notFoundColor })
        availableColors.value = GameUtils.selectRandomColors(GameUtils.colorList, colorsCount)
        correctColors.value = GameUtils.selectRandomColors(availableColors.value, 4)
        highScoreButtonVisible.value = false
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Your score: ${attempts.value.size}",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 64.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            itemsIndexed(
                items = attempts.value,
                key = { index, _ -> index }) { index, colors ->

                GameRow(
                    selectedColors = colors,
                    feedbackColors = feedbacks.value.getOrNull(index)
                        ?: List(4) { GameUtils.notFoundColor },
                    clickable = index == attempts.value.size - 1,
                    onSelectColorClick = { buttonIndex ->
                        if (index == attempts.value.size - 1) {
                            attempts.value = attempts.value.toMutableList().also {
                                it[index] = GameUtils.selectNextAvailableColor(
                                    availableColors.value,
                                    it[index],
                                    buttonIndex
                                )
                            }
                        }
                    },
                    onCheckClick = {
                        if (index == attempts.value.size - 1 && colors != List(4) { Color.White }) {
                            val newFeedback =
                                GameUtils.checkColors(colors, correctColors.value)
                            if (newFeedback != List(4) { Color.Red }) {
                                attempts.value += listOf(initialAttempt)
                            }
                            feedbacks.value = feedbacks.value.toMutableList()
                                .apply { add(feedbacks.value.size - 1, newFeedback) }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LaunchedEffect(feedbacks.value) {
            highScoreButtonVisible.value = feedbacks.value.size >= 2 &&
                    feedbacks.value[feedbacks.value.size - 2].all { it == Color.Red }
        }
        if (highScoreButtonVisible.value) {
            Button(
                onClick = {
                    viewModel.score.intValue = attempts.value.size
                    coroutineScope.launch {
                        viewModel.saveScore()
                    }
                    navigateToResultsScreen(attempts.value.size)
                    clearGame()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(animationSpec = tween(durationMillis = 300)),
            ) {
                Text("HighScore table")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Button(
                onClick = navigateToProfileScreen
            ) {
                Text(text = "Log out")
            }
        }

    }
}