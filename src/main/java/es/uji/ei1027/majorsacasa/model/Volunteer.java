package es.uji.ei1027.majorsacasa.model;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class Volunteer {
    private int id;
    private String name;
    private String surname;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private LocalDate birthdate;

    private int phonenumber;
    private String address;
    private LocalDate acceptDate;
    private String username;
    private String password;
    private LocalDate endDate;

    private String state;

    public Volunteer() {
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAcceptDate(LocalDate acceptDate) {
        this.acceptDate = acceptDate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getAcceptDate() {
        return acceptDate;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate today(){
        return LocalDate.now();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

