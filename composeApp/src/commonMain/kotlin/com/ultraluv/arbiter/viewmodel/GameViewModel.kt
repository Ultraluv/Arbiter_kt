package com.ultraluv.arbiter.viewmodel

import com.ultraluv.arbiter.engine.util.mutate
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ultraluv.arbiter.engine.gridGenerator.MineGridGenerator
import com.ultraluv.arbiter.engine.gridGenerator.NoGuessMineGridGenerator
import com.ultraluv.arbiter.engine.model.base.GridSize
import com.ultraluv.arbiter.engine.model.base.Position
import com.ultraluv.arbiter.engine.model.cell.MineCell
import com.ultraluv.arbiter.engine.model.cell.RawCell
import com.ultraluv.arbiter.engine.model.grid.Grid
import com.ultraluv.arbiter.engine.model.grid.impl.EmptyMineGrid
import com.ultraluv.arbiter.engine.model.grid.impl.MutableMineGrid
import com.ultraluv.arbiter.game.GameState
import com.ultraluv.arbiter.game.GameUIState
import com.ultraluv.arbiter.mapper.toDisplayGridInformation
import com.ultraluv.arbiter.mode.ModeState
import com.ultraluv.arbiter.ui.gameScreen_UI.action.CellClick
import com.ultraluv.arbiter.ui.gameScreen_UI.action.CellClickListener
import com.ultraluv.arbiter.ui.gameScreen_UI.model.displayGridInformation.DisplayGridInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel {
    private val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.Default
    )

    private var grid: Grid? = null
    private val _gameUIState = mutableStateOf(
        GameUIState(
            displayGridInfo = DisplayGridInformation.empty(GridSize.setGridSize(30, 16)),
            mineCount = 99,
            flagCount = 0,
            isNoGuess = true,
            gameState = GameState.Idle
        )
    )
    val gameUIState: State<GameUIState> = _gameUIState

    private val _modeState = mutableStateOf<ModeState>(
        ModeState.Flag
    )
    val modeState: State<ModeState> = _modeState

    val cellClickListener = CellClickListener { action ->
        var grid = this@GameViewModel.grid ?: return@CellClickListener
        var gameState = _gameUIState.value.gameState

        // start game
        if (grid is EmptyMineGrid) {
            if (_gameUIState.value.gameState == GameState.Loading) return@CellClickListener
            scope.launch {
                startGame(
                    gridSize = _gameUIState.value.displayGridInfo.gridSize,
                    mineCount = _gameUIState.value.mineCount,
                    isNoGuess = _gameUIState.value.isNoGuess,
                    startPosition = action.position
                )
            }
            _gameUIState.value = _gameUIState.value.copy(
                displayGridInfo = grid.toDisplayGridInformation(),
                gameState = GameState.Loading
            )
            return@CellClickListener
        }

        if(gameUIState == GameState.Victory || gameState == GameState.Defeat) return@CellClickListener

        when (action) {
            is CellClick.OnUnFlaggedCellClicked -> {
                grid = grid.mutate {
                    val cell = this[action.position]
                    if (cell is RawCell.UnRevealedCell.UnFlaggedCell) {
                        when(modeState.value){
                            ModeState.Flag -> {
                                this[action.position] = cell.asFlagged()
                            }
                            ModeState.Click -> {
                                when (cell.cell) {
                                    is MineCell.Mine -> {
                                        this[action.position] = cell.asRevealed()
                                        gameState = GameState.Defeat
                                    }
                                    is MineCell.NumberCell.Cell -> {
                                        this[action.position] = cell.asRevealed()
                                    }
                                    is MineCell.NumberCell.ZeroCell -> {
                                        scope.launch {
                                            revalZeroCell(action.position)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            is CellClick.OnFlaggedCellClicked -> {
                grid = grid.mutate {
                    val cell = this[action.position]
                    if (cell is RawCell.UnRevealedCell.FlaggedCell) {
                        when(modeState.value){
                            ModeState.Flag -> {
                                this[action.position] = cell.asUnFlagged()
                            }
                            ModeState.Click -> {}
                        }
                    }
                }
            }

            is CellClick.OnNumberCellClicked -> {
                grid = grid.mutate {

                }
            }
        }

        this@GameViewModel.grid = grid
        _gameUIState.value = _gameUIState.value.copy(
            displayGridInfo = grid.toDisplayGridInformation(),
            gameState = gameState,
//            flagCount = grid.getFlagCount()
        )
    }

    fun idleGame(
        gridSize: GridSize = GridSize.setGridSize(30, 16),
        mineCount: Int = 99,
        isNoGuess: Boolean = true,
    ) {
        val newGrid = EmptyMineGrid.create(gridSize)
        this.grid = newGrid
        val gameState = GameState.Idle

        _gameUIState.value = _gameUIState.value.copy(
            displayGridInfo = newGrid.toDisplayGridInformation(),
            mineCount = mineCount,
            isNoGuess = isNoGuess,
            gameState = gameState
        )
    }

    suspend fun startGame(
        gridSize: GridSize = GridSize.setGridSize(30, 16),
        mineCount: Int = 99,
        isNoGuess: Boolean = true,
        startPosition: Position = Position.zero()
    ) {
        val newGrid = if(isNoGuess){
            NoGuessMineGridGenerator().generateGrid(
                gridSize = gridSize,
                starCell = startPosition,
                mineCount = mineCount
            )
        }else{
            MineGridGenerator().generateGrid(
                gridSize = gridSize,
                starCell = startPosition,
                mineCount = mineCount
            )
        }
        val revealedGrid = newGrid.mutate {
            scope.launch {
                revalZeroCell(startPosition)
            }
        }

        grid = revealedGrid
        val gameState = GameState.Playing

        _gameUIState.value = _gameUIState.value.copy(
            displayGridInfo = revealedGrid.toDisplayGridInformation(),
            gameState = gameState
        )
    }

    suspend fun MutableMineGrid.revalZeroCell(
        position: Position
    ) {
        val cell = this.getOrNull(position)

        if (cell is RawCell.UnRevealedCell.UnFlaggedCell) {
            val revealed = cell.asRevealed()
            this[position] = revealed

            delay(40L)

            grid = this
            _gameUIState.value = _gameUIState.value.copy(
                displayGridInfo = this.toDisplayGridInformation(),
                gameState = GameState.Playing
            )

            if (revealed.cell is MineCell.NumberCell.ZeroCell) {
                val currentRow = position.row
                val currentCol = position.col

                for (row in currentRow - 1..currentRow + 1) {
                    for (col in currentCol - 1..currentCol + 1) {
                        val currentPosition = Position(row, col)
                        val cell = this.getOrNull(currentPosition)
                        if (cell != null && cell is RawCell.UnRevealedCell.UnFlaggedCell) {
                            revalZeroCell(currentPosition)
                        }
                    }
                }
            }
        }
    }

    fun rePlay(
        gridSize: GridSize = GridSize.setGridSize(30, 16),
        mineCount: Int = 99,
        isNoGuess: Boolean = true,
    ) {
        val newGrid = EmptyMineGrid.create(gridSize)
        this.grid = newGrid
        val gameState = GameState.Idle

        _gameUIState.value = _gameUIState.value.copy(
            displayGridInfo = newGrid.toDisplayGridInformation(),
            mineCount = mineCount,
            isNoGuess = isNoGuess,
            gameState = gameState
        )
    }

    fun onChangeMode() {
        if (_modeState.value == ModeState.Flag) {
            _modeState.value = ModeState.Click
        } else{
            _modeState.value = ModeState.Flag
        }
    }

    fun clear() {
        scope.cancel()
    }
}
