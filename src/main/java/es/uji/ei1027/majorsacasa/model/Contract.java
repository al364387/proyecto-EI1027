package es.uji.ei1027.majorsacasa.model;

import java.util.Date;

public class Contract {
    private String number;
    private boolean catering;
    private boolean nursing;
    private boolean cleaning;
    private float price;
    private Date startDate;
    private Date endDate;
    private int serviceNumber;

    public Contract() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isCatering() {
        return catering;
    }

    public void setCatering(boolean catering) {
        this.catering = catering;
    }

    public boolean isNursing() {
        return nursing;
    }

    public void setNursing(boolean nursing) {
        this.nursing = nursing;
    }

    public boolean isCleaning() {
        return cleaning;
    }

    public void setCleaning(boolean cleaning) {
        this.cleaning = cleaning;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(int serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "number='" + number + '\'' +
                ", catering=" + catering +
                ", nursing=" + nursing +
                ", cleaning=" + cleaning +
                ", price=" + price +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", serviceNumber=" + serviceNumber +
                '}';
    }
}
