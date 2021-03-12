package net.colors_wind.geneticalgorithm

import java.util.*


fun Random.uniform(a: Double, b: Double) = nextDouble() * (b - a) + a

fun Random.gaussian(avg: Double, sigma: Double) = nextGaussian() * sigma + avg
fun Double.limit(min: Double, max: Double) =
    when {
        this > max -> max
        this < min -> min
        else -> this
    }

infix fun ClosedRange<Double>.step(step: Double): Iterable<Double> {
    require(start.isFinite())
    require(endInclusive.isFinite())
    require(step > 0.0) { "Step must be positive, was: $step." }
    val sequence = generateSequence(start) { previous ->
        if (previous == Double.POSITIVE_INFINITY) return@generateSequence null
        val next = previous + step
        if (next > endInclusive) null else next
    }
    return sequence.asIterable()
}
