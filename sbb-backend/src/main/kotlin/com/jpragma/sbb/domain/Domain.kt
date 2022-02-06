package com.jpragma.sbb.domain

import com.jpragma.sbb.validation.DateIntervalConstraint
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Embedded
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

typealias Money = Double

data class DateInterval(@Column("FROM_DATE") val from:LocalDate, @Column("TO_DATE") val to:LocalDate) {
	companion object {
		fun of(from: String, to: String): DateInterval = DateInterval(LocalDate.parse(from), LocalDate.parse(to))
	}
}


@Table("INVOICE")
data class Invoice (
	@Id
	@field:NotEmpty
	val id: String,
	@field:NotEmpty
	val client: String,
	@Embedded.Nullable
	@field:DateIntervalConstraint
	val interval:DateInterval,
	@field:Min(1)
	val amount: Money,
	@field:Min(0)
	val tax: Money,
	val notes: String? = null,
	@Version
	val version: Int? = null
)
