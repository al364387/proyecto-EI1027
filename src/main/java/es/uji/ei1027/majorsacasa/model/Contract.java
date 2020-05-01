package es.uji.ei1027.majorsacasa.model;

import java.time.LocalDate;

public class Contract {

    private String numContract;
    private boolean catering;
    private boolean nursing;
    private boolean cleaning;
    private float price;
    private LocalDate startDate;
    private LocalDate endDate;
    private int serviceNumber;
    private String cifCompany;

    public Contract() {
    }

    public String getNumContract() {
        return numContract;
    }

    public void setNumContract(String numContract) {
        this.numContract = numContract;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(int serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getCifCompany() {
        return cifCompany;
    }

    public void setCifCompany(String cifCompany) {
        this.cifCompany = cifCompany;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "number='" + numContract + '\'' +
                ", catering=" + catering +
                ", nursing=" + nursing +
                ", cleaning=" + cleaning +
                ", price=" + price +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", serviceNumber=" + serviceNumber +
                ", cifCompany='" + cifCompany + '\'' +
                '}';
    }
}
