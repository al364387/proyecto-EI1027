package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Invoice;
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

    //Añadir Linea de Factura
    public void addInvoiceLine(InvoiceLine invoiceLine) {
        jdbcTemplate.update("INSERT INTO InvoiceLine VALUES(?, ?, ?)",
                invoiceLine.getNumber(), invoiceLine.getConcept(), invoiceLine.getMonthPrice());
    }

    //Borrar Linea de Factura
    public void deleteInvoiceLine(int numberInvoice) {
        jdbcTemplate.update("DELETE from InvoiceLine where number=?",
                numberInvoice);
    }

    //Modificar atributos de la Linea de Factura
    public void updateInvoiceLine(InvoiceLine invoiceLine) {
        jdbcTemplate.update("UPDATE InvoiceLine SET concept =?, monthPrice =? WHERE numberInvoice =?", invoiceLine.getConcept(),invoiceLine.getMonthPrice(), invoiceLine.getNumber());
    }

    //Obtiene InvoiceLine
    public InvoiceLine getInvoiceLine(int numberInvoice) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM InvoiceLine WHERE numberInvoice =?", new InvoiceLineRowMapper(), numberInvoice);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Listar Lineas de Facturas
    public List<InvoiceLine> getInvoiceLines() {
        try {
            List<InvoiceLine> c = jdbcTemplate.query("SELECT * from InvoiceLine",
                    new InvoiceLineRowMapper());
            System.out.println("template: " + c);
            return c;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("sin lineas de factura: ");
            return new ArrayList<InvoiceLine>();
        }
    }


}
