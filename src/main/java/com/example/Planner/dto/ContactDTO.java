package com.example.Planner.dto;

import com.example.Planner.models.Company;

import java.io.Serializable;

public class ContactDTO implements Serializable {

    private int id;
    private String name;
    private String position;
    private String department;
    private String country;
    private String email;
    private String phone;
    private String status;
    private boolean consent;
    private Company company;

    public ContactDTO() {
    }

    public ContactDTO(int id, String name, String position, String department, String country, String email, String phone, String status, boolean consent, Company company) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.consent = consent;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isConsent() {
        return consent;
    }

    public void setConsent(boolean consent) {
        this.consent = consent;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
