package com.jpragma.sbb.domain

import com.jpragma.sbb.validation.DateIntervalConstraint
import java.time.LocalDate
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

typealias Money = Double

data class DateInterval(val from:LocalDate, val to:LocalDate) {
	companion object {
		fun of(from: String, to: String): DateInterval = DateInterval(LocalDate.parse(from), LocalDate.parse(to))
	}
}


data class Invoice (
	@field:NotEmpty
	val id: String,
	@field:NotEmpty
	val client: String,
	@field:DateIntervalConstraint
	val interval:DateInterval,
	@field:Min(1)
	val amount: Money,
	@field:Min(1)
	val tax: Money,
	val notes: String? = null
)
