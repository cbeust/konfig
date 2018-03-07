package com.beust.konfig

import java.io.FileReader

enum class Db { POSTGRES, MYSQL }
data class Config(var name: String = "", val db: Db = Db.MYSQL)

fun main(args: Array<String>) {
    val r = Konfig().parse<Config>(FileReader("src/main/kotlin/com/beust/konfig/test.kts"))
    println("Result: $r")
}