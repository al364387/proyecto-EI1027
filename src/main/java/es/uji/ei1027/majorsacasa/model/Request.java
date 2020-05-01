package es.uji.ei1027.majorsacasa.model;

import java.sql.Time;
import java.util.Date;

public class Request {
    private int number;
    private String state;
    private Date startDate;
    private Date endDate;  //acepta nulos
    private Time time;  //tiempo del servicio
    private boolean catering;
    private boolean nursing;
    private boolean cleaning;
    private String description;
    private String elderlyId; //Id del mayor que haga la peticion
    private String contractId; //segun el tipo de servicio

   public Request() {
       this.startDate = new Date();
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

    public Date getTime() {
        return time;
    }

    public void setTime(Time time) {
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

    public String getElderlyId() {
        return elderlyId;
    }

    public void setElderlyId(String elderlyId) {
        this.elderlyId = elderlyId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
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
                ", elderlyId='" + elderlyId + '\'' +
                ", contractId='" + contractId + '\'' +
                '}';
    }
}