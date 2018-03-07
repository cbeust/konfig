
import com.beust.kobalt.plugin.application.application
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.project

object Version {
    val main = "0.1"
    val kotlin = "1.2.10"
}

val p = project {
    name = "konfig"
    group = "com.beust"
    artifactId = name
    version = Version.main

    dependencies {
        compile("org.jetbrains.kotlin:kotlin-runtime:${Version.kotlin}",
                "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}",
                "org.jetbrains.kotlin:kotlin-compiler:${Version.kotlin}",
                "org.jetbrains.kotlin:kotlin-script-runtime:${Version.kotlin}",
                "org.jetbrains.kotlin:kotlin-script-util:${Version.kotlin}",
                "org.jetbrains.kotlin:kotlin-reflect:${Version.kotlin}")
    }

    dependenciesTest {
        compile("org.testng:testng:6.14.3")
    }

    assemble {
        jar {
            fatJar = true
        }
    }

    application {
        mainClass = "com.beust.konfig.MainKt"
    }
}
