import java.io.File

val threebv: MutableList<Int> = mutableListOf()
val islands: MutableList<Int> = mutableListOf()
val openings: MutableList<Int> = mutableListOf()
val zero: MutableList<Int> = mutableListOf()
val one: MutableList<Int> = mutableListOf()
val two: MutableList<Int> = mutableListOf()
val three: MutableList<Int> = mutableListOf()
val four: MutableList<Int> = mutableListOf()
val five: MutableList<Int> = mutableListOf()
val six: MutableList<Int> = mutableListOf()
val seven: MutableList<Int> = mutableListOf()
val eight: MutableList<Int> = mutableListOf()

fun storeStats(board: Array<Array<Cell>>) {
    threebv.add(count3bv(board))
    islands.add(countIslands(board))
    openings.add(countOpenings(board))
    zero.add(countCellType(board, Cell.ZERO))
    one.add(countCellType(board, Cell.ONE))
    two.add(countCellType(board, Cell.TWO))
    three.add(countCellType(board, Cell.THREE))
    four.add(countCellType(board, Cell.FOUR))
    five.add(countCellType(board, Cell.FIVE))
    six.add(countCellType(board, Cell.SIX))
    seven.add(countCellType(board, Cell.SEVEN))
    eight.add(countCellType(board, Cell.EIGHT))
}

fun logStatsSummary(height: Int, width: Int, mines: Int, count: Int) {
    val threebvMap = threebv.groupBy { it }.mapValues { it.value.size }.toSortedMap()
    val islandsMap = islands.groupBy { it }.mapValues { it.value.size }.toSortedMap()
    val openingsMap = openings.groupBy { it }.mapValues { it.value.size }.toSortedMap()
    val zeroOccurrence = zero.sum().toDouble() / count
    val oneOccurrence = one.sum().toDouble() / count
    val twoOccurrence = two.sum().toDouble() / count
    val threeOccurrence = three.sum().toDouble() / count
    val fourOccurrence = four.sum().toDouble() / count
    val fiveOccurrence = five.sum().toDouble() / count
    val sixOccurrence = six.sum().toDouble() / count
    val sevenOccurrence = seven.sum().toDouble() / count
    val eightOccurrence = eight.sum().toDouble() / count

    File("stats.csv").printWriter().use { out ->
        out.println("height:${height}, width:${width}, mines:${mines}, trial count:${count}")
        out.println()
        out.println("3BV (3BV, Count)")
        threebvMap.forEach { out.println("${it.key}, ${it.value}") }
        out.println()
        out.println("Islands (Islands, Count)")
        islandsMap.forEach { out.println("${it.key}, ${it.value}") }
        out.println()
        out.println("Openings (Openings, Count)")
        openingsMap.forEach { out.println("${it.key}, ${it.value}") }
        out.println()

        out.println("Average Number Occurrence (Number, Average Occurrence Per Board)")
        out.println("0, ${zeroOccurrence.format(5)}")
        out.println("1, ${oneOccurrence.format(5)}")
        out.println("2, ${twoOccurrence.format(5)}")
        out.println("3, ${threeOccurrence.format(5)}")
        out.println("4, ${fourOccurrence.format(5)}")
        out.println("5, ${fiveOccurrence.format(5)}")
        out.println("6, ${sixOccurrence.format(5)}")
        out.println("7, ${sevenOccurrence.format(5)}")
        out.println("8, ${eightOccurrence.format(5)}")
    }
}

private fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)