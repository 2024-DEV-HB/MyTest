package com.example.tictactoetest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictactoetest.model.Player

class GameManagerViewModel : ViewModel() {
    private val gameState = GameState()

    private val _board = MutableLiveData<List<Player?>>()
    val board: LiveData<List<Player?>> = _board

    private val _currentPlayer = MutableLiveData<Player>()
    val currentPlayer: LiveData<Player> = this._currentPlayer

    private val _winner = MutableLiveData<Player?>()
    val winner: LiveData<Player?> = _winner

    private val _isDraw = MutableLiveData<Boolean>()
    val isDraw: LiveData<Boolean> = _isDraw

    init {
        _board.value = gameState.board
        _currentPlayer.value = gameState.currentPlayer
    }

    fun playTurn(position : Int) {
        if (gameState.playTurn(position)) {
            _board.value = gameState.board
            _currentPlayer.value = gameState.currentPlayer
            _winner.value = gameState.checkWinner()
            _isDraw.value = gameState.checkDraw()
        }
    }

    fun resetGame() {
        gameState.resetGame()
        _board.value = gameState.board
        _currentPlayer.value = gameState.currentPlayer
    }
}