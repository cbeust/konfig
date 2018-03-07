package com.beust.konfig

import java.io.Reader
import java.io.StringReader
import javax.script.ScriptEngineManager

class Konfig {
    inline fun <reified T> parse(reader: Reader) : T {
        val klass = T::class
        val pack = klass.java.`package`.name

        val tmpReader = StringReader(buildString {
            append("package $pack\n")
            append(reader.readText())
        })

        System.setProperty("idea.io.use.fallback", "true")
        val start = System.currentTimeMillis()
        println("Supported: " + ScriptEngineManager().engineFactories.map { it.extensions })
        val engine = ScriptEngineManager().getEngineByExtension("kts")
        if (engine != null) {
            val result = engine.eval(tmpReader) as T
            println("Time to compile the script: " + (System.currentTimeMillis() - start) + " ms")
            return result
        } else {
            throw IllegalArgumentException("Couldn't find the Kotlin Script interpreter")
        }
    }
}