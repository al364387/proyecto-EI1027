package es.uji.ei1027.majorsacasa.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Request {
    private int number;
    private String state;
    private LocalDate startDate;
    private LocalDate endDate;  //acepta nulos
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;  //tiempo del servicio
    private boolean catering;
    private boolean nursing;
    private boolean cleaning;
    private String description;
    private String elderlyDNI; // DNI del mayor que haga la peticion
    private int contractId; // Según el tipo de servicio

   public Request() {
       startDate = LocalDate.now();
       this.state="Pendiente";
   }

   public int getNumber() {
       return number;
   }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getElderlyDNI() {
        return elderlyDNI;
    }

    public void setElderlyDNI(String elderlyDNI) {
        this.elderlyDNI = elderlyDNI;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "number=" + number +
                ", state='" + state + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", time=" + time +
                ", catering=" + catering +
                ", nursing=" + nursing +
                ", cleaning=" + cleaning +
                ", description='" + description + '\'' +
                ", elderlyId='" + elderlyDNI + '\'' +
                ", contractId='" + contractId + '\'' +
                '}';
    }

    public String getType(){
       if(catering) return "Catering";
       if(nursing) return "Nursing";
       else return "Cleaning";
    }
}