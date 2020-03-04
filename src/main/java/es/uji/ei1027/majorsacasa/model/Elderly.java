package es.uji.ei1027.majorsacasa.model;

import java.util.Date;

public class Elderly {
    private String socialAsisNum;
    private String name;
    private String surname;
    private String email;
    private String DNI;
    private Date birthDate;
    private int phoneNumber;
    private String address;
    private String bankAccount;
    private String username;
    private String password;

    public Elderly() {
    }

    public String getSocialAsisNum() {
        return socialAsisNum;
    }

    public void setSocialAsisNum(String socialAsisNum) {
        this.socialAsisNum = socialAsisNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Elderly{" +
                "socialAsisNum='" + socialAsisNum + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", DNI='" + DNI + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}