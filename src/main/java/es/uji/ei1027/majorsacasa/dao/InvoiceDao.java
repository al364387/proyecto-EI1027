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
            System.out.println("template: " + c);
            return c;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("sin facturas: ");
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

    /* Sacar lista de facturas asociadas a una empresa
    SELECT Invoice.* FROM Invoice
    INNER JOIN Invoiceline on Invoice.invoicenum = Invoiceline.invoicenumid
    INNER JOIN Request on Invoiceline.requestnum = Request.number
    INNER JOIN Contract on Request.contractid = Contract.numcontract
    INNER JOIN Company on Contract.cifcompany = Company.cif
    WHERE Company.cif='Y3418145O';
     */

    public List<Invoice> getInvoicesCompany(String cif) {
        try {
            List<Invoice> c = jdbcTemplate.query("SELECT Invoice.* FROM Invoice\n" +
                            "    INNER JOIN Invoiceline on Invoice.invoicenum = Invoiceline.invoicenumid\n" +
                            "    INNER JOIN Request on Invoiceline.requestnum = Request.number\n" +
                            "    INNER JOIN Contract on Request.contractid = Contract.numcontract\n" +
                            "    INNER JOIN Company on Contract.cifcompany = Company.cif\n" +
                            "    WHERE Company.cif=?;",
                    new InvoiceRowMapper(), cif);
            System.out.println("template: " + c);
            return c;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("sin facturas: ");
            return new ArrayList<Invoice>();
        }
    }


    //NO SE NI SI SE HACE AQUÍ, ESO ES UN PROBLEMA
    /*
    public void crearPDF(Invoice invoice, List<InvoiceLine> invoiceLines) {
        // Se crea el documento
        try {
            Document documento = new Document();

            // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
            String num = invoice.getInvoicenum().replace('/', '-');

            try {
                PdfWriter.getInstance(documento, new FileOutputStream(num + ".pdf"));
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No such file was found to generate the PDF "
                        + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }
            documento.open();

            // Se asocia el documento al OutputStream y se indica que el espaciado entre
            // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento


            documento.addTitle("Majors a casa");

            Image logo = Image.getInstance("logo.png");

            logo.setAbsolutePosition(0, 700);
            documento.add(logo);
            //logo.scaleToFit(10, 30);
            logo.scaleAbsolute(10, 30);

            Paragraph titulo = new Paragraph("Majors a Casa",
                    FontFactory.getFont("arial",   // fuente
                            22,                            // tamaño
                            Font.ITALIC,                   // estilo
                            BaseColor.CYAN));             // color

            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            String servicio = "";
            if (invoice.isCatering()) servicio = "Catering";
            if (invoice.isNursing()) servicio = "Nursing";
            if (invoice.isCleaning()) servicio = "Cleaning";

            documento.add(new Paragraph("Compañia: " ));
            documento.add(new Paragraph("Correo electronico: " ));
            documento.add(new Paragraph("Servicio: " + servicio));
            documento.add(new Paragraph("Fecha: " + invoice.getDate()));


            int numColumns = 3;
            PdfPTable tabla = new PdfPTable(numColumns);
            //Conseguir de alguna manera la lista de lineas de factura que iran en esta factura
            for (InvoiceLine invoiceLine : invoiceLines) {
                tabla.addCell(invoiceLine.getNumber() + "");
                tabla.addCell(invoiceLine.getConcept());
                tabla.addCell(invoiceLine.getMonthPrice() + "");
            }
            documento.add(tabla);

            documento.close();

            System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
        } catch (DocumentException ex) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + ex);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    */
}
