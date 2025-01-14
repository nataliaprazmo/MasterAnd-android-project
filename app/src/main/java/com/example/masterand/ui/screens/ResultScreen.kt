package com.example.masterand.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.masterand.viewmodel.ResultsViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ResultScreen(
    score: Int,
    navigateToGameScreen: () -> Unit,
    navigateToProfileScreen: () -> Unit,
//    viewModel: ResultsViewModel = viewModel(factory = AppViewModelProvider.Factory)
    viewModel: ResultsViewModel = hiltViewModel<ResultsViewModel>()
) {
    val topScores by viewModel.topScores.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text("Results", style = MaterialTheme.typography.displayLarge)
        Spacer(modifier = Modifier.height(32.dp))
        Text("Recent score: $score", style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(topScores) { playerScore ->
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = playerScore.playerName,
                            style = MaterialTheme.typography.displaySmall
                        )
                        Text(
                            text = playerScore.score.toString(),
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                    HorizontalDivider(
                        color = Color.Gray,
                        thickness = 1.dp
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navigateToGameScreen()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Play again")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navigateToProfileScreen()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ResultScreen(
        5, {}, {}
    )
}