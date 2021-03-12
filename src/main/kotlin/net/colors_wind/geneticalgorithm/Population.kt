package net.colors_wind.geneticalgorithm

import net.colors_wind.geneticalgorithm.GlobalConfig.N
import net.colors_wind.geneticalgorithm.GlobalConfig.xMax
import net.colors_wind.geneticalgorithm.GlobalConfig.xMin
import net.colors_wind.geneticalgorithm.GlobalConfig.yMax
import net.colors_wind.geneticalgorithm.GlobalConfig.yMin
import java.util.*


class Population(val config: PopulationConfig) {
    val random = Random()
    private val chromosomes = ArrayList<Chromosome>(N).apply {
        repeat(N) {
            this.add(
                Chromosome(
                    this@Population,
                    random.uniform(xMin, xMax),
                    random.uniform(yMin, yMax)
                )
            )
        }
    }.toTypedArray()
    private val best = arrayListOf<Chromosome>()


    fun selection() {
        val copy = chromosomes.copyOf()
        val fitnessSum = chromosomes.sumByDouble { it.fitness }
        repeat(N) {
            var randomDouble = random.uniform(0.0, fitnessSum)
            for (chromosome in copy) if (chromosome.fitness >= randomDouble) {
                chromosomes[it] = chromosome
                break
            } else randomDouble -= chromosome.fitness
        }
        chromosomes.maxByOrNull { it.fitness }?.let { best.add(it) }
    }

    fun crossover() {
        var oneIndex = -1
        repeat(N) { otherIndex ->
            if (random.nextDouble() < config.pc) oneIndex =
                oneIndex.takeIf { it >= 0 }?.let { oneIndex ->
                    val one = chromosomes[oneIndex]
                    val other = chromosomes[otherIndex]
                    val first = Chromosome(this, one.x, other.y)
                    val second = Chromosome(this, other.x, one.y)
                    chromosomes[oneIndex] = first
                    chromosomes[otherIndex] = second
                    -1
                } ?: otherIndex
        }
    }

    fun mutation() {
        repeat(N) {
            if (random.nextDouble() < config.pm) chromosomes[it].mutation()
        }
    }

    fun nextGeneration() {
        selection()
        crossover()
        mutation()
    }

    fun getBest(): Chromosome? {
        return chromosomes.maxByOrNull { it.fitness }
    }

    fun print() {
        println(chromosomes.maxByOrNull { it.fitness })
    }
}