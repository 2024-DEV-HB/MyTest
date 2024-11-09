package com.example.tictactoetest

import com.example.tictactoetest.model.Player

class GameManager {

    val board = MutableList<Player?>(9) { null }
    var currentPlayer = Player.X

    fun playTurn(position: Int) : Boolean {
        if (position !in 0..8 || board[position] != null) return false
        board[position] = currentPlayer
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
        return true
    }

    fun checkWinner(): Player? {
        val winningPositions = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // Row
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // Col
            listOf(0, 4, 8), listOf(2, 4, 6), // Diagonal
        )

        winningPositions.forEach { (a, b, c) ->
            if (board[a] != null && board[a] == board[b] && board[b] == board[c]) {
                return board[a]
            }
        }
        return null
    }

    fun checkDraw(): Boolean {
        return board.all { it != null } && checkWinner() == null
    }

    fun resetGame() {
        board.fill(null)
        currentPlayer = Player.X
    }

}

