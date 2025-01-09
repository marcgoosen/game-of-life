package com.github.marcgoosen.gameoflife

import kotlin.random.Random

val block = """
        XX
        XX
    """.trimIndent().parseUniverse()

val beehive = """
        .XX.
        X..X
        .XX.
    """.trimIndent().parseUniverse()

val loaf = """
        .XX.
        X..X
        .X.X
        ..X.
    """.trimIndent().parseUniverse()

val boat = """
        XX.
        X.X
        .X.
    """.trimIndent().parseUniverse()

val tub = """
        .X.
        X.X
        .X.
    """.trimIndent().parseUniverse()

val blinker = """
        XXX
    """.trimIndent().parseUniverse()

val toad = """
        .XXX
        XXX.
    """.trimIndent().parseUniverse()

val beacon = """
        XX..
        XX..
        ..XX
        ..XX
    """.trimIndent().parseUniverse()

val pulsar = """
        ..XXX...XXX..
        .............
        X....X.X....X
        X....X.X....X
        X....X.X....X
        ..XXX...XXX..
        .............
        ..XXX...XXX..
        X....X.X....X
        X....X.X....X
        X....X.X....X
        .............
        ..XXX...XXX..
    """.trimIndent().parseUniverse()

val glider = """
        .X.
        ..X
        XXX
    """.trimIndent().parseUniverse()

fun Universe.moveTo(x: Int = 0, y: Int = 0) = map { Point(it.x + x, it.y + y) }.toSet().asUniverse()
fun Universe.moveRandomly() = moveTo(x = Random.nextInt(-100, 100), y = Random.nextInt(-100, 100))

