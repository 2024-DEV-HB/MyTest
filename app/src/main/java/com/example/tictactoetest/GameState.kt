package com.example.tictactoetest

class GameState {

    val board = MutableList<Player?>(9) { null }
    var currentPlayer = Player.X

    fun playTurn(position: Int) : Boolean {
        if (position !in 0..8 || board[position] != null) return false
        board[position] = currentPlayer
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
        return true
    }

}

enum class Player {
    X,
    O
}
