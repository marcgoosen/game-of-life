package com.github.marcgoosen.gameoflife

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class GameTest {
    private val universe = Universe(
        setOf(
            Point(1, 1),
            Point(1, 2),
            Point(2, 1),
            Point(2, 2)
        )
    )

    @Test
    fun `it should determine neighbors`() {
        assertEquals(
            Point(5, 5).neighbors(), setOf(
                Point(5, 4),
                Point(5, 6),
                Point(4, 5),
                Point(6, 5),
                Point(4, 4),
                Point(6, 4),
                Point(4, 6),
                Point(6, 6)
            )
        )
    }

    @Test
    fun `it should determine neighbors in the negative space`() {
        assertEquals(
            Point(-5, -5).neighbors(), setOf(
                Point(-5, -4),
                Point(-5, -6),
                Point(-4, -5),
                Point(-6, -5),
                Point(-4, -4),
                Point(-6, -4),
                Point(-4, -6),
                Point(-6, -6)
            )
        )
    }

    @Test
    fun `it should determine candidate new neighbors to be born`() {
        val candidateNeighbors = universe.allNeighbors()
        assertEquals(
            candidateNeighbors, setOf(
                Point(0, 0),
                Point(1, 0),
                Point(2, 0),
                Point(3, 0),
                Point(0, 1),
                Point(3, 1),
                Point(0, 2),
                Point(3, 2),
                Point(0, 3),
                Point(1, 3),
                Point(2, 3),
                Point(3, 3)
            )
        )
    }

    @Test
    fun `it should count the neighbors in a universe`() {
        val neighborCount = universe.countNeighbors(Point(1, 1))
        assertEquals(neighborCount, 3)
    }

    @Test
    fun `it should evolve, but stay the same`() {
        val stillLives = listOf(
            loaf.moveRandomly(),
            block.moveRandomly(),
            beehive.moveRandomly(),
            boat.moveRandomly(),
            tub.moveRandomly(),
        )

        stillLives.forEach {
            assertEquals(it, it.evolve())
        }
    }

    @Test
    fun `it should evolve and oscillate (period 2)`() {
        val stillLives = listOf(
            blinker.moveRandomly(),
            toad.moveRandomly(),
            beacon.moveRandomly(),
        )

        stillLives.forEach {
            val fase1 = it.evolve()
            val fase2 = fase1.evolve()
            val fase3 = fase2.evolve()
            assertEquals(fase2, it)
            assertNotEquals(fase2, fase1)
            assertEquals(fase3, fase1)
        }
    }

    @Test
    fun `it should evolve and oscillate (period 3)`() {
        val stillLives = listOf(
            pulsar.moveRandomly(),
        )

        stillLives.forEach {
            val fase1 = it.evolve()
            val fase2 = fase1.evolve()
            val fase3 = fase2.evolve()
            val fase4 = fase3.evolve()
            assertEquals(fase3, it)
            assertNotEquals(fase2, fase1)
            assertNotEquals(fase3, fase2)
            assertEquals(fase4, fase1)
        }
    }
}