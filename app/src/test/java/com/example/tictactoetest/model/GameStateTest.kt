package com.example.tictactoetest.model

import com.example.tictactoetest.GameState
import com.example.tictactoetest.Player
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
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

    @Test
    fun `should detect a winning row`() {
        val game = GameState()
        game.playTurn(0) // X joue
        game.playTurn(3) // O joue
        game.playTurn(1) // X joue
        game.playTurn(4) // O joue
        game.playTurn(2) // X gagne
        assertEquals(Player.X, game.checkWinner())

    }

    @Test
    fun `should return draw when all spaces are filled and no winner`() {
        val game = GameState()

        game.playTurn(0)
        game.playTurn(4)
        game.playTurn(8)
        game.playTurn(1)
        game.playTurn(7)
        game.playTurn(6)
        game.playTurn(2)
        game.playTurn(5)
        game.playTurn(3)


        assertNull(game.checkWinner())
    }

}