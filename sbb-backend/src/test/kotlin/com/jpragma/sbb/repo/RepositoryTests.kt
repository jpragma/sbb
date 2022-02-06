package com.jpragma.sbb.repo

import com.jpragma.sbb.domain.DateInterval
import com.jpragma.sbb.domain.Invoice
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.hasSize
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.java.isPresent
import strikt.java.propertiesAreEqualTo
import java.time.LocalDate

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class RepositoryTests {
    @Autowired
    lateinit var repo:InvoiceRepository

    @Test
    @Sql(scripts = ["/test-data.sql"])
    internal fun invoiceRead() {
        val invoices = repo.findAll().toList()
        expectThat(invoices).hasSize(1)
        expectThat(invoices.first()).propertiesAreEqualTo(Invoice(
            "001",
            "Acme",
            DateInterval.of("2021-11-01", "2021-11-30"),
            6000.0,
            300.0,
            "lorem ipsum"
        ))
    }

    @Test
    internal fun invoicePersistence() {
        expectThat(repo.findAll().toList()).isEmpty()
        val invoice = Invoice("001", "Acme", DateInterval(LocalDate.now(), LocalDate.now()), 100.0, 10.0, "Lorem")
        repo.save(invoice)
        val expectedPersisted = invoice.copy(version = 0)
        expectThat(repo.findAll()).containsExactly(expectedPersisted)
    }

    @Test
    internal fun invoiceSaveAndUpdate() {
        expectThat(repo.findAll().toList()).isEmpty()
        val invoice = Invoice("001", "Acme", DateInterval(LocalDate.now(), LocalDate.now()), 100.0, 10.0, "Lorem")
        val persisted = repo.save(invoice)
        expectThat(persisted.version).isEqualTo(0)
        val invoice2 = invoice.copy(amount = 200.0, version = persisted.version)
        repo.save(invoice2)
        expectThat(repo.count()).isEqualTo(1)
        expectThat(repo.findById("001"))
            .isPresent()
            .and {
                get {amount}.isEqualTo(200.0)
            }
    }

}