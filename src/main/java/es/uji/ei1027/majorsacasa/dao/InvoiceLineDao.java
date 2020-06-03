package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.InvoiceLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InvoiceLineDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Añadir Linea de Factura
    public void addInvoiceLine(InvoiceLine invoiceLine) {
        jdbcTemplate.update("INSERT INTO InvoiceLine VALUES(?, ?, ?, ?, ?)",
                invoiceLine.getNumber(), invoiceLine.getConcept(), invoiceLine.getMonthPrice(),
                invoiceLine.getInvoiceNumId(), invoiceLine.getRequestNum());
    }

    // Borrar Linea de Factura
    public void deleteInvoiceLine(int number) {
        jdbcTemplate.update("DELETE from InvoiceLine where number=?",
                number);
    }

    // Modificar atributos de la Linea de Factura
    public void updateInvoiceLine(InvoiceLine invoiceLine) {
        jdbcTemplate.update("UPDATE InvoiceLine SET concept =?, monthPrice =? WHERE number =?",
                invoiceLine.getConcept(),invoiceLine.getMonthPrice(), invoiceLine.getNumber());
    }

    // Obtiene InvoiceLine
    public InvoiceLine getInvoiceLine(int number) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM InvoiceLine WHERE number =?",
                    new InvoiceLineRowMapper(), number);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Listar Lineas de Facturas
    public List<InvoiceLine> getInvoiceLines(String invoiceNumId) {
        try {
            return jdbcTemplate.query("SELECT * from InvoiceLine WHERE invoiceNumId=?" ,
                    new InvoiceLineRowMapper(), invoiceNumId);
        } catch (EmptyResultDataAccessException e) {

            return new ArrayList<InvoiceLine>();
        }
    }
}
