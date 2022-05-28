package com.jpragma.sbb.rest

import com.jpragma.sbb.domain.Invoice
import com.jpragma.sbb.repo.InvoiceRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class InvoicesController(
    private val invoiceRepository: InvoiceRepository
) {

    @GetMapping("/api/invoices")
    fun getInvoices(): Iterable<Invoice> {
        return invoiceRepository.findAll()
    }

    @PostMapping("/api/invoices")
    fun saveInvoice(@RequestBody @Valid invoice: Invoice): Invoice {
        return invoiceRepository.save(invoice)
    }

}