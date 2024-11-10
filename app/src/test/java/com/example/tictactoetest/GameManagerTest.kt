package com.example.tictactoetest

import com.example.tictactoetest.model.Player
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Test

class GameManagerTest {

    @Test
    fun `board should be empty at start and player X starts`() {
        val game = GameManager()

        assertTrue(game.board.all { it == null })
        assertEquals(Player.X, game.currentPlayer)

    }

    @Test
    fun `players should alternate turns`() {
        val game = GameManager()

        game.playTurn(0)
        assertEquals(Player.O, game.currentPlayer)

        game.playTurn(1)
        assertEquals(Player.X, game.currentPlayer)
    }

    @Test
    fun `should detect a winning row`() {
        val game = GameManager()
        game.playTurn(0) // X play
        game.playTurn(3) // O play
        game.playTurn(1) // X play
        game.playTurn(4) // O play
        game.playTurn(2) // X win
        assertEquals(Player.X, game.checkWinner())

    }

    @Test
    fun `should return draw when all spaces are filled and no winner`() {
        val game = GameManager()

        game.playTurn(0) // X play
        game.playTurn(4) // O play
        game.playTurn(8) // X play
        game.playTurn(1) // O play
        game.playTurn(7) // X play
        game.playTurn(6) // O play
        game.playTurn(2) // X play
        game.playTurn(5) // O play
        game.playTurn(3) // X play and it's a draw


        assertNull(game.checkWinner())
        assertTrue(game.checkDraw())
    }

    @Test
    fun `should not allow playing on an occupied position`() {
        val game = GameManager()

        assertTrue(game.playTurn(0)) // Player X plays in pos 0

        assertFalse(game.playTurn(0)) // Payer O also plays in po 0

        assertEquals(Player.O, game.currentPlayer) // It's O's turn because he didn't take a free position
    }

    @Test
    fun `should reset the game board and current player`() {
        val game = GameManager()

        game.playTurn(0) // X play in position 0
        game.playTurn(1) // O play in position 1
        game.resetGame() // the board is empty

        assertTrue(game.board.all {  it == null })
        assertEquals(Player.X, game.currentPlayer)
    }

}