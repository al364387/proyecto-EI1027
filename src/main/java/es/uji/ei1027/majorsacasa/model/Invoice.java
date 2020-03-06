package es.uji.ei1027.majorsacasa.model;

import java.time.LocalDate;

public class Invoice {
    private String invoiceNumber;
    private LocalDate date;
    private boolean catering;
    private boolean cleaning;
    private boolean nursing;
    private float price;


    public Invoice() {
    }


    public String getInvoiceNumber() {
        return invoiceNumber;
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

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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
