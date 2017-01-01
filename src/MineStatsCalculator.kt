fun count3bv(board: Array<Array<Cell>>): Int {
    return countCells(board, { board, y, x, checkSheet ->
        if (board[y][x] == Cell.MINE || board[y][x] != Cell.ZERO && neighborHas(board, y, x, Cell.ZERO)) {
            false
        } else if (board[y][x] == Cell.ZERO) {
            floodFillCheckSheet(board, y, x, { board, y, x -> board[y][x] == Cell.ZERO }, checkSheet)
            true
        } else {
            checkSheet[y][x] = true
            true
        }
    })
}

fun countIslands(board: Array<Array<Cell>>): Int {
    return countCells(board, { board, y, x, checkSheet ->
        if (board[y][x] == Cell.MINE || board[y][x] == Cell.ZERO || neighborHas(board, y, x, Cell.ZERO)) {
            false
        } else {
            floodFillCheckSheet(board, y, x, { board, y, x ->
                if (neighborHas(board, y, x, Cell.ZERO)) false
                else Cell.ONE.num <= board[y][x].num && board[y][x].num <= Cell.EIGHT.num
            }, checkSheet)
            true
        }
    })
}

fun countOpenings(board: Array<Array<Cell>>): Int {
    return countCells(board, { board, y, x, checkSheet ->
        if (board[y][x] == Cell.ZERO) {
            floodFillCheckSheet(board, y, x, { board, y, x -> board[y][x] == Cell.ZERO }, checkSheet)
            true
        } else {
            false
        }
    })
}

fun countCellType(board: Array<Array<Cell>>, target: Cell): Int {
    return countCells(board, { board, y, x, dummy -> board[y][x] == target })
}

private fun floodFillCheckSheet(board: Array<Array<Cell>>, y: Int, x: Int,
                                condition: (Array<Array<Cell>>, Int, Int) -> Boolean, checkSheet: Array<Array<Boolean>>) {
    checkSheet[y][x] = true
    if (condition(board, y, x)) {
        foreachNeighbors(board, y, x, checkSheet, {
            board, yyy, xxx, checkSheet ->
            if (checkSheet != null && !checkSheet[yyy][xxx]) {
                floodFillCheckSheet(board, yyy, xxx, condition, checkSheet)
            }
        })
    }
}

private fun neighborHas(board: Array<Array<Cell>>, y: Int, x: Int, target: Cell): Boolean {
    return foreachNeighbors(board, y, x, null, { board, yyy, xxx, dummy ->
        board[yyy][xxx] == target
    }).contains(true)
}