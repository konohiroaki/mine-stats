import java.io.File

val threebv: MutableMap<Int, Long> = mutableMapOf()
val islands: MutableMap<Int, Long> = mutableMapOf()
val openings: MutableMap<Int, Long> = mutableMapOf()
var zero: Long = 0
var one: Long = 0
var two: Long = 0
var three: Long = 0
var four: Long = 0
var five: Long = 0
var six: Long = 0
var seven: Long = 0
var eight: Long = 0

fun storeStats(board: Array<Array<Cell>>) {
    val threebvCount = count3bv(board)
    val islandsCount = countIslands(board)
    val openingsCount = countOpenings(board)
    threebv.put(threebvCount, threebv.getOrElse(threebvCount, { 0 }) + 1)
    islands.put(islandsCount, islands.getOrElse(islandsCount, { 0 }) + 1)
    openings.put(openingsCount, openings.getOrElse(openingsCount, { 0 }) + 1)

    zero += countCellType(board, Cell.ZERO)
    one += countCellType(board, Cell.ONE)
    two += countCellType(board, Cell.TWO)
    three += countCellType(board, Cell.THREE)
    four += countCellType(board, Cell.FOUR)
    five += countCellType(board, Cell.FIVE)
    six += countCellType(board, Cell.SIX)
    seven += countCellType(board, Cell.SEVEN)
    eight += countCellType(board, Cell.EIGHT)
}

fun logStatsSummary(height: Int, width: Int, mines: Int, count: Int) {
    val threebvMap = threebv.toSortedMap()
    val islandsMap = islands.toSortedMap()
    val openingsMap = openings.toSortedMap()
    val zeroOccurrence = zero.toDouble() / count
    val oneOccurrence = one.toDouble() / count
    val twoOccurrence = two.toDouble() / count
    val threeOccurrence = three.toDouble() / count
    val fourOccurrence = four.toDouble() / count
    val fiveOccurrence = five.toDouble() / count
    val sixOccurrence = six.toDouble() / count
    val sevenOccurrence = seven.toDouble() / count
    val eightOccurrence = eight.toDouble() / count

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