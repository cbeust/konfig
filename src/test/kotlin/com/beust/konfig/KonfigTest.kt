package com.beust.konfig

import org.testng.Assert
import org.testng.annotations.Test
import java.io.FileReader

enum class Db { POSTGRES, MYSQL }
data class Config(var name: String = "", val db: Db = Db.MYSQL)

@Test
class KonfigTest {

    fun simple() {
        val text = """ Config(name = "blah", db = Db.POSTGRES)\n""""
        val r = Konfig().parse<Config>(
                FileReader("src/test/resources/test.kts")
//                StringReader(text)
        )
        Assert.assertEquals(r, Config("blah", Db.POSTGRES))
    }
}