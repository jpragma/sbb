package com.jpragma.sbb.repo

import com.jpragma.sbb.domain.Invoice
import org.springframework.data.repository.CrudRepository

interface InvoiceRepository : CrudRepository<Invoice, String> {
}

//@Repository
//class JdbcInvoiceRepository(private val jdbcTemplate: JdbcTemplate) {
//
//    private val rowMapper = RowMapper<Invoice> { rs: ResultSet, rowNum: Int ->
//        Invoice(
//            rs.getString("ID"),
//            rs.getString("CLIENT"),
//            DateInterval(rs.getDate("FROM_DATE").toLocalDate(), rs.getDate("TO_DATE").toLocalDate()),
//            rs.getDouble("AMOUNT"),
//            rs.getDouble("TAX"),
//            rs.getString("NOTES")
//        )
//    }
//
//    override fun save(invoice: Invoice): Invoice {
//        with(invoice) {
//            jdbcTemplate.update(
//                "INSERT INTO INVOICE (ID, CLIENT, FROM_DATE, TO_DATE, AMOUNT, TAX, NOTES) VALUES (?, ?, ?, ?, ?, ?, ?)",
//                    id, client, interval.from, interval.to, amount, tax, notes
//                )
//        }
//        return invoice
//    }
//
//    override fun findAll(): Collection<Invoice> {
//        return jdbcTemplate.query("SELECT * FROM INVOICE", rowMapper)
//    }

//}