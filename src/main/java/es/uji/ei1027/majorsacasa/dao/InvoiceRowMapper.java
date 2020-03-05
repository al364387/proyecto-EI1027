package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Invoice;

import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceRowMapper implements RowMapper<Invoice> {
    public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException{
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(rs.getString("invoiceNumber"));
        Date date = rs.getDate("date");
        invoice.setDate(date != null ? date.toLocalDate() : null);
        invoice.setCatering(rs.getBoolean("catering"));
        invoice.setNursing(rs.getBoolean("nursing"));
        invoice.setCleaning(rs.getBoolean("cleaning"));
        invoice.setPrice(rs.getFloat("price"));
        return invoice;
    }


}
