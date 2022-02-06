package com.jpragma.sbb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SbbServer

fun main(args: Array<String>) {
    runApplication<SbbServer>(*args)
}
