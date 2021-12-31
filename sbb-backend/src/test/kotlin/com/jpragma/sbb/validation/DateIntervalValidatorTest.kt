package com.jpragma.sbb.validation

import com.jpragma.sbb.domain.DateInterval
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import strikt.api.expectThat
import strikt.assertions.isFalse
import strikt.assertions.isTrue
import java.time.LocalDate

internal class DateIntervalValidatorTest {
    private val validator = DateIntervalValidator()

    @BeforeEach
    internal fun setUp() {
        validator.initialize(DateIntervalConstraint("2021-01-01", "2021-12-31", "Invalid date interval", groups = arrayOf(), payload = arrayOf()))
    }

    @Test
    fun `should find no validation error, if value is null`() {
        expectThat(validator.isValid(value = null, context = null)).isTrue()
    }

    @Test
    fun `should find no validation error, if value is min or max`() {
        expectThat(
            validator.isValid(value = DateInterval(LocalDate.parse("2021-01-01"), LocalDate.parse("2021-12-31")), context = null)
        ).isTrue()
    }

    @ParameterizedTest
    @CsvSource("2020-12-31,2021-12-31", "2021-05-15,2022-01-01", "2021-10-11,2021-10-09")
    fun `should return validation error, if value is invalid`(from:String, to:String) {
        expectThat(
            validator.isValid(value = DateInterval(LocalDate.parse(from), LocalDate.parse(to)), context = null)
        ).isFalse()
    }
}