package com.jpragma.sbb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SbbServer

fun main(args: Array<String>) {
	runApplication<SbbServer>(*args)
}

@RestController
class TestController {

	@GetMapping("/invoice")
	fun getSampleInvoice():Invoice {
		val inv = Invoice("Procom", 10000.0)
		return inv
	}
}

data class Invoice(
	val client: String,
	val amount: Double
)
