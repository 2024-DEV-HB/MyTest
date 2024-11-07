package com.example.tictactoetest.model

import com.example.tictactoetest.GameState
import com.example.tictactoetest.Player
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class GameStateTest {

    @Test
    fun `boad should be empty at start and player X starts`() {
        val game = GameState()

        assertTrue(game.board.all { it == null })
        assertEquals(Player.X, game.currentPlayer)

    }

    @Test
    fun `players should alternate turns`() {
        val game = GameState()

        game.playTurn(0)
        assertEquals(Player.O, game.currentPlayer)

        game.playTurn(1)
        assertEquals(Player.X, game.currentPlayer)
    }

}