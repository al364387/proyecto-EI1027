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
                invoice.getInvoicenum(), invoice.getDate(), invoice.isCatering(), invoice.isCleaning(), invoice.isNursing(),
                invoice.getPrice());
    }


    //Borrar Factura
    public void deleteInvoice(String invoicenum) {
        jdbcTemplate.update("DELETE from Invoice where invoicenum= ?",
                invoicenum);
    }

    //Listar Facturas
    public List<Invoice> getInvoices() {
        try {
            List<Invoice> c = jdbcTemplate.query("SELECT * from Invoice",
                    new InvoiceRowMapper());
            return c;
        } catch (EmptyResultDataAccessException e) {

            return new ArrayList<Invoice>();
        }
    }

    public Invoice getInvoice(String invoicenum){

        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Invoice WHERE invoicenum =?", new InvoiceRowMapper(), invoicenum);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Invoice> getInvoicesCompany(String cif) {
        try {
            List<Invoice> c = jdbcTemplate.query("SELECT Invoice.* FROM Invoice\n" +
                            "    INNER JOIN Invoiceline on Invoice.invoicenum = Invoiceline.invoicenumid\n" +
                            "    INNER JOIN Request on Invoiceline.requestnum = Request.number\n" +
                            "    INNER JOIN Contract on Request.contractid = Contract.numcontract\n" +
                            "    INNER JOIN Company on Contract.cifcompany = Company.cif\n" +
                            "    WHERE Company.cif=?;",
                    new InvoiceRowMapper(), cif);

            return c;
        } catch (EmptyResultDataAccessException e) {

            return new ArrayList<Invoice>();
        }
    }
}
