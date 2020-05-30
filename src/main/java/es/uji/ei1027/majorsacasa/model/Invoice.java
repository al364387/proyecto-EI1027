package es.uji.ei1027.majorsacasa.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;

public class Invoice {
    private String invoicenum;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private LocalDate date;
    private boolean catering;
    private boolean cleaning;
    private boolean nursing;
    private float price;


    public Invoice() {
    }


    public String getInvoicenum() {
        return invoicenum;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isCatering() {
        return catering;
    }

    public boolean isCleaning() {
        return cleaning;
    }

    public boolean isNursing() {
        return nursing;
    }

    public float getPrice() {
        return price;
    }

    public void setInvoicenum(String invoicenum) {
        this.invoicenum = invoicenum;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCatering(boolean catering) {
        this.catering = catering;
    }

    public void setCleaning(boolean cleaning) {
        this.cleaning = cleaning;
    }

    public void setNursing(boolean nursing) {
        this.nursing = nursing;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public void crearPDF(){
        // Se crea el documento
        try {
            Document documento = new Document();

// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
            String num = invoicenum.replace('/', '-');

            try {
                PdfWriter.getInstance(documento, new FileOutputStream(num+".pdf"));
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("A - No such file was found to generate the PDF "
                        + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }
            documento.open();

            // Se asocia el documento al OutputStream y se indica que el espaciado entre
// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento



            documento.addTitle("Majors a casa");

            Image logo = Image.getInstance("logo.png");

            logo.setAbsolutePosition(480,690);
            documento.add(logo);
            logo.scaleAbsolute(10, 30);

            //Image logo = Image.getInstance("logo.png");
            //documento.add(logo);

            Paragraph titulo = new Paragraph("Majors a Casa",
                    FontFactory.getFont("arial",   // fuente
                            22,                            // tamaño
                            Font.ITALIC,                   // estilo
                            BaseColor.CYAN));             // color

            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            String servicio = "";
            if(catering) servicio = "Catering";
            if(nursing) servicio = "Nursing";
            if(cleaning) servicio = "Cleaning";

            documento.add(new Paragraph("Compañia: " ));
            documento.add(new Paragraph("Correo electronico: " ));
            documento.add(new Paragraph("Servicio: " + servicio));
            documento.add(new Paragraph("Fecha: " + date));


            int numColumns = 3;
            PdfPTable tabla = new PdfPTable(numColumns);
            //Conseguir de alguna manera la lista de lineas de factura que iran en esta factura
            /*
            for(InvoiceLine invoiceLine : invoiceLines){
                tabla.addCell(invoiceLine.getNumber() + "");
                tabla.addCell(invoiceLine.getConcept());
                tabla.addCell(invoiceLine.getMonthPrice() + "");
            }
            */
            documento.add(tabla);

            documento.close();

            System.out.println("B - Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
        } catch (DocumentException ex) {
            System.out.println("C - The file not exists (Se ha producido un error al generar un documento): " + ex);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
