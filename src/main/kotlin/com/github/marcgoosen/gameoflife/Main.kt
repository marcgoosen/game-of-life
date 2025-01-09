package com.github.marcgoosen.gameoflife

fun main() {
    var universe = toad.moveRandomly()

    (0..100).forEach {
        println("Generation $it")
        println(universe.toFormattedString())
        println()
        Thread.sleep(500)
        universe = universe.evolve()
    }
}

val Universe.minX get() = minByOrNull { it.x }?.x ?: 0
val Universe.maxX get() = maxByOrNull { it.x }?.x ?: 0
val Universe.minY get() = minByOrNull { it.y }?.y ?: 0
val Universe.maxY get() = maxByOrNull { it.y }?.y ?: 0

val Universe.rows get() = minY..maxY
val Universe.columns get() = minX..maxX

fun Universe.toFormattedString() =
    rows.joinToString("\n") { y ->
        columns.joinToString("") { x ->
            if (contains(Point(x, y))) {
                "X"
            } else {
                "."
            }
        }
    }

fun String.parseUniverse(): Universe {
    val points = mutableSetOf<Point>()
    split("\n").forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
            if (char == 'X') {
                points.add(Point(x, y))
            }
        }
    }
    return points.asUniverse()
}

fun Set<Point>.asUniverse() = Universe(this)