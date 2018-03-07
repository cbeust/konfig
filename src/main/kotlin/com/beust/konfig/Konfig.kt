package com.beust.konfig

import java.io.Reader
import javax.script.ScriptEngineManager

class Konfig {
    inline fun <reified T> parse(reader: Reader) : T {
        val klass = T::class
        val ind = klass.qualifiedName?.lastIndexOf(".")
        val pack = klass.qualifiedName?.substring(0, ind!!)
        println("K: $klass, package: $pack")

        System.setProperty("idea.io.use.fallback", "true")
        println("Supported: " + ScriptEngineManager().engineFactories.map { it.extensions })
        val engine = ScriptEngineManager().getEngineByExtension("kts")
        val result = engine!!.eval(reader) as T
        return result
    }
}