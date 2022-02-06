package com.jpragma.sbb.repo

import com.jpragma.sbb.domain.Invoice
import org.springframework.data.repository.CrudRepository

interface InvoiceRepository : CrudRepository<Invoice, String> {
}