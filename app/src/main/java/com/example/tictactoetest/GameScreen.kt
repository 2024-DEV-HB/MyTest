package com.example.tictactoetest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoetest.model.Player

@Composable
fun GameScreen(viewModel: GameViewModel = viewModel()) {
    val gameState by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (gameState) {
            is TicTacToeState.InProgress -> {
                Text(
                    text = "Player ${(gameState as TicTacToeState.InProgress).currentPlayer}'s turn",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                GameGrid(
                    board = (gameState as TicTacToeState.InProgress).board,
                    onPositionClick = { position -> viewModel.playTurn(position)}
                )
            }
            is TicTacToeState.Win -> {
                Text(
                    text = "Player ${(gameState as TicTacToeState.Win).winner}'s win !",
                    fontSize = 24.sp,
                    color = Color.Green,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            TicTacToeState.Draw -> {
                Text(
                    text = "It's a Draw",
                    fontSize = 24.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { viewModel.resetGame() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ) {
            Text("Restart Game")
        }
    }
}

@Composable
fun GameGrid(board: List<Player?>, onPositionClick: (Int) -> Unit) {
    Column {
        for (row in 0..2) {
            Row(horizontalArrangement = Arrangement.Center) {
                for (col in 0..2) {
                    val position = row * 3 + col
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(4.dp)
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                            .clickable { onPositionClick(position) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = board[position]?.name ?: "",
                            fontSize = 36.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}