fun main() {

    val toCubeSet: (String) -> Pair<String, Int> = fun(grabText: String): Pair<String, Int> {
        val (cubeCountText, color) = grabText.trim().split(" ")
        return Pair(color, cubeCountText.toInt())
    }

    val toGames = fun(input: List<String>): Map<Int, List<Pair<String, Int>>> {
        return input.fold(emptyMap<Int, List<Pair<String, Int>>>()) { acc, s ->

            val (gameText, grabsText) = s.split(":")
            val gameNo = gameText.split(" ").last().toInt()
            val cubeSetsForGame = grabsText.split(",", ";").map(toCubeSet)

            acc + Pair(gameNo, cubeSetsForGame)
        }
    }

    val legalGame: (Map.Entry<Int, List<Pair<String,Int>>>) -> Boolean = fun(entry: Map.Entry<Int, List<Pair<String, Int>>>):Boolean {
        val cubesInGame = mapOf("red" to 12, "green" to 13, "blue" to 14)
        return entry.value.map { (color, cubeCount) -> cubesInGame[color]!! >= cubeCount }.toSet().contains(false).not()
    }

    val smallestCubeSetForGame: (Map.Entry<Int, List<Pair<String,Int>>>) -> Map<String, Int> = fun(entry: Map.Entry<Int, List<Pair<String, Int>>>):Map<String, Int> {
        val byColor = entry.value.groupBy({ it.first }, { it.second })
        return byColor.map { it.key to it.value.max() }.toMap()
    }

    fun part1(input: List<String>): Int {
        return toGames(input).filter(legalGame).keys.sum()
    }

    fun part2(input: List<String>): Int {
        return toGames(input).map(smallestCubeSetForGame).sumOf { it.values.reduce { acc, i -> acc * i } }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_part1_test")
    check(part1(testInput) == 8)

    //val testInput2 = readInput("Day02_part2_test")
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

