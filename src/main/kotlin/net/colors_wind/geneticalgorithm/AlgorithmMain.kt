package net.colors_wind.geneticalgorithm

fun main(args: Array<String>) {
    val population = Population(PopulationConfig())
    repeat(GlobalConfig.Gmax) {
        population.nextGeneration()
        population.print()
    }
}

