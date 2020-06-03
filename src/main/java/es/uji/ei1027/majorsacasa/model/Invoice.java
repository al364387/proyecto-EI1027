package es.uji.ei1027.majorsacasa.model;


import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

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

}
