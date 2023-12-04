fun main() {
    fun part1(input: List<String>): Int {
        val digits = input.map { str -> str.filter { it.isDigit() } }
        return digits.sumOf { it -> (it.first().toString() + it.last().toString()).toInt()}
    }

    fun part2(input: List<String>): Int {
        val digits = mapOf("one"    to "1",
                           "two"    to "2",
                           "three"  to "3",
                           "four"   to "4",
                           "five"   to "5",
                           "six"    to "6",
                           "seven"  to "7",
                           "eight"  to "8",
                           "nine"   to "9")
        val digitPattern = digits.keys.joinToString("|")
        val digitPatternReversed = digitPattern.reversed()
        val matcher = Regex("$digitPattern|[0-9]")
        val matcherReverse = Regex("$digitPatternReversed|[0-9]")
        return input.sumOf {
            val first = matcher.find(it)?.value
            val last = matcherReverse.find(it.reversed())?.value?.reversed()
            ((digits[first] ?: first) + (digits[last] ?: last)).toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_part1_test")
    check(part1(testInput) == 142)

    val testInput2 = readInput("Day01_part2_test")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
