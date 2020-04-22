package es.uji.ei1027.majorsacasa.model;

public class InvoiceLine {
    private int number;
    private String concept;
    private float monthPrice;
    private String invoiceNumId;
    private int requestNum;



    public InvoiceLine() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getInvoiceNumId() {
        return invoiceNumId;
    }

    public void setInvoiceNumId(String invoiceNumId) {
        this.invoiceNumId = invoiceNumId;
    }

    public int getRequestNum() {
        return requestNum;
    }

    public void setRequestNum(int requestNum) {
        this.requestNum = requestNum;
    }
}
