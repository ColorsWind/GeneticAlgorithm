package net.colors_wind.geneticalgorithm

import java.io.File
import java.io.FileWriter
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.CountDownLatch

fun main(args: Array<String>) {
    val pcs = 0.1.rangeTo(1.0).step(0.1).toList()
    val pms = 0.01.rangeTo(0.10).step(0.01).toList()
    val queue = ConcurrentLinkedQueue<PopulationConfig>()
    val result = Vector<String>()
    for (pc in pcs) for (pm in pms) {
        queue.add(PopulationConfig(pc, pm))
    }
    val latch = CountDownLatch(GlobalConfig.thread)
    repeat(GlobalConfig.thread) {
        Thread(WorkThread(it, queue, result, latch)).start()
    }
    latch.await()
    val writer = FileWriter(File("result.csv").apply { takeUnless { it.exists() }?.createNewFile() })
    writer.appendLine("pc,pm,avg,worst,worst-x,worst-y,best,best-x,best-y")
    result.forEach { writer.appendLine(it) }
    writer.flush()
    writer.close()
    println("Write completed!")

}

class WorkThread(
    private val index: Int,
    private val queue: Queue<PopulationConfig>,
    val result: Vector<String>,
    val latch: CountDownLatch
) :
    Runnable {
    override fun run() {
        println("Thread #$index Start")
        while (true) queue.poll()?.let { config ->
            val populations = (0..GlobalConfig.repeat).map {
                val population = Population(config)
                repeat(GlobalConfig.Gmax) {
                    population.nextGeneration()
                }
                population
            }
            val bestChromosomes = populations.map { it.getBest() }
            result.add("${config.pc}," +
                    "${config.pm}," +
                    "${bestChromosomes.sumByDouble { it!!.fitness } / bestChromosomes.size}," +
                    "${bestChromosomes.minByOrNull { it!!.fitness }!!.fitness}," +
                    "${bestChromosomes.minByOrNull { it!!.fitness }!!.getData()}," +
                    "${bestChromosomes.maxByOrNull { it!!.fitness }!!.fitness}," +
                    "${bestChromosomes.maxByOrNull { it!!.fitness }!!.getData()},"
            )
            println("Rest task: ${queue.size}")
        } ?: break
        latch.countDown()
        println("Thread #$index Stop")
    }

}