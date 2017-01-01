fun main(args: Array<String>) {
    val height = args.get(0).toInt()
    val width = args.get(1).toInt()
    val mines = args.get(2).toInt()
    val count = args.get(3).toInt()
    val generator = MineBoardGenerator(height, width, mines)

    val start = System.currentTimeMillis()
    for (i in 1..count) {
        val board = generator.generate()
        storeStats(board)
        if (i % 10000 == 0) {
            println("" + i + " " + (System.currentTimeMillis() - start) / 1000)
        }
    }
    logStatsSummary(height, width, mines, count)
}

enum class Cell(val num: Int, val print: String) {
    ZERO(0, "-"),
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    MINE(9, "*");

    companion object {
        fun from(count: Int): Cell = Cell.values().first { it.num == count }
    }
}