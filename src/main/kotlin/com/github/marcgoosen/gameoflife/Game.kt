package com.github.marcgoosen.gameoflife

data class Point(val x: Int, val y: Int)
data class Universe(val points: Set<Point>) : Set<Point> by points

fun Point.neighbors() = (-1..1).flatMap { dx ->
    (-1..1).map { dy ->
        Point(x + dx, y + dy)
    }
}.toSet() - this

fun Universe.allNeighbors() = flatMap { it.neighbors() }.toSet() - this
fun Universe.countNeighbors(point: Point) = point.neighbors().count { contains(it) }
fun Universe.shouldSurvive(point: Point) = countNeighbors(point) in 2..3
fun Universe.shouldBeBorn(point: Point) = countNeighbors(point) == 3

fun Universe.evolve() = Universe(filter {
    shouldSurvive(it)
}.toSet() + allNeighbors().filter {
    shouldBeBorn(it)
}.toSet())