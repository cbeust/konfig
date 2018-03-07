# Konfig
Statically typed configuration for Kotlin

## What is Konfig?

Konfig allows you to express your configuration files in Kotlin so that the objects created after
parsing your configuration are the exact objects that your code expects. This guarantees that
your code and its configuration files will never go out of sync and they will be verified by
the Kotlin compiler.

## How does it work?

Assume your configuration classes are as follows:

```kotlin
enum class Db { POSTGRES, MYSQL }
data data class Config(var name: String = "", val db: Db = Db.MYSQL)
```

Your configuration file will be the following valid Kotlin, saved in a Kotlin Script file, e.g. `config.kts`:

```kotlin
Config(
    name = "John",
    db = Db.POSTGRES
)
```

You can parse this file with the following code:

```kotlin
val config = Konfig().parse<Config>(FileReader("test.kts"))
assertThat(config).isEqualTo(Config("John", Db.POSTGRES))
```

## Pros and cons

The immediate benefit is that your configuration syntax can evolve without any risk of ever breaking, as can often
happen when you use some other external format (XML, JSON, .properties, etc...).

Another advantage is that since your configuration file is pure Kotlin, you can use any piece of Kotlin code
inside that you need, e.g. retrieving environment variables, etc...

There is a sizeable disadvantage to this approach: the Kotlin Script parser is very slow. Admittedly, it's still
experimental and being actively worked on, but on my laptop, parsing even a trivial configuration takes between
five and six seconds.
