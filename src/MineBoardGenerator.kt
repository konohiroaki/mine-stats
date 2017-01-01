import java.util.*

class MineBoardGenerator(val height: Int, val width: Int, val mines: Int) {
    val random = Random()

    fun generate(): Array<Array<Cell>> {
        val board = Array(height, { Array(width, { Cell.ZERO }) })
        distributeMines(board)
        setNumbers(board)
        return board
    }

    private fun distributeMines(board: Array<Array<Cell>>) {
        var placed = 0
        while (placed < mines) {
            val y = random.nextInt(height)
            val x = random.nextInt(width)
            if (board[y][x] != Cell.MINE) {
                board[y][x] = Cell.MINE
                placed++
            }
        }
    }

    private fun setNumbers(board: Array<Array<Cell>>) {
        foreachCells(board, { board, y, x ->
            if (board[y][x] != Cell.MINE) {
                board[y][x] = countNeighborMines(board, y, x)
            }
        })
    }

    private fun countNeighborMines(board: Array<Array<Cell>>, y: Int, x: Int): Cell {
        return Cell.from(foreachNeighbors(board, y, x, null, { board, yyy, xxx, dummy ->
            board[yyy][xxx] == Cell.MINE
        }).count { it })
    }
}