package com.jpragma.sbb.domain

import am.ik.yavi.builder.validator
import java.time.LocalDate

@JvmInline
value class Money(val value:Double)

data class DateInterval(val from:LocalDate, val to:LocalDate) {
	companion object {
		fun of(from: String, to: String): DateInterval = DateInterval(LocalDate.parse(from), LocalDate.parse(to))
	}
}


data class Invoice (
	val id: String,
	val client: String,
	val interval:DateInterval,
	val amount: Money,
	val tax: Money,
	val notes: String? = null
) {
	companion object {
		val validator = validator<Invoice> {
			Invoice::id {
				notBlank()
			}
			Invoice::client {
				notBlank()
			}
		}
	}
	fun validate() = validator.validate(this)
}

