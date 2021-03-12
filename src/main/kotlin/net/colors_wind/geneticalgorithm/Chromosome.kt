package net.colors_wind.geneticalgorithm

import net.colors_wind.geneticalgorithm.GlobalConfig.fitness
import net.colors_wind.geneticalgorithm.GlobalConfig.xMax
import net.colors_wind.geneticalgorithm.GlobalConfig.xMin
import net.colors_wind.geneticalgorithm.GlobalConfig.yMax
import net.colors_wind.geneticalgorithm.GlobalConfig.yMin

class Chromosome(val population: Population, val x: Double, val y: Double) {

    val fitness = fitness()

    fun mutation(): Chromosome {
        return if (population.random.nextBoolean()) {
            Chromosome(
                population,
                population.random.gaussian(x, population.config.sigma).limit(xMin, xMax),
                y
            )
        } else {
            Chromosome(
                population,
                x,
                population.random.gaussian(y, population.config.sigma).limit(yMin, yMax),
            )
        }
    }

    fun getData() : String {
        return "$x,$y"
    }
    override fun toString(): String {
        return "Chromosome(x=$x, y=$y, fitness=$fitness)"
    }


}


