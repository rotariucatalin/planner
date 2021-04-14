package com.example.Planner.dto;

import com.example.Planner.models.Company;
import com.example.Planner.models.Contact;

import java.io.Serializable;

public class CompanyDTO implements Serializable {

    private int id;
    private String name;
    private String type;
    private String email;
    private String phoneNumber;
    private String postAddress;
    private String city;
    private String website;
    private String companyDescription;
    private Company salesCompany;
    private Contact salesPerson;

    public CompanyDTO() {
    }

    public CompanyDTO(int id, String name, String type, String email, String phoneNumber, String postAddress, String city, String website, String companyDescription, Company salesCompany, Contact salesPerson) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.postAddress = postAddress;
        this.city = city;
        this.website = website;
        this.companyDescription = companyDescription;
        this.salesCompany = salesCompany;
        this.salesPerson = salesPerson;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public Company getSalesCompany() {
        return salesCompany;
    }

    public void setSalesCompany(Company salesCompany) {
        this.salesCompany = salesCompany;
    }

    public Contact getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(Contact salesPerson) {
        this.salesPerson = salesPerson;
    }
}
