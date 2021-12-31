package com.jpragma.sbb.validation

import com.jpragma.sbb.domain.DateInterval
import java.time.LocalDate
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [DateIntervalValidator::class])
annotation class DateIntervalConstraint(
    val min: String = "1970-01-01",
    val max: String = "9999-12-31",
    val message: String = "Invalid date interval",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class DateIntervalValidator : ConstraintValidator<DateIntervalConstraint, DateInterval> {

    private lateinit var minValue: LocalDate
    private lateinit var maxValue: LocalDate


    override fun initialize(constraint: DateIntervalConstraint) {
        minValue = LocalDate.parse(constraint.min)
        maxValue = LocalDate.parse(constraint.max)
    }

    override fun isValid(value: DateInterval?, context: ConstraintValidatorContext?): Boolean {
        return  (value == null) || (insideInterval(value.from) && insideInterval(value.to) && (!value.to.isBefore(value.from)))
    }

    private fun insideInterval(value: LocalDate):Boolean {
        return !value.isBefore(minValue) && !value.isAfter(maxValue)
    }
}