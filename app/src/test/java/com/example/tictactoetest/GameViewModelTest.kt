package com.example.tictactoetest

import com.example.tictactoetest.model.Player
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import org.junit.Before
import org.junit.Test



class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel()
    }

    @Test
    fun `initial state should be InProgress with empty board and current player X`() = runTest {

        withTimeout(1000L) {
            val inProgressState = viewModel.state.first() as TicTacToeState.InProgress
            assertEquals(9, inProgressState.board.size)
            inProgressState.board.forEachIndexed { _, value -> assertEquals(null, value) }
            assertEquals(Player.X, inProgressState.currentPlayer)

        }
    }

    @Test
    fun `should update state to win when a player wins`() = runTest {

        viewModel.playTurn(0)
        viewModel.playTurn(3)
        viewModel.playTurn(1)
        viewModel.playTurn(4)
        viewModel.playTurn(2)

        val currentState = viewModel.state.value
        assertTrue(currentState is TicTacToeState.Win)
        assertEquals(Player.X, (currentState as TicTacToeState.Win).winner)
    }

    @Test
    fun `should update state to draw when all space are filled with no winner`() = runTest {

        viewModel.playTurn(0)
        viewModel.playTurn(1)
        viewModel.playTurn(2)
        viewModel.playTurn(4)
        viewModel.playTurn(3)
        viewModel.playTurn(5)
        viewModel.playTurn(7)
        viewModel.playTurn(6)
        viewModel.playTurn(8)

        val currentState = viewModel.state.value
        assertTrue(currentState is TicTacToeState.Draw)

    }

    @Test
    fun `should update state to InProgress after a valid move`() = runTest {

        viewModel.playTurn(0)
        val currentState = viewModel.state.value

        assertTrue(currentState is TicTacToeState.InProgress)
        assertEquals(Player.O, (currentState as TicTacToeState.InProgress).currentPlayer)
    }

    @Test
    fun `should reset the game state to initial values`() = runTest {
        viewModel.playTurn(0)
        viewModel.playTurn(1)
        viewModel.playTurn(2)

        viewModel.resetGame()

        val resetState = viewModel.state.value

        assertTrue(resetState is TicTacToeState.InProgress)
        assertEquals(Player.X, (resetState as TicTacToeState.InProgress).currentPlayer)
        assertTrue(resetState.board.all { it == null })
    }

}