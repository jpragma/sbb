package com.jpragma.sbb

import com.jpragma.sbb.domain.Invoice
import com.jpragma.sbb.repo.InvoiceRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SbbServer

fun main(args: Array<String>) {
    runApplication<SbbServer>(*args)
}

@RestController
class InvoicesController(
    private val invoiceRepository: InvoiceRepository
) {

    @GetMapping("/invoices")
    fun getInvoices(): Iterable<Invoice> {
        return invoiceRepository.findAll()
    }

    @PostMapping("/invoices")
    fun saveInvoice(@RequestBody invoice: Invoice): ResponseEntity<Any> {
        val violations = invoice.validate()
        if (violations.isValid) {
            val saved = invoiceRepository.save(invoice)
            return ResponseEntity.ok<Any>(saved)
        } else {
            return ResponseEntity.badRequest().body<Any>(violations.details())
        }
    }

}

