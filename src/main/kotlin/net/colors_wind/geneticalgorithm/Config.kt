package net.colors_wind.geneticalgorithm

import kotlin.math.PI
import kotlin.math.sin

object GlobalConfig {
    const val xMin = -3.0
    const val xMax = 12.1
    const val yMin = 4.1
    const val yMax = 5.8


    const val N = 100
    const val repeat = 30
    const val Gmax = 2000

    val thread = Runtime.getRuntime().availableProcessors()
    fun Chromosome.fitness() = 21.5 + x * sin(4 * PI * x) + y * sin(20 * PI * y)
}

class PopulationConfig(
    val pc: Double = 0.7,
    val pm: Double = 0.07,
    val sigma: Double = 0.001
) {
}