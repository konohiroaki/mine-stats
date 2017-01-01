fun printBoard(board: Array<Array<Cell>>) {
    for (y in 0..board.size - 1) {
        for (x in 0..board[y].size - 1) {
            print(board[y][x].print + "  ")
        }
        println()
    }
}

fun <T> foreachCells(board: Array<Array<Cell>>, func: (Array<Array<Cell>>, Int, Int) -> T) {
    for (y in 0..board.size - 1) {
        for (x in 0..board[0].size - 1) {
            func(board, y, x)
        }
    }
}

fun <T> foreachNeighbors(board: Array<Array<Cell>>, y: Int, x: Int, checkSheet: Array<Array<Boolean>>?,
                         func: (Array<Array<Cell>>, Int, Int, Array<Array<Boolean>>?) -> T): List<T> {
    val list: MutableList<T> = mutableListOf()
    for (yy in -1..1) {
        val yyy = y + yy
        for (xx in -1..1) {
            val xxx = x + xx
            if (yy == 0 && xx == 0
                    || yyy < 0 || yyy >= board.size
                    || xxx < 0 || xxx >= board[0].size) {
                continue
            }
            list.add(func(board, yyy, xxx, checkSheet))
        }
    }
    return list
}

fun countCells(board: Array<Array<Cell>>, func: (Array<Array<Cell>>, Int, Int, Array<Array<Boolean>>) -> Boolean): Int {
    val checkSheet = Array(board.size, { Array(board[0].size, { false }) })
    var count = 0
    foreachCells(board, { board, y, x ->
        if (!checkSheet[y][x] && func(board, y, x, checkSheet)) {
            count++
        }
    })
    return count
}
