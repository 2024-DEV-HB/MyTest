package com.example.tictactoetest

import com.example.tictactoetest.model.Player
import junit.framework.TestCase.assertEquals
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

}