package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.InvoiceLine;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceLineRowMapper implements RowMapper<InvoiceLine> {

    public InvoiceLine mapRow(ResultSet rs, int rowNum) throws SQLException {
        InvoiceLine invoiceLine = new InvoiceLine();
        invoiceLine.setNumber(rs.getInt("number"));
        invoiceLine.setConcept(rs.getString("concept"));
        invoiceLine.setMonthPrice(rs.getFloat("monthPrice"));
        invoiceLine.setInvoiceNumId(rs.getString("invoiceNumId"));
        invoiceLine.setRequestNum(rs.getInt("requestNum"));

        return invoiceLine;
    }
}
