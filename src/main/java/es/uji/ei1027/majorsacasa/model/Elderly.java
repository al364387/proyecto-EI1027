package es.uji.ei1027.majorsacasa.model;

import java.time.LocalDate;

public class Elderly {
    private String socialAssisId;
    private String name;
    private String surname;
    private String email;
    private String DNI;
    private LocalDate birthDate;
    private int phoneNumber;
    private String address;
    private String bankAccount;
    private String username;
    private String password;

    public Elderly() {
    }

    public void setSocialAssisId(String socialAssisId) {
        this.socialAssisId = socialAssisId;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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
                "socialAsisNum='" + socialAssisId + '\'' +
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
