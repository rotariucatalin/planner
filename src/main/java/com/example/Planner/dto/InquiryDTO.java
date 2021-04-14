package com.example.Planner.dto;

import com.example.Planner.models.Company;
import com.example.Planner.models.Contact;

import java.io.Serializable;

public class InquiryDTO implements Serializable {

    private int id;
    private int inqNr;
    private int inqRevision;
    private String inqDescription;
    private String inqDate;
    private Float inqRate;
    private Float inqPrice;
    private String inqCurrency;
    private String inqStatus;
    private String inqPriority;
    private Company company;
    private Contact contact;
    private Company salesCompany;
    private Contact salesContact;
    private Contact inqProjectManager;

    public InquiryDTO() {
    }

    public InquiryDTO(int id, int inqNr, int inqRevision, String inqDescription, String inqDate, Float inqRate, Float inqPrice, String inqCurrency, String inqStatus, String inqPriority, Company company, Contact contact, Company salesCompany, Contact salesContact, Contact inqProjectManager) {
        this.id = id;
        this.inqNr = inqNr;
        this.inqRevision = inqRevision;
        this.inqDescription = inqDescription;
        this.inqDate = inqDate;
        this.inqRate = inqRate;
        this.inqPrice = inqPrice;
        this.inqCurrency = inqCurrency;
        this.inqStatus = inqStatus;
        this.inqPriority = inqPriority;
        this.company = company;
        this.contact = contact;
        this.salesCompany = salesCompany;
        this.salesContact = salesContact;
        this.inqProjectManager = inqProjectManager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInqNr() {
        return inqNr;
    }

    public void setInqNr(int inqNr) {
        this.inqNr = inqNr;
    }

    public int getInqRevision() {
        return inqRevision;
    }

    public void setInqRevision(int inqRevision) {
        this.inqRevision = inqRevision;
    }

    public String getInqDescription() {
        return inqDescription;
    }

    public void setInqDescription(String inqDescription) {
        this.inqDescription = inqDescription;
    }

    public String getInqDate() {

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        //return LocalDate.parse(inqDate).format(formatter);
        return inqDate;
    }

    public void setInqDate(String inqDate) {
        this.inqDate = inqDate;
    }

    public Float getInqRate() {
        return inqRate;
    }

    public void setInqRate(Float inqRate) {
        this.inqRate = inqRate;
    }

    public Float getInqPrice() {
        return inqPrice;
    }

    public void setInqPrice(Float inqPrice) {
        this.inqPrice = inqPrice;
    }

    public String getInqCurrency() {
        return inqCurrency;
    }

    public void setInqCurrency(String inqCurrency) {
        this.inqCurrency = inqCurrency;
    }

    public String getInqStatus() {
        return inqStatus;
    }

    public void setInqStatus(String inqStatus) {
        this.inqStatus = inqStatus;
    }

    public String getInqPriority() {
        return inqPriority;
    }

    public void setInqPriority(String inqPriority) {
        this.inqPriority = inqPriority;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Company getSalesCompany() {
        return salesCompany;
    }

    public void setSalesCompany(Company salesCompany) {
        this.salesCompany = salesCompany;
    }

    public Contact getSalesContact() {
        return salesContact;
    }

    public void setSalesContact(Contact salesContact) {
        this.salesContact = salesContact;
    }

    public Contact getInqProjectManager() {
        return inqProjectManager;
    }

    public void setInqProjectManager(Contact inqProjectManager) {
        this.inqProjectManager = inqProjectManager;
    }
}
