package com.example.tictactoetest

class GameState {

    val board = MutableList<Player?>(9) { null }
    var currentPlayer = Player.X

}

enum class Player {
    X,
    O
}
