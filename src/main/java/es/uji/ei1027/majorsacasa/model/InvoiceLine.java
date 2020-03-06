package es.uji.ei1027.majorsacasa.model;

public class InvoiceLine {
    private int numberInvoice;
    private String concept;
    private float monthPrice;


    public InvoiceLine() {
    }

    public int getNumber() {
        return numberInvoice;
    }

    public void setNumber(int number) {
        this.numberInvoice = number;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public float getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(float monthPrice) {
        this.monthPrice = monthPrice;
    }
}
