package es.uji.ei1027.majorsacasa.dao;

import javax.sql.DataSource;

import es.uji.ei1027.majorsacasa.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InvoiceDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //Añadir Factura
    public void addInvoice(Invoice invoice) {
        jdbcTemplate.update("INSERT INTO Invoice VALUES(?, ?, ?, ?, ?, ?)",
                invoice.getInvoiceNumber(), invoice.getDate(), invoice.isCatering(), invoice.isCleaning(), invoice.isNursing(),
                invoice.getPrice());
    }

    //Borrar Factura
    public void deleteInvoice(String invoiceNumber) {
        jdbcTemplate.update("DELETE from Invoice where invoiceNumber=?",
                invoiceNumber);
    }

    //Listar Facturas
    public List<Invoice> getInvoices() {
        try {
            List<Invoice> c = jdbcTemplate.query("SELECT * from Invoice",
                    new InvoiceRowMapper());
            System.out.println("template: " + c);
            return c;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("sin voluntarios: ");
            return new ArrayList<Invoice>();
        }
    }
}
